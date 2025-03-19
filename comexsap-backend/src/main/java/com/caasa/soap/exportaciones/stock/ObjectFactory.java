
package com.caasa.soap.exportaciones.stock;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.caasa.soap.exportaciones.stock package. 
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

    private final static QName _MTConsultaStockReq_QNAME = new QName("http://aasa.com.pe/WEB/ConsultaStock", "MT_consulta_stock_req");
    private final static QName _MTConsultaStockRes_QNAME = new QName("http://aasa.com.pe/WEB/ConsultaStock", "MT_consulta_stock_res");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.caasa.soap.exportaciones.stock
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTConsultaStockRes }
     * 
     */
    public DTConsultaStockRes createDTConsultaStockRes() {
        return new DTConsultaStockRes();
    }

    /**
     * Create an instance of {@link DTConsultaStockReq }
     * 
     */
    public DTConsultaStockReq createDTConsultaStockReq() {
        return new DTConsultaStockReq();
    }

    /**
     * Create an instance of {@link DTConsultaStockRes.Datos }
     * 
     */
    public DTConsultaStockRes.Datos createDTConsultaStockResDatos() {
        return new DTConsultaStockRes.Datos();
    }

    /**
     * Create an instance of {@link DTConsultaStockRes.Return }
     * 
     */
    public DTConsultaStockRes.Return createDTConsultaStockResReturn() {
        return new DTConsultaStockRes.Return();
    }

    /**
     * Create an instance of {@link DTConsultaStockReq.Detalle }
     * 
     */
    public DTConsultaStockReq.Detalle createDTConsultaStockReqDetalle() {
        return new DTConsultaStockReq.Detalle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTConsultaStockReq }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTConsultaStockReq }{@code >}
     */
    @XmlElementDecl(namespace = "http://aasa.com.pe/WEB/ConsultaStock", name = "MT_consulta_stock_req")
    public JAXBElement<DTConsultaStockReq> createMTConsultaStockReq(DTConsultaStockReq value) {
        return new JAXBElement<DTConsultaStockReq>(_MTConsultaStockReq_QNAME, DTConsultaStockReq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTConsultaStockRes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DTConsultaStockRes }{@code >}
     */
    @XmlElementDecl(namespace = "http://aasa.com.pe/WEB/ConsultaStock", name = "MT_consulta_stock_res")
    public JAXBElement<DTConsultaStockRes> createMTConsultaStockRes(DTConsultaStockRes value) {
        return new JAXBElement<DTConsultaStockRes>(_MTConsultaStockRes_QNAME, DTConsultaStockRes.class, null, value);
    }

}
