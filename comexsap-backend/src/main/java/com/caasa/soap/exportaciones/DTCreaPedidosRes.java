
package com.caasa.soap.exportaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_crea_pedidos_res complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_crea_pedidos_res"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="salida" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="num_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="tipo_msj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="desc_msj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_crea_pedidos_res", propOrder = {
    "salida"
})
public class DTCreaPedidosRes {

    @XmlElement(required = true)
    protected List<DTCreaPedidosRes.Salida> salida;

    /**
     * Gets the value of the salida property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salida property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalida().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCreaPedidosRes.Salida }
     * 
     * 
     */
    public List<DTCreaPedidosRes.Salida> getSalida() {
        if (salida == null) {
            salida = new ArrayList<DTCreaPedidosRes.Salida>();
        }
        return this.salida;
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
     *         &lt;element name="num_pedido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="tipo_msj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="desc_msj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "numPedido",
        "tipoMsj",
        "id",
        "descMsj",
        "num"
    })
    public static class Salida {

        @XmlElement(name = "num_pedido")
        protected String numPedido;
        @XmlElement(name = "tipo_msj")
        protected String tipoMsj;
        protected String id;
        @XmlElement(name = "desc_msj")
        protected String descMsj;
        protected String num;

        /**
         * Obtiene el valor de la propiedad numPedido.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumPedido() {
            return numPedido;
        }

        /**
         * Define el valor de la propiedad numPedido.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumPedido(String value) {
            this.numPedido = value;
        }

        /**
         * Obtiene el valor de la propiedad tipoMsj.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoMsj() {
            return tipoMsj;
        }

        /**
         * Define el valor de la propiedad tipoMsj.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoMsj(String value) {
            this.tipoMsj = value;
        }

        /**
         * Obtiene el valor de la propiedad id.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Define el valor de la propiedad id.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * Obtiene el valor de la propiedad descMsj.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescMsj() {
            return descMsj;
        }

        /**
         * Define el valor de la propiedad descMsj.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescMsj(String value) {
            this.descMsj = value;
        }

        /**
         * Obtiene el valor de la propiedad num.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNum() {
            return num;
        }

        /**
         * Define el valor de la propiedad num.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNum(String value) {
            this.num = value;
        }

    }

}
