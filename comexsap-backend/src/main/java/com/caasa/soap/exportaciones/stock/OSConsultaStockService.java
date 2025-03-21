package com.caasa.soap.exportaciones.stock;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.4.4
 * 2023-08-02T15:02:35.320-05:00
 * Generated source version: 3.4.4
 *
 */
@WebServiceClient(name = "OS_consulta_stockService",
                  wsdlLocation = "/wsdl/ConsultarStockQAS.wsdl",
                  targetNamespace = "http://aasa.com.pe/WEB/ConsultaStock")
public class OSConsultaStockService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://aasa.com.pe/WEB/ConsultaStock", "OS_consulta_stockService");
    public final static QName HTTPSPort = new QName("http://aasa.com.pe/WEB/ConsultaStock", "HTTPS_Port");
    public final static QName HTTPPort = new QName("http://aasa.com.pe/WEB/ConsultaStock", "HTTP_Port");
    static {
        URL url = OSConsultaStockService.class.getResource("/wsdl/ConsultarStockQAS.wsdl");
        if (url == null) {
            url = OSConsultaStockService.class.getClassLoader().getResource("/wsdl/ConsultarStockQAS.wsdl");
        }
        if (url == null) {
            java.util.logging.Logger.getLogger(OSConsultaStockService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "/wsdl/ConsultarStockQAS.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public OSConsultaStockService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OSConsultaStockService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OSConsultaStockService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public OSConsultaStockService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public OSConsultaStockService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public OSConsultaStockService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns OSConsultaStock
     */
    @WebEndpoint(name = "HTTPS_Port")
    public OSConsultaStock getHTTPSPort() {
        return super.getPort(HTTPSPort, OSConsultaStock.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OSConsultaStock
     */
    @WebEndpoint(name = "HTTPS_Port")
    public OSConsultaStock getHTTPSPort(WebServiceFeature... features) {
        return super.getPort(HTTPSPort, OSConsultaStock.class, features);
    }


    /**
     *
     * @return
     *     returns OSConsultaStock
     */
    @WebEndpoint(name = "HTTP_Port")
    public OSConsultaStock getHTTPPort() {
        return super.getPort(HTTPPort, OSConsultaStock.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OSConsultaStock
     */
    @WebEndpoint(name = "HTTP_Port")
    public OSConsultaStock getHTTPPort(WebServiceFeature... features) {
        return super.getPort(HTTPPort, OSConsultaStock.class, features);
    }

}
