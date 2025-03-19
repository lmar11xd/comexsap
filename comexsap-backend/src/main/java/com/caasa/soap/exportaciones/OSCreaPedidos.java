package com.caasa.soap.exportaciones;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.4.4
 * 2023-08-02T15:02:34.665-05:00
 * Generated source version: 3.4.4
 *
 */
@WebService(targetNamespace = "http://aasa.com.pe/WEB/CrePediWeb", name = "OS_crea_pedidos")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface OSCreaPedidos {

    @WebMethod(operationName = "OS_crea_pedidos", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_crea_pedidos_res", targetNamespace = "http://aasa.com.pe/WEB/CrePediWeb", partName = "MT_crea_pedidos_res")
    public DTCreaPedidosRes osCreaPedidos(

        @WebParam(partName = "MT_crea_pedidos_req", name = "MT_crea_pedidos_req", targetNamespace = "http://aasa.com.pe/WEB/CrePediWeb")
        DTCreaPedidosReq mtCreaPedidosReq
    );
}
