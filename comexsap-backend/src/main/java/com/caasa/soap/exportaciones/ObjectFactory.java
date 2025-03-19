
package com.caasa.soap.exportaciones;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.caasa.soap.exportaciones package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTModiPedidosReq_QNAME = new QName("http://aasa.com.pe/C4C/ModPediWeb", "MT_modi_pedidos_req");
    private final static QName _MTModiPedidosRes_QNAME = new QName("http://aasa.com.pe/C4C/ModPediWeb", "MT_modi_pedidos_res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.caasa.soap.exportaciones
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTModiPedidosRes }
     * 
     */
    public DTModiPedidosRes createDTModiPedidosRes() {
        return new DTModiPedidosRes();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq }
     * 
     */
    public DTModiPedidosReq createDTModiPedidosReq() {
        return new DTModiPedidosReq();
    }

    /**
     * Create an instance of {@link DTModiPedidosRes.Salida }
     * 
     */
    public DTModiPedidosRes.Salida createDTModiPedidosResSalida() {
        return new DTModiPedidosRes.Salida();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Cabecera }
     * 
     */
    public DTModiPedidosReq.Cabecera createDTModiPedidosReqCabecera() {
        return new DTModiPedidosReq.Cabecera();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.CabeceraX }
     * 
     */
    public DTModiPedidosReq.CabeceraX createDTModiPedidosReqCabeceraX() {
        return new DTModiPedidosReq.CabeceraX();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Detalle }
     * 
     */
    public DTModiPedidosReq.Detalle createDTModiPedidosReqDetalle() {
        return new DTModiPedidosReq.Detalle();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.DetalleX }
     * 
     */
    public DTModiPedidosReq.DetalleX createDTModiPedidosReqDetalleX() {
        return new DTModiPedidosReq.DetalleX();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Interlocutor }
     * 
     */
    public DTModiPedidosReq.Interlocutor createDTModiPedidosReqInterlocutor() {
        return new DTModiPedidosReq.Interlocutor();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Condiciones }
     * 
     */
    public DTModiPedidosReq.Condiciones createDTModiPedidosReqCondiciones() {
        return new DTModiPedidosReq.Condiciones();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Textos }
     * 
     */
    public DTModiPedidosReq.Textos createDTModiPedidosReqTextos() {
        return new DTModiPedidosReq.Textos();
    }

    /**
     * Create an instance of {@link DTModiPedidosReq.Repartos }
     * 
     */
    public DTModiPedidosReq.Repartos createDTModiPedidosReqRepartos() {
        return new DTModiPedidosReq.Repartos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTModiPedidosReq }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTModiPedidosReq }{@code >}
     */
    @XmlElementDecl(namespace = "http://aasa.com.pe/C4C/ModPediWeb", name = "MT_modi_pedidos_req")
    public JAXBElement<DTModiPedidosReq> createMTModiPedidosReq(DTModiPedidosReq value) {
        return new JAXBElement<DTModiPedidosReq>(_MTModiPedidosReq_QNAME, DTModiPedidosReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTModiPedidosRes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTModiPedidosRes }{@code >}
     */
    @XmlElementDecl(namespace = "http://aasa.com.pe/C4C/ModPediWeb", name = "MT_modi_pedidos_res")
    public JAXBElement<DTModiPedidosRes> createMTModiPedidosRes(DTModiPedidosRes value) {
        return new JAXBElement<DTModiPedidosRes>(_MTModiPedidosRes_QNAME, DTModiPedidosRes.class, null, value);
    }

}
