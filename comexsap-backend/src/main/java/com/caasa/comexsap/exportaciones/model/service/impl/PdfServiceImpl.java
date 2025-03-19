package com.caasa.comexsap.exportaciones.model.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.caasa.comexsap.exportaciones.model.persistence.extended.ClienteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ConfiguracionExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ProductoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ReporteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.service.ExportacionMaritimoService;
import com.caasa.comexsap.exportaciones.model.service.PackingListService;
import com.caasa.comexsap.exportaciones.model.service.PdfService;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionEtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePackingListTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPosicionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.comexsap.exportaciones.util.DateUtil;
import com.caasa.comexsap.exportaciones.util.PedidoUtil;

@Service
public class PdfServiceImpl implements PdfService {

	private static final String PDF_RESOURCES = "/pdfresources/";
	private static final String TEMPLATE_PACKINGLIST = "packinglist";
	private static final String TEMPLATE_PACKINGLIST_CARGASUELTA = "packinglist_cargasuelta";
	private static final String TEMPLATE_PACKINGLIST_AEREO = "packinglist_aereo";
	private static final String TEMPLATE_REPORTE_COTIZACION = "cotizacion_reporte";
	private static final String TEMPLATE_FACTURA_COMERCIAL = "factura_comercial";
	private static final String TEMPLATE_FACTURA_COMERCIAL_CARGASUELTA = "factura_comercial_cargasuelta";
	private static final String TEMPLATE_EXPORTACION_MARITIMA = "exportacion_maritima";
	
	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	@Autowired
	private ClienteExtendedMapper clienteExtendedMapper;

	@Autowired
	private ConfiguracionExtendedMapper configuracionExtendedMapper;

	@Autowired
	private ProductoExtendedMapper productoExtendedMapper;
	
	@Autowired
	private ExportacionMaritimoService exportacionMaritimoService;
		
	@Autowired
	private PackingListService packingListService;
	
	@Autowired
	private ReporteExtendedMapper reporteExtendedMapper;
	
	@Override
	public ResponseEntity<Resource> generarPackingListPdf(int idExportacion, boolean versionIngles, boolean original) throws Exception {
    	String html = "", template = "";
		Context context;
		
		ExportacionMaritimoTO documento = exportacionMaritimoService.obtenerExportacionMaritimoxId(idExportacion);
    	if(documento.getIdDespacho() == Constantes.DESPACHO_CONTENEDOR) {
    		List<ReporteContenedorTO> contenedores = packingListService.reporteContenedor(idExportacion);
    		context = getContextPackingListPdf(documento, contenedores);
    		template = TEMPLATE_PACKINGLIST;
    	} else if(documento.getIdDespacho() == Constantes.DESPACHO_CARGA_SUELTA) {
    		List<PackingListCargaSueltaTO> posiciones = packingListService.obtenerPackingListCargaSuelta(idExportacion);
			List<ExportacionEtiquetaTO> etiquetas = exportacionMaritimoService.listarExportacionEtiquetas(idExportacion);
    		context = getContextPackingListCargaSueltaPdf(documento, posiciones, etiquetas);
    		template = TEMPLATE_PACKINGLIST_CARGASUELTA;
    	} else {
    		List<PackingListAereoTO> posiciones = packingListService.obtenerPackingListAereo(idExportacion);
    		context = getContextPackingListAereoPdf(documento, posiciones);
    		template = TEMPLATE_PACKINGLIST_AEREO;
    	}

		context.setVariable("versionIngles", versionIngles);
		context.setVariable("original", original);
		html = loadAndFillTemplate(context, template);
    	String xhtml = convertToXhtml(html);
		return renderPdf(xhtml);
	}

	@Override
	public ResponseEntity<Resource> generarCotizacionPdf(CotizacionTO cotizacion, List<PosicionPedidoTO> posiciones, String usuario) throws Exception {
		Context context = getContextCotizacionPdf(cotizacion, posiciones, usuario);
		String html = loadAndFillTemplate(context, TEMPLATE_REPORTE_COTIZACION);
		String xhtml = convertToXhtml(html);
		return renderPdf(xhtml);
	}

