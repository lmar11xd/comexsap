
package com.caasa.soap.exportaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_crea_pedidos_req complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_crea_pedidos_req"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="testrun" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cabecera" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="org_ventas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="canal_dist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="grupo_vend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="oficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="lista_precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cond_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cond_exp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_pref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_fact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ord_compra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="mot_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="grupo1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="bloque_entr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="zona_ventas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="grupo_cli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="centro_sum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="incoterm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="incoterm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_occli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_descObr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_despa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_cont" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="factura_financiera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Detalle" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="pos_sup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="almacen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tdes_exs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tdes_fsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tdes_lim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_nc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_lote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_dmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num_pmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="mot_rech" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="interlocutor" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="cod_inte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_int" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="condiciones" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="cond_pos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cond_pr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cond_bp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cond_val" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="koein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="kpein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="kmein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="textos" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="id_texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="repartos" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="posnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="fecha_prefe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_crea_pedidos_req", propOrder = {
    "testrun",
    "cabecera",
    "detalle",
    "interlocutor",
    "condiciones",
    "textos",
    "repartos"
})
public class DTCreaPedidosReq {

    protected String testrun;
    @XmlElement(required = true)
    protected List<DTCreaPedidosReq.Cabecera> cabecera;
    @XmlElement(name = "Detalle", required = true)
    protected List<DTCreaPedidosReq.Detalle> detalle;
    @XmlElement(required = true)
    protected List<DTCreaPedidosReq.Interlocutor> interlocutor;
    @XmlElement(required = true)
    protected List<DTCreaPedidosReq.Condiciones> condiciones;
    @XmlElement(required = true)
    protected List<DTCreaPedidosReq.Textos> textos;
    @XmlElement(required = true)
    protected List<DTCreaPedidosReq.Repartos> repartos;