	@Override
	public ResponseEntity<Resource> generarFacturaComercialPdf(RequestReporteFacturaComercialTO request, boolean versionIngles, boolean original, int tipoFactura) throws Exception {
		String html = "", template = "";
		Context context;
		
		if(request.getTipoDespacho() == Constantes.DESPACHO_CONTENEDOR || request.getTipoDespacho() == Constantes.DESPACHO_AEREO) {
			context = getContextFacturaComercialPdf(request);
			template = TEMPLATE_FACTURA_COMERCIAL;
		} else {
			List<PackingListCargaSueltaTO> packingList = packingListService.obtenerPackingListCargaSuelta(request.getIdExportacion());
			List<ExportacionEtiquetaTO> etiquetas = exportacionMaritimoService.listarExportacionEtiquetas(request.getIdExportacion());
			context = getContextFacturaComercialCargaSueltaPdf(request, packingList, etiquetas, tipoFactura);
			template = TEMPLATE_FACTURA_COMERCIAL_CARGASUELTA;
			context.setVariable("tipoFactura", tipoFactura);
		}
		
		context.setVariable("versionIngles", versionIngles);
		context.setVariable("original", original);
		html = loadAndFillTemplate(context, template);
		String xhtml = convertToXhtml(html);
		return renderPdf(xhtml);
	}

	@Override
	public ResponseEntity<Resource> generarExportacionMaritimaPdf(RequestReporteFacturaTO request) throws Exception {
		Context context = getContextExportacionMaritimaPdf(request);
		String html = loadAndFillTemplate(context, TEMPLATE_EXPORTACION_MARITIMA);
		String xhtml = convertToXhtml(html);
		return renderPdf(xhtml);
	}

	private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.setIndentContent(true);
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setSmartIndent(true);
        tidy.setShowWarnings(false);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);

        Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), null);

        OutputStream out = new ByteArrayOutputStream();
        tidy.pprint(htmlDOM, out);
        return out.toString();
    }
	
    private ResponseEntity<Resource> renderPdf(String html) throws Exception {
        File file = File.createTempFile("packinglist", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        URL url = new ClassPathResource(PDF_RESOURCES).getURL();
        String externalForm = url.toExternalForm();
        renderer.setDocumentFromString(html, externalForm);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        
        InputStream inputStream = new FileInputStream(file);
        InputStreamResource streamResource = new InputStreamResource(inputStream);
        MediaType mediaType = MediaType.APPLICATION_PDF;
			
        return ResponseEntity.ok()
					.header("Content-Disposition", "inline; filename=\"" + file.getName() + "\"")
					.contentLength(file.length())
					.contentType(mediaType)
					.body(streamResource);
    }
    
    private String loadAndFillTemplate(Context context, String templateName) {
        return springTemplateEngine.process(templateName, context);//templates
    }

    private Context getContextCotizacionPdf(CotizacionTO cotizacion, List<PosicionPedidoTO> posiciones, String usuario) {
    	float importeSubTotal = 0, importeTotal = 0, importeFlete = 0, importeSeguro = 0, cantidadTotal = 0, pesoTotal = 0;
		int contMaterial = 0, numeroContenedor = 1;
		String descripcionTotal = "", descripcionSubTotal = "", descripcionFlete = "", descripcionSeguro = "", nombrePuerto = "";
		String descripcionJerarquia = "", cargoUsuario = "", tipoDocumento = "";
		
		List<String> listaMaterial = new ArrayList<String>();
		List<PosicionPedidoTO> listaPosicion = new ArrayList<PosicionPedidoTO>();
		    	    	
        String codigoPedido = "";
        if (cotizacion.isTipoDocumento()) {
        	codigoPedido = Constantes.COTIZACION + cotizacion.getCodigoPedido();
        	tipoDocumento = Constantes.TEXTO_COTIZACION;
		} else {
			codigoPedido = Constantes.PROFORMA + cotizacion.getCodigoPedido();
			tipoDocumento = Constantes.TEXTO_PROFORMA;
		}
        
        cargoUsuario = reporteExtendedMapper.obtenerCargoUsuario(cotizacion.getCodigoPersonal());
        if(cargoUsuario != null) {
        	cargoUsuario = cargoUsuario.toLowerCase();
        }
        
        ClienteTO cliente = null;
		if (cotizacion.getCodigoCliente() != null) {
			cliente = this.clienteExtendedMapper.obtenerClienteSapxCodigoSap(cotizacion.getCodigoCliente());
		} else {
			cliente = this.clienteExtendedMapper.obtenerClienteNuevoxDescripcion(cotizacion.getNombreCliente());
		}
		
        ParametroTO pais = null;
		if (cliente != null && cliente.getCodigoPais() != null) {
			pais = this.configuracionExtendedMapper.obtenerPaisxParametro(Constantes.APP_CODE,
					Constantes.PARAMETRO_D020, cliente.getCodigoPais().trim());
		}
		
		String observacion = "";
		if(cotizacion.getObservacion() != null) {
			observacion = cotizacion.getObservacion().replace("\n", "<br/>");
		}
		
		Context context = new Context();
        context.setVariable("fechaActual", DateUtil.getFormatedLarge());
		context.setVariable("codigoPedido", codigoPedido);
		context.setVariable("tipoDocumento", tipoDocumento);
		context.setVariable("nombreContacto", cotizacion.getNombreContacto());
		context.setVariable("cargoContacto", cotizacion.getCargoContacto());
		context.setVariable("razonSocial", cotizacion.getNombreCliente());
		context.setVariable("lugarContacto", (pais != null ? pais.getDescripcion() : ""));
		context.setVariable("nombreCondicionPago", cotizacion.getNombreCondicionPago());
		context.setVariable("vigencia", cotizacion.getVigencia());
		context.setVariable("lugarTotal", cotizacion.getLugarTotal());
		context.setVariable("usuario", usuario);
		context.setVariable("cargoUsuario", cargoUsuario);
		context.setVariable("precioCabecera", cotizacion.getPrecioCabecera());
		context.setVariable("tiempoEntrega", cotizacion.getTiempoEntrega());
		context.setVariable("observacion", observacion);
        
		// Campos calculados
		for (PosicionPedidoTO posicion : posiciones) {
			ProductoTO producto = productoExtendedMapper.obtenerUnidadesPaquetexCodigo(posicion.getCodigoSAP());
			BigDecimal paquetes = new BigDecimal(posicion.getUnidadTotal() / (producto == null ? 1 : producto.getUnidadesPaquete()));
			posicion.setNroPaquete(paquetes);
			importeSubTotal += posicion.getSubtotal();
		}

		if(cotizacion.getNumeroContenedor() > 0) {
			numeroContenedor = cotizacion.getNumeroContenedor();
		}
		
		importeSeguro = cotizacion.getSeguroInternacional().floatValue();
		importeFlete = numeroContenedor * cotizacion.getImporteFlete().floatValue();

		descripcionSeguro = Constantes.SEGURO_INTERNACIONAL;
		descripcionFlete = Constantes.FLETE + " " + numeroContenedor + " " + " X " + Constantes.CNT
				+ " " + cotizacion.getNombrePuertoOrigen() + " - " + cotizacion.getNombrePuertoDestino();
		
		if(cotizacion.getNombreIncotermComercial() == null) {
			cotizacion.setNombreIncotermComercial(cotizacion.getNombreIncoterm());
		}
		
		if((cotizacion.getNombreIncoterm().equalsIgnoreCase(Constantes.CFR) && cotizacion.getNombreIncotermComercial().equalsIgnoreCase(Constantes.CFR))
				|| (cotizacion.getNombreIncoterm().equalsIgnoreCase(Constantes.CIF) && cotizacion.getNombreIncotermComercial().equalsIgnoreCase(Constantes.CIF))
				|| (cotizacion.getNombreIncoterm().equalsIgnoreCase(Constantes.CPT) && cotizacion.getNombreIncotermComercial().equalsIgnoreCase(Constantes.CPT))) {
			importeTotal = importeSubTotal - importeFlete - importeSeguro;
			
			nombrePuerto = cotizacion.getNombrePuertoDestino();
			
			descripcionSubTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncoterm() + " " + cotizacion.getNombrePuertoDestino();
						
			if (importeSeguro > 0 || importeFlete > 0) {
				descripcionTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncoterm() + " " + cotizacion.getNombrePuertoOrigen();
			} else {
				descripcionTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncotermComercial() + " " + cotizacion.getNombrePuertoDestino();
			}
		} else {
			importeTotal = importeSubTotal + importeFlete + importeSeguro;
			
			descripcionSubTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncoterm() + " " + cotizacion.getNombrePuertoOrigen();
			
			if (importeSeguro > 0 || importeFlete > 0) {
				nombrePuerto = cotizacion.getNombrePuertoDestino();
				descripcionTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncotermComercial() + " " + cotizacion.getNombrePuertoDestino();
			} else {
				nombrePuerto = cotizacion.getNombrePuertoOrigen();
				descripcionTotal = Constantes.TOTAL + " " + cotizacion.getNombreIncoterm() + " " + cotizacion.getNombrePuertoOrigen();
			}
		}
		
		String descripcionImporteTotal = PedidoUtil.Convertir(String.format("%.2f", importeTotal), true);
		context.setVariable("descripcionImporteTotal", descripcionImporteTotal);

		for (PosicionPedidoTO posicion : posiciones) {
			PosicionPedidoTO posicionBean = new PosicionPedidoTO();

			ProductoTO producto = this.productoExtendedMapper.obtenerProductoxCodigoSap(posicion.getCodigoSAP());
			if (producto != null && producto.getDescripcionJerarquia2() != null) {
				listaMaterial.add(producto.getDescripcionJerarquia2());
			}
			
			cantidadTotal += posicion.getUnidadTotal();
			pesoTotal += posicion.getPesoTeoricoTotal();

			posicionBean.setIdPosicion(posicion.getIdPosicion());
			posicionBean.setCantidadVenta(posicion.getCantidadVenta());
			posicionBean.setDescripcionPedidoMaterial(posicion.getDescripcionPedidoMaterial());
			posicionBean.setPrecioCFR(posicion.getPrecioCFR());
			posicionBean.setPesoTeorico(posicion.getPesoTeorico());
			posicionBean.setPesoTeoricoTotal(posicion.getPesoTeoricoTotal());
			posicionBean.setPrecioUnitario(posicion.getPrecioUnitario());
			posicionBean.setSubtotal(posicion.getSubtotal());
			posicionBean.setUnidadTotal(posicion.getUnidadTotal());
			posicionBean.setPrecioPosicion(posicion.getPrecioPosicion());
			posicionBean.setNroPaquete(posicion.getNroPaquete());
			listaPosicion.add(posicionBean);
		}

		context.setVariable("nombreIncoterm", cotizacion.getNombreIncotermComercial());
		context.setVariable("nombrePuerto", nombrePuerto);
		context.setVariable("descripcionSubTotal", descripcionSubTotal);
		context.setVariable("importeSubTotal", importeSubTotal);
		context.setVariable("descripcionFlete", descripcionFlete);
		context.setVariable("importeFlete", importeFlete);
		context.setVariable("descripcionSeguro", descripcionSeguro);
		context.setVariable("importeSeguro", importeSeguro);
		context.setVariable("descripcionTotal", descripcionTotal);
		context.setVariable("importeTotal", importeTotal);
		
		SortedSet<String> listaMaterialesSinDuplicados = new TreeSet<String>(listaMaterial);

		for (String descripcionMaterial : listaMaterialesSinDuplicados) {
			contMaterial += 1;
			if (contMaterial == 1) {
				descripcionJerarquia += descripcionMaterial;
			} else {
				descripcionJerarquia += ", " + descripcionMaterial;
			}
		}

		context.setVariable("descripcionMaterial", descripcionJerarquia);
		context.setVariable("posiciones", listaPosicion);
		context.setVariable("cantidadTotal", cantidadTotal);
		context.setVariable("pesoTotal", pesoTotal);
					
        return context;
    }
    
    private Context getContextPackingListPdf(ExportacionMaritimoTO documento, List<ReporteContenedorTO> contenedores) {
    	String lugarEmbarque = "", puertoDestino = "";
    	lugarEmbarque = documento.getPuertoOrigen() + " - " + documento.getPaisPuertoOrigen();
    	puertoDestino = documento.getPuertoDestino() + " - " + documento.getPaisPuertoDestino();
    	    	
        Context context = new Context();
        context.setVariable("nombreCliente", documento.getNombreCliente());
        context.setVariable("lugarEmbarque", lugarEmbarque);
        context.setVariable("puertoDestino", puertoDestino);
        context.setVariable("fechaActual", DateUtil.getFormatedNowSlash());
        
        if(contenedores != null && !contenedores.isEmpty()) {
        	for (ReporteContenedorTO contenedor : contenedores) {
        		List<ReporteContenedorMaterialTO> materiales = this.packingListService.reporteContenedorMaterial(contenedor.getId());
            	float pesoTotal = 0;
        		for (ReporteContenedorMaterialTO material : materiales) {
        			pesoTotal += material.getPesoTotal();
        			List<ReportePackingListTO> packinglist = this.packingListService.reportePackingList(contenedor.getId(), material.getCodigo());
					material.setPackinglist(packinglist);
				}
        		contenedor.setPosiciones(materiales);
            	contenedor.setPesoTotal(pesoTotal);
			}
        }
        
        context.setVariable("contenedores", contenedores);
        
        return context;
    }
    
    private Context getContextPackingListCargaSueltaPdf(ExportacionMaritimoTO documento, List<PackingListCargaSueltaTO> posiciones, List<ExportacionEtiquetaTO> etiquetas) {
    	String direccionShipper = "", direccionConsignatario = "", puertoEmbarque = "", puertoDescarga = "";
    	float totalPesoTeorico = 0, totalPesoNeto = 0, totalPesoBruto = 0;
    	int totalPaquetes = 0, totalPiezas = 0;
    	boolean mostrarPesoTeorico = true;
    	
    	List<ExportacionEtiquetaTO> etiquetasArriba = new ArrayList<>();
    	List<ExportacionEtiquetaTO> etiquetasAbajo = new ArrayList<>();
    	
    	if(documento.getDireccionShipper() != null) {
    		direccionShipper = documento.getDireccionShipper().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	
    	if(documento.getDireccionConsignatario() != null) {
    		direccionConsignatario = documento.getDireccionConsignatario().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	
    	puertoEmbarque = documento.getPuertoOrigen() + ", " + documento.getPaisPuertoOrigen();
    	puertoDescarga = documento.getPuertoDestino() + ", " + documento.getPaisPuertoDestino();
    	
    	for (ExportacionEtiquetaTO etiqueta : etiquetas) {
    		String contenido = etiqueta.getContenido().replace("\\n", "<br/>").replace("\n", "<br/>");
			etiqueta.setContenido(contenido);
			if(etiqueta.getPosicion() == Constantes.ETIQUETA_ARRIBA) {
				etiquetasArriba.add(etiqueta);
			} else {
				etiquetasAbajo.add(etiqueta);
			}
		}
    	    	
        Context context = new Context();
        context.setVariable("shipper", documento.getShipper());
        context.setVariable("direccionShipper", direccionShipper);
        context.setVariable("consignatario", documento.getConsignatario());
        context.setVariable("direccionConsignatario", direccionConsignatario);
        context.setVariable("fechaFacturaSap", DateUtil.getFormatedLarge(documento.getFechaFactura()).toUpperCase());
        context.setVariable("nave", documento.getNave());
        context.setVariable("puertoEmbarque", puertoEmbarque);
        context.setVariable("puertoDescarga", puertoDescarga);
        
        for (PackingListCargaSueltaTO posicion : posiciones) {
			totalPesoTeorico += posicion.getPesoTeorico().floatValue();
			totalPesoNeto += posicion.getPesoNeto().floatValue();
			totalPesoBruto += posicion.getPesoBruto().floatValue();
			totalPaquetes += posicion.getPaquetes();
			totalPiezas += posicion.getPiezas();
			
			if(posicion.getPesoTeorico().floatValue() == 0) {
				mostrarPesoTeorico = false;
			}
		}
        
        context.setVariable("etiquetasArriba", etiquetasArriba);
		context.setVariable("etiquetasAbajo", etiquetasAbajo);
        context.setVariable("posiciones", posiciones);
        context.setVariable("totalPesoTeorico", totalPesoTeorico);
        context.setVariable("totalPesoNeto", totalPesoNeto);
        context.setVariable("totalPesoBruto", totalPesoBruto);
        context.setVariable("totalPaquetes", totalPaquetes);
        context.setVariable("totalPiezas", totalPiezas);
        context.setVariable("mostrarPesoTeorico", mostrarPesoTeorico);
        return context;
    }
    
    private Context getContextPackingListAereoPdf(ExportacionMaritimoTO documento, List<PackingListAereoTO> posiciones) {
    	String direccionShipper = "", direccionConsignatario = "";
    	
    	if(documento.getDireccionShipper() != null) {
    		direccionShipper = documento.getDireccionShipper().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	
    	if(documento.getDireccionConsignatario() != null) {
    		direccionConsignatario = documento.getDireccionConsignatario().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	
    	String descripcionMaterial = "";
    	if(posiciones != null && posiciones.size() > 0) {
    		descripcionMaterial = posiciones.get(0).getMaterial();
    	}
    	
        Context context = new Context();
        context.setVariable("shipper", documento.getShipper());
        context.setVariable("direccionShipper", direccionShipper);
        context.setVariable("consignatario", documento.getConsignatario());
        context.setVariable("direccionConsignatario", direccionConsignatario);
        context.setVariable("fecha", DateUtil.getFormatedLarge());
        context.setVariable("descripcionMaterial", descripcionMaterial);
        context.setVariable("posiciones", posiciones);
        return context;
    }
    
    private Context getContextFacturaComercialPdf(RequestReporteFacturaComercialTO request) {
    	String shipper = "", direccionConsignatario = "", emitidoEn = "", descripcionImporteTotal = "";
    	float subTotal = 0, importeTotal = 0,pesoTotal = 0, cantidadTotal = 0;
    	if(request.getDireccionShipper() != null) {
    		shipper = request.getDireccionShipper().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	if(request.getDireccionConsignatario() != null) {
    		direccionConsignatario = request.getDireccionConsignatario().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	if(request.getEmitidoEn() != null) {
    		emitidoEn = request.getEmitidoEn().replace("\\,", ",");
    	}
		
		Context context = new Context();        
		context.setVariable("direccionShipper", shipper);
		context.setVariable("consignatario", request.getConsignatario());
		context.setVariable("direccionConsignatario", direccionConsignatario);
		context.setVariable("cliente", request.getCliente());
		context.setVariable("puertoEmbarque", request.getPuertoEmbarque());
		context.setVariable("navio", request.getNavio());
		context.setVariable("puertoDescarga", request.getPuertoDescarga());
		context.setVariable("booking", request.getBooking());
		context.setVariable("factura", request.getFactura());
		context.setVariable("textoFecha", request.getTextoFecha());
		context.setVariable("emitidoEn", emitidoEn);
		context.setVariable("condicionFlete", request.getCondicionFlete());
		context.setVariable("formaPago", request.getFormaPago());
		context.setVariable("paisOrigen", request.getPaisOrigen());
		context.setVariable("referencia", request.getReferencia());
		context.setVariable("nombreUsuario", request.getNombreUsuario());
		context.setVariable("unidadMedida", request.getUnidadMedida());
		
		//Buscar pesos del PackingList
		for (RequestPosicionFacturaTO posicion : request.getPosiciones()) {
			String componenteTexto = "";
			float pesoComex = this.reporteExtendedMapper.obtenerPesoPackingList(posicion.getId());
			if(posicion.getComponenteTexto() != null) {
				componenteTexto = posicion.getComponenteTexto().replace("\n", "<br/>");
				posicion.setComponenteTexto(componenteTexto);
			}
			posicion.setPesoNeto(pesoComex);
			
			if(request.isPesoPackingList()) {
				if(request.getUnidadMedida().equalsIgnoreCase(Constantes.KG)) {
					posicion.setCantidad(pesoComex * Constantes.TON_A_KG);
				} else {
					posicion.setCantidad(pesoComex);
				}
				posicion.setImporte(posicion.getCantidad() * posicion.getPrecio());
			}
			
			cantidadTotal += posicion.getCantidad();
			pesoTotal += pesoComex;
			subTotal += posicion.getImporte();
		}

		if(request.getIncotermComercial().equalsIgnoreCase(Constantes.CFR) && request.getIncoterm().equalsIgnoreCase(Constantes.CFR)) {
			importeTotal = subTotal - request.getFlete() - request.getSeguro();
		} else if(request.getIncotermComercial().equalsIgnoreCase(Constantes.CIF) && request.getIncoterm().equalsIgnoreCase(Constantes.CIF)) {
			importeTotal = subTotal - request.getFlete() - request.getSeguro();
		} else {
			importeTotal = subTotal + request.getFlete() + request.getSeguro();
		}
		
		int numeroTotales = request.getTotales().size();
		request.getTotales().get(0).setImporte(subTotal);
		request.getTotales().get(numeroTotales - 1).setImporte(importeTotal);
		
		if(request.getDescripcionTotal() == null || request.getDescripcionTotal().equals("")) {
			descripcionImporteTotal = PedidoUtil.Convertir(String.format("%.2f", importeTotal), true);
		} else {
			descripcionImporteTotal = request.getDescripcionTotal();
		}
		context.setVariable("nombreMonto", descripcionImporteTotal);
		context.setVariable("pesoTotal", pesoTotal);
		context.setVariable("cantidadTotal", cantidadTotal);
		context.setVariable("posiciones", request.getPosiciones());
		context.setVariable("totales", request.getTotales());
					
        return context;
    }
    
    private Context getContextFacturaComercialCargaSueltaPdf(RequestReporteFacturaComercialTO request, List<PackingListCargaSueltaTO> packingList, List<ExportacionEtiquetaTO> etiquetas, int tipoFactura) {
    	String shipper = "", direccionConsignatario = "", emitidoEn = "", descripcionImporteTotal = "";
    	float subTotal = 0, importeTotal = 0;
    	float totalPesoTeorico = 0, totalPesoReal = 0;
    	int totalPaquetes = 0, totalPiezas = 0;
    	
    	List<ExportacionEtiquetaTO> etiquetasArriba = new ArrayList<>();
    	List<ExportacionEtiquetaTO> etiquetasAbajo = new ArrayList<>();
    	
    	if(request.getDireccionShipper() != null) {
    		shipper = request.getDireccionShipper().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	if(request.getDireccionConsignatario() != null) {
    		direccionConsignatario = request.getDireccionConsignatario().replace("\\,", ",").replace("\\n", "<br/>").replace("\n", "<br/>");
    	}
    	if(request.getEmitidoEn() != null) {
    		emitidoEn = request.getEmitidoEn().replace("\\,", ",");
    	}
		
    	for (ExportacionEtiquetaTO etiqueta : etiquetas) {
    		String contenido = etiqueta.getContenido().replace("\\n", "<br/>").replace("\n", "<br/>");
			etiqueta.setContenido(contenido);
			if(etiqueta.getPosicion() == Constantes.ETIQUETA_ARRIBA) {
				etiquetasArriba.add(etiqueta);
			} else {
				etiquetasAbajo.add(etiqueta);
			}
		}
    	
    	String nombreUsuario = "";
    	String cargoUsuario = reporteExtendedMapper.obtenerCargoUsuario(request.getCodigoPersonal());
    	
    	if(request.getNombreUsuario() != null) {
    		nombreUsuario = request.getNombreUsuario().toUpperCase();
    	}
    	
    	if(cargoUsuario != null) {
    		cargoUsuario = cargoUsuario.toUpperCase();
    	}
    	
		Context context = new Context();        
		context.setVariable("direccionShipper", shipper);
		context.setVariable("consignatario", request.getConsignatario());
		context.setVariable("direccionConsignatario", direccionConsignatario);
		context.setVariable("cliente", request.getCliente());
		context.setVariable("puertoEmbarque", request.getPuertoEmbarque());
		context.setVariable("navio", request.getNavio());
		context.setVariable("puertoDescarga", request.getPuertoDescarga());
		context.setVariable("booking", request.getBooking());
		context.setVariable("factura", request.getFactura());
		context.setVariable("textoFecha", request.getTextoFecha());
		context.setVariable("emitidoEn", emitidoEn);
		context.setVariable("condicionFlete", request.getCondicionFlete());
		context.setVariable("formaPago", request.getFormaPago());
		context.setVariable("paisOrigen", request.getPaisOrigen());
		context.setVariable("referencia", request.getReferencia());
		context.setVariable("nombreUsuario", nombreUsuario);
		context.setVariable("cargoUsuario", cargoUsuario);
		context.setVariable("unidadMedida", request.getUnidadMedida());
		
		//Buscar pesos del PackingList
		for (RequestPosicionFacturaTO posicion : request.getPosiciones()) {
			float pesoTeorico = 0, pesoReal = 0, importe = 0;
			int paquetes = 0, piezas = 0; 
			
			List<PackingListCargaSueltaTO> packingPosicion = packingList.stream().filter(i -> i.getCodigo().equalsIgnoreCase(posicion.getCodigo())).collect(Collectors.toList());
			
			if(packingPosicion != null && !packingPosicion.isEmpty()) {
				for(PackingListCargaSueltaTO item : packingPosicion) {
					if(item.getPesoTeorico() == null) {
						item.setPesoTeorico(new BigDecimal(0));
					}
					pesoTeorico += item.getPesoTeorico().floatValue();
					pesoReal += item.getPesoNeto().floatValue();
					paquetes += item.getPaquetes();
					piezas += item.getPiezas();
				}
				
				if(tipoFactura == Constantes.FACT_COM_PRECIO_SAP) {
					importe = piezas * posicion.getPrecio();
				} else if(tipoFactura == Constantes.FACT_COM_PESO_TEORICO) {
					importe = pesoTeorico * posicion.getPrecio();
				} else {
					importe = pesoReal * posicion.getPrecio();
				}
	
				totalPesoTeorico += pesoTeorico;
				totalPesoReal += pesoReal;
				totalPaquetes += paquetes;
				totalPiezas += piezas;
				subTotal += importe;
				
				posicion.setPesoTeorico(pesoTeorico);
				posicion.setPesoReal(pesoReal);
				posicion.setPaquetes(paquetes);
				posicion.setPiezas(piezas);
				posicion.setImporte(importe);
			}
		}

		if(request.getIncotermComercial().equalsIgnoreCase(Constantes.CFR) && request.getIncoterm().equalsIgnoreCase(Constantes.CFR)) {
			importeTotal = subTotal - request.getFlete() - request.getSeguro();
		} else if(request.getIncotermComercial().equalsIgnoreCase(Constantes.CIF) && request.getIncoterm().equalsIgnoreCase(Constantes.CIF)) {
			importeTotal = subTotal - request.getFlete() - request.getSeguro();
		} else {
			importeTotal = subTotal + request.getFlete() + request.getSeguro();
		}
		
		int numeroTotales = request.getTotales().size();
		request.getTotales().get(0).setImporte(subTotal);
		request.getTotales().get(numeroTotales - 1).setImporte(importeTotal);
		
		if(request.getDescripcionTotal() == null || request.getDescripcionTotal().equals("")) {
			descripcionImporteTotal = PedidoUtil.Convertir(String.format("%.2f", importeTotal), true);
		} else {
			descripcionImporteTotal = request.getDescripcionTotal();
		}
		
		context.setVariable("descripcionImporteTotal", descripcionImporteTotal);
		context.setVariable("posiciones", request.getPosiciones());
		context.setVariable("etiquetasArriba", etiquetasArriba);
		context.setVariable("etiquetasAbajo", etiquetasAbajo);
		
		context.setVariable("totalPesoTeorico", totalPesoTeorico);
		context.setVariable("totalPesoReal", totalPesoReal);
		context.setVariable("totalPaquetes", totalPaquetes);
		context.setVariable("totalPiezas", totalPiezas);
		context.setVariable("totalImporte", subTotal);
		
		context.setVariable("totales", request.getTotales());
		
        return context;
    }
    
    private Context getContextExportacionMaritimaPdf(RequestReporteFacturaTO request) {
    	String direccionShipper = "", direccionConsignatario = "", direccionNotificante = "";
    	
    	if(request.getDireccionShipper() != null) direccionShipper = request.getDireccionShipper().replace("\\,", ",").replace("\n", "<br/>");
		if(request.getDireccionConsignatario() != null) direccionConsignatario = request.getDireccionConsignatario().replace("\\,", ",").replace("\n", "");
		if(request.getDireccionNotificante() != null) direccionNotificante = request.getDireccionNotificante().replace("\\,", ",").replace("\n", "");
				
		Context context = new Context();
		context.setVariable("booking", request.getBooking());
		context.setVariable("agenteAduana", request.getAgenteAduana());
		context.setVariable("shipper", request.getShipper());
		context.setVariable("direccionShipper", direccionShipper);
		context.setVariable("consignatario", request.getConsignatario());
		context.setVariable("direccionConsignatario", direccionConsignatario);
		context.setVariable("notificante", request.getNotificante());
		context.setVariable("direccionNotificante", direccionNotificante);
		context.setVariable("puertoOrigen", request.getPuertoOrigen());
		context.setVariable("puertoDestino", request.getPuertoDestino());
		context.setVariable("fechaCarguio", request.getFechaCarguio());
		context.setVariable("tipoTransporte", request.getTipoTransporte());
		context.setVariable("motonave", request.getMotonave());
		context.setVariable("regimen", request.getRegimen());
		context.setVariable("etiquetaValor", request.getEtiquetaValor());
		context.setVariable("valorFobTotal", request.getValorFOBTotal());
		context.setVariable("flete", request.getFleteTotal());
		context.setVariable("seguro", request.getSeguroTotal());
		context.setVariable("valorCfrTotal", request.getValorCFRTotal());
		
		context.setVariable("posiciones", request.getPosiciones());
					
        return context;
    }
}