    /**
     * Obtiene el valor de la propiedad testrun.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestrun() {
        return testrun;
    }

    /**
     * Define el valor de la propiedad testrun.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestrun(String value) {
        this.testrun = value;
    }

    /**
     * Gets the value of the cabecera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cabecera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCabecera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Cabecera }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Cabecera> getCabecera() {
        if (cabecera == null) {
            cabecera = new ArrayList<DTCreaPedidosReq.Cabecera>();
        }
        return this.cabecera;
    }

    /**
     * Gets the value of the detalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Detalle }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<DTCreaPedidosReq.Detalle>();
        }
        return this.detalle;
    }

    /**
     * Gets the value of the interlocutor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interlocutor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterlocutor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Interlocutor }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Interlocutor> getInterlocutor() {
        if (interlocutor == null) {
            interlocutor = new ArrayList<DTCreaPedidosReq.Interlocutor>();
        }
        return this.interlocutor;
    }

    /**
     * Gets the value of the condiciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condiciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondiciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Condiciones }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Condiciones> getCondiciones() {
        if (condiciones == null) {
            condiciones = new ArrayList<DTCreaPedidosReq.Condiciones>();
        }
        return this.condiciones;
    }

    /**
     * Gets the value of the textos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Textos }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Textos> getTextos() {
        if (textos == null) {
            textos = new ArrayList<DTCreaPedidosReq.Textos>();
        }
        return this.textos;
    }

    /**
     * Gets the value of the repartos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the repartos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRepartos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosReq.Repartos }
     * 
     * 
     */
    public List<DTCreaPedidosReq.Repartos> getRepartos() {
        if (repartos == null) {
            repartos = new ArrayList<DTCreaPedidosReq.Repartos>();
        }
        return this.repartos;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="org_ventas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="canal_dist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="moneda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="grupo_vend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="oficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="lista_precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cond_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cond_exp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="grupo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_pref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_fact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_doc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ord_compra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="mot_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cotizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="grupo1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="bloque_entr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="zona_ventas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="grupo_cli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="centro_sum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="incoterm1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="incoterm2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_occli" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_descObr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_despa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_cont" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="factura_financiera" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orgVentas",
        "canalDist",
        "sector",
        "tipoDoc",
        "moneda",
        "fechaPrecio",
        "grupoVend",
        "oficina",
        "listaPrecio",
        "condPago",
        "condExp",
        "grupo",
        "fechaPref",
        "fechaFact",
        "fechaDoc",
        "ordCompra",
        "motPedido",
        "cotizacion",
        "grupo1",
        "bloqueEntr",
        "zonaVentas",
        "grupoCli",
        "tipoPago",
        "centroSum",
        "incoterm1",
        "incoterm2",
        "fechaOccli",
        "numDescObr",
        "numDespa",
        "numCont",
        "facturaFinanciera"
    })
    public static class Cabecera {

        @XmlElement(name = "org_ventas")
        protected String orgVentas;
        @XmlElement(name = "canal_dist")
        protected String canalDist;
        protected String sector;
        @XmlElement(name = "tipo_doc")
        protected String tipoDoc;
        protected String moneda;
        @XmlElement(name = "fecha_precio")
        protected String fechaPrecio;
        @XmlElement(name = "grupo_vend")
        protected String grupoVend;
        protected String oficina;
        @XmlElement(name = "lista_precio")
        protected String listaPrecio;
        @XmlElement(name = "cond_pago")
        protected String condPago;
        @XmlElement(name = "cond_exp")
        protected String condExp;
        protected String grupo;
        @XmlElement(name = "fecha_pref")
        protected String fechaPref;
        @XmlElement(name = "fecha_fact")
        protected String fechaFact;
        @XmlElement(name = "fecha_doc")
        protected String fechaDoc;
        @XmlElement(name = "ord_compra")
        protected String ordCompra;
        @XmlElement(name = "mot_pedido")
        protected String motPedido;
        protected String cotizacion;
        protected String grupo1;
        @XmlElement(name = "bloque_entr")
        protected String bloqueEntr;
        @XmlElement(name = "zona_ventas")
        protected String zonaVentas;
        @XmlElement(name = "grupo_cli")
        protected String grupoCli;
        @XmlElement(name = "tipo_pago")
        protected String tipoPago;
        @XmlElement(name = "centro_sum")
        protected String centroSum;
        protected String incoterm1;
        protected String incoterm2;
        @XmlElement(name = "fecha_occli")
        protected String fechaOccli;
        @XmlElement(name = "num_descObr")
        protected String numDescObr;
        @XmlElement(name = "num_despa")
        protected String numDespa;
        @XmlElement(name = "num_cont")
        protected String numCont;
        @XmlElement(name = "factura_financiera")
        protected String facturaFinanciera;

        /**
         * Obtiene el valor de la propiedad orgVentas.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrgVentas() {
            return orgVentas;
        }

        /**
         * Define el valor de la propiedad orgVentas.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrgVentas(String value) {
            this.orgVentas = value;
        }

        /**
         * Obtiene el valor de la propiedad canalDist.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCanalDist() {
            return canalDist;
        }

        /**
         * Define el valor de la propiedad canalDist.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCanalDist(String value) {
            this.canalDist = value;
        }

        /**
         * Obtiene el valor de la propiedad sector.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSector() {
            return sector;
        }

        /**
         * Define el valor de la propiedad sector.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSector(String value) {
            this.sector = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoDoc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoDoc() {
            return tipoDoc;
        }

        /**
         * Define el valor de la propiedad tipoDoc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoDoc(String value) {
            this.tipoDoc = value;
        }

        /**
         * Obtiene el valor de la propiedad moneda.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMoneda() {
            return moneda;
        }

        /**
         * Define el valor de la propiedad moneda.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMoneda(String value) {
            this.moneda = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaPrecio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaPrecio() {
            return fechaPrecio;
        }

        /**
         * Define el valor de la propiedad fechaPrecio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaPrecio(String value) {
            this.fechaPrecio = value;
        }

        /**
         * Obtiene el valor de la propiedad grupoVend.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrupoVend() {
            return grupoVend;
        }

        /**
         * Define el valor de la propiedad grupoVend.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrupoVend(String value) {
            this.grupoVend = value;
        }

        /**
         * Obtiene el valor de la propiedad oficina.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOficina() {
            return oficina;
        }

        /**
         * Define el valor de la propiedad oficina.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOficina(String value) {
            this.oficina = value;
        }

        /**
         * Obtiene el valor de la propiedad listaPrecio.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getListaPrecio() {
            return listaPrecio;
        }

        /**
         * Define el valor de la propiedad listaPrecio.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setListaPrecio(String value) {
            this.listaPrecio = value;
        }

        /**
         * Obtiene el valor de la propiedad condPago.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondPago() {
            return condPago;
        }

        /**
         * Define el valor de la propiedad condPago.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondPago(String value) {
            this.condPago = value;
        }

        /**
         * Obtiene el valor de la propiedad condExp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondExp() {
            return condExp;
        }

        /**
         * Define el valor de la propiedad condExp.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondExp(String value) {
            this.condExp = value;
        }

        /**
         * Obtiene el valor de la propiedad grupo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrupo() {
            return grupo;
        }

        /**
         * Define el valor de la propiedad grupo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrupo(String value) {
            this.grupo = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaPref.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaPref() {
            return fechaPref;
        }

        /**
         * Define el valor de la propiedad fechaPref.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaPref(String value) {
            this.fechaPref = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaFact.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaFact() {
            return fechaFact;
        }

        /**
         * Define el valor de la propiedad fechaFact.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaFact(String value) {
            this.fechaFact = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaDoc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaDoc() {
            return fechaDoc;
        }

        /**
         * Define el valor de la propiedad fechaDoc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaDoc(String value) {
            this.fechaDoc = value;
        }

        /**
         * Obtiene el valor de la propiedad ordCompra.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrdCompra() {
            return ordCompra;
        }

        /**
         * Define el valor de la propiedad ordCompra.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrdCompra(String value) {
            this.ordCompra = value;
        }

        /**
         * Obtiene el valor de la propiedad motPedido.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMotPedido() {
            return motPedido;
        }

        /**
         * Define el valor de la propiedad motPedido.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMotPedido(String value) {
            this.motPedido = value;
        }

        /**
         * Obtiene el valor de la propiedad cotizacion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCotizacion() {
            return cotizacion;
        }

        /**
         * Define el valor de la propiedad cotizacion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCotizacion(String value) {
            this.cotizacion = value;
        }

        /**
         * Obtiene el valor de la propiedad grupo1.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrupo1() {
            return grupo1;
        }

        /**
         * Define el valor de la propiedad grupo1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrupo1(String value) {
            this.grupo1 = value;
        }

        /**
         * Obtiene el valor de la propiedad bloqueEntr.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBloqueEntr() {
            return bloqueEntr;
        }

        /**
         * Define el valor de la propiedad bloqueEntr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBloqueEntr(String value) {
            this.bloqueEntr = value;
        }

        /**
         * Obtiene el valor de la propiedad zonaVentas.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZonaVentas() {
            return zonaVentas;
        }

        /**
         * Define el valor de la propiedad zonaVentas.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZonaVentas(String value) {
            this.zonaVentas = value;
        }

        /**
         * Obtiene el valor de la propiedad grupoCli.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrupoCli() {
            return grupoCli;
        }

        /**
         * Define el valor de la propiedad grupoCli.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrupoCli(String value) {
            this.grupoCli = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoPago.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoPago() {
            return tipoPago;
        }

        /**
         * Define el valor de la propiedad tipoPago.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoPago(String value) {
            this.tipoPago = value;
        }

        /**
         * Obtiene el valor de la propiedad centroSum.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCentroSum() {
            return centroSum;
        }

        /**
         * Define el valor de la propiedad centroSum.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCentroSum(String value) {
            this.centroSum = value;
        }

        /**
         * Obtiene el valor de la propiedad incoterm1.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIncoterm1() {
            return incoterm1;
        }

        /**
         * Define el valor de la propiedad incoterm1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIncoterm1(String value) {
            this.incoterm1 = value;
        }

        /**
         * Obtiene el valor de la propiedad incoterm2.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIncoterm2() {
            return incoterm2;
        }

        /**
         * Define el valor de la propiedad incoterm2.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIncoterm2(String value) {
            this.incoterm2 = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaOccli.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaOccli() {
            return fechaOccli;
        }

        /**
         * Define el valor de la propiedad fechaOccli.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaOccli(String value) {
            this.fechaOccli = value;
        }

        /**
         * Obtiene el valor de la propiedad numDescObr.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumDescObr() {
            return numDescObr;
        }

        /**
         * Define el valor de la propiedad numDescObr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumDescObr(String value) {
            this.numDescObr = value;
        }

        /**
         * Obtiene el valor de la propiedad numDespa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumDespa() {
            return numDespa;
        }

        /**
         * Define el valor de la propiedad numDespa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumDespa(String value) {
            this.numDespa = value;
        }

        /**
         * Obtiene el valor de la propiedad numCont.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumCont() {
            return numCont;
        }

        /**
         * Define el valor de la propiedad numCont.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumCont(String value) {
            this.numCont = value;
        }

        /**
         * Obtiene el valor de la propiedad facturaFinanciera.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFacturaFinanciera() {
            return facturaFinanciera;
        }

        /**
         * Define el valor de la propiedad facturaFinanciera.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFacturaFinanciera(String value) {
            this.facturaFinanciera = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="cond_pos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cond_pr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cond_bp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cond_val" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="koein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="kpein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="kmein" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "condPos",
        "condPr",
        "condBp",
        "condVal",
        "koein",
        "kpein",
        "kmein"
    })
    public static class Condiciones {

        @XmlElement(name = "cond_pos")
        protected String condPos;
        @XmlElement(name = "cond_pr")
        protected String condPr;
        @XmlElement(name = "cond_bp")
        protected String condBp;
        @XmlElement(name = "cond_val")
        protected String condVal;
        protected String koein;
        protected String kpein;
        protected String kmein;

        /**
         * Obtiene el valor de la propiedad condPos.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondPos() {
            return condPos;
        }

        /**
         * Define el valor de la propiedad condPos.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondPos(String value) {
            this.condPos = value;
        }

        /**
         * Obtiene el valor de la propiedad condPr.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondPr() {
            return condPr;
        }

        /**
         * Define el valor de la propiedad condPr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondPr(String value) {
            this.condPr = value;
        }

        /**
         * Obtiene el valor de la propiedad condBp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondBp() {
            return condBp;
        }

        /**
         * Define el valor de la propiedad condBp.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondBp(String value) {
            this.condBp = value;
        }

        /**
         * Obtiene el valor de la propiedad condVal.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCondVal() {
            return condVal;
        }

        /**
         * Define el valor de la propiedad condVal.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCondVal(String value) {
            this.condVal = value;
        }

        /**
         * Obtiene el valor de la propiedad koein.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKoein() {
            return koein;
        }

        /**
         * Define el valor de la propiedad koein.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKoein(String value) {
            this.koein = value;
        }

        /**
         * Obtiene el valor de la propiedad kpein.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKpein() {
            return kpein;
        }

        /**
         * Define el valor de la propiedad kpein.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKpein(String value) {
            this.kpein = value;
        }

        /**
         * Obtiene el valor de la propiedad kmein.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKmein() {
            return kmein;
        }

        /**
         * Define el valor de la propiedad kmein.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKmein(String value) {
            this.kmein = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="pos_sup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="material" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="centro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="almacen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="unidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tdes_exs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tdes_fsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tdes_lim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_nc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_lote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_dmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num_pmo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="mot_rech" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "posicion",
        "posSup",
        "material",
        "cantidad",
        "centro",
        "almacen",
        "unidad",
        "ruta",
        "tdesExs",
        "tdesFsu",
        "tdesLim",
        "tipoNc",
        "numLote",
        "numDmo",
        "numPmo",
        "motRech",
        "tipoPosicion"
    })
    public static class Detalle {

        protected String posicion;
        @XmlElement(name = "pos_sup")
        protected String posSup;
        protected String material;
        protected String cantidad;
        protected String centro;
        protected String almacen;
        protected String unidad;
        protected String ruta;
        @XmlElement(name = "tdes_exs")
        protected String tdesExs;
        @XmlElement(name = "tdes_fsu")
        protected String tdesFsu;
        @XmlElement(name = "tdes_lim")
        protected String tdesLim;
        @XmlElement(name = "tipo_nc")
        protected String tipoNc;
        @XmlElement(name = "num_lote")
        protected String numLote;
        @XmlElement(name = "num_dmo")
        protected String numDmo;
        @XmlElement(name = "num_pmo")
        protected String numPmo;
        @XmlElement(name = "mot_rech")
        protected String motRech;
        @XmlElement(name = "tipo_posicion")
        protected String tipoPosicion;

        /**
         * Obtiene el valor de la propiedad posicion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPosicion() {
            return posicion;
        }

        /**
         * Define el valor de la propiedad posicion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPosicion(String value) {
            this.posicion = value;
        }

        /**
         * Obtiene el valor de la propiedad posSup.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPosSup() {
            return posSup;
        }

        /**
         * Define el valor de la propiedad posSup.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPosSup(String value) {
            this.posSup = value;
        }

        /**
         * Obtiene el valor de la propiedad material.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMaterial() {
            return material;
        }

        /**
         * Define el valor de la propiedad material.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMaterial(String value) {
            this.material = value;
        }

        /**
         * Obtiene el valor de la propiedad cantidad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCantidad() {
            return cantidad;
        }

        /**
         * Define el valor de la propiedad cantidad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCantidad(String value) {
            this.cantidad = value;
        }

        /**
         * Obtiene el valor de la propiedad centro.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCentro() {
            return centro;
        }

        /**
         * Define el valor de la propiedad centro.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCentro(String value) {
            this.centro = value;
        }

        /**
         * Obtiene el valor de la propiedad almacen.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlmacen() {
            return almacen;
        }

        /**
         * Define el valor de la propiedad almacen.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlmacen(String value) {
            this.almacen = value;
        }

        /**
         * Obtiene el valor de la propiedad unidad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnidad() {
            return unidad;
        }

        /**
         * Define el valor de la propiedad unidad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnidad(String value) {
            this.unidad = value;
        }

        /**
         * Obtiene el valor de la propiedad ruta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRuta() {
            return ruta;
        }

        /**
         * Define el valor de la propiedad ruta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRuta(String value) {
            this.ruta = value;
        }

        /**
         * Obtiene el valor de la propiedad tdesExs.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTdesExs() {
            return tdesExs;
        }

        /**
         * Define el valor de la propiedad tdesExs.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTdesExs(String value) {
            this.tdesExs = value;
        }

        /**
         * Obtiene el valor de la propiedad tdesFsu.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTdesFsu() {
            return tdesFsu;
        }

        /**
         * Define el valor de la propiedad tdesFsu.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTdesFsu(String value) {
            this.tdesFsu = value;
        }

        /**
         * Obtiene el valor de la propiedad tdesLim.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTdesLim() {
            return tdesLim;
        }

        /**
         * Define el valor de la propiedad tdesLim.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTdesLim(String value) {
            this.tdesLim = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoNc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoNc() {
            return tipoNc;
        }

        /**
         * Define el valor de la propiedad tipoNc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoNc(String value) {
            this.tipoNc = value;
        }

        /**
         * Obtiene el valor de la propiedad numLote.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumLote() {
            return numLote;
        }

        /**
         * Define el valor de la propiedad numLote.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumLote(String value) {
            this.numLote = value;
        }

        /**
         * Obtiene el valor de la propiedad numDmo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumDmo() {
            return numDmo;
        }

        /**
         * Define el valor de la propiedad numDmo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumDmo(String value) {
            this.numDmo = value;
        }

        /**
         * Obtiene el valor de la propiedad numPmo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumPmo() {
            return numPmo;
        }

        /**
         * Define el valor de la propiedad numPmo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumPmo(String value) {
            this.numPmo = value;
        }

        /**
         * Obtiene el valor de la propiedad motRech.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMotRech() {
            return motRech;
        }

        /**
         * Define el valor de la propiedad motRech.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMotRech(String value) {
            this.motRech = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoPosicion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoPosicion() {
            return tipoPosicion;
        }

        /**
         * Define el valor de la propiedad tipoPosicion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoPosicion(String value) {
            this.tipoPosicion = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="cod_inte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_int" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "codInte",
        "tipoInt"
    })
    public static class Interlocutor {

        @XmlElement(name = "cod_inte")
        protected String codInte;
        @XmlElement(name = "tipo_int")
        protected String tipoInt;

        /**
         * Obtiene el valor de la propiedad codInte.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodInte() {
            return codInte;
        }

        /**
         * Define el valor de la propiedad codInte.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodInte(String value) {
            this.codInte = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoInt.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoInt() {
            return tipoInt;
        }

        /**
         * Define el valor de la propiedad tipoInt.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoInt(String value) {
            this.tipoInt = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="posnr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="fecha_prefe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "posnr",
        "fechaPrefe",
        "cantidad"
    })
    public static class Repartos {

        protected String posnr;
        @XmlElement(name = "fecha_prefe")
        protected String fechaPrefe;
        protected String cantidad;

        /**
         * Obtiene el valor de la propiedad posnr.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPosnr() {
            return posnr;
        }

        /**
         * Define el valor de la propiedad posnr.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPosnr(String value) {
            this.posnr = value;
        }

        /**
         * Obtiene el valor de la propiedad fechaPrefe.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFechaPrefe() {
            return fechaPrefe;
        }

        /**
         * Define el valor de la propiedad fechaPrefe.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFechaPrefe(String value) {
            this.fechaPrefe = value;
        }

        /**
         * Obtiene el valor de la propiedad cantidad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCantidad() {
            return cantidad;
        }

        /**
         * Define el valor de la propiedad cantidad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCantidad(String value) {
            this.cantidad = value;
        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="id_texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="texto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idTexto",
        "texto"
    })
    public static class Textos {

        @XmlElement(name = "id_texto")
        protected String idTexto;
        protected String texto;

        /**
         * Obtiene el valor de la propiedad idTexto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdTexto() {
            return idTexto;
        }

        /**
         * Define el valor de la propiedad idTexto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdTexto(String value) {
            this.idTexto = value;
        }

        /**
         * Obtiene el valor de la propiedad texto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTexto() {
            return texto;
        }

        /**
         * Define el valor de la propiedad texto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTexto(String value) {
            this.texto = value;
        }

    }

}
