
package com.caasa.soap.exportaciones.stock;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_consulta_stock_req complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_consulta_stock_req"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="es_secundario" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="gpo_vendedor" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="oficina" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="centro" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="almacen" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Org_ventas" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="canal_distribucion" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="detalle" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="unidad" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="material" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="18"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="posicion" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cantidad_solicitada" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="13"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="centro" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="almacen" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="multisociedad" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "DT_consulta_stock_req", propOrder = {
    "esSecundario",
    "gpoVendedor",
    "oficina",
    "centro",
    "almacen",
    "orgVentas",
    "canalDistribucion",
    "detalle"
})
public class DTConsultaStockReq {

    @XmlElement(name = "es_secundario")
    protected String esSecundario;
    @XmlElement(name = "gpo_vendedor")
    protected String gpoVendedor;
    protected String oficina;
    protected String centro;
    protected String almacen;
    @XmlElement(name = "Org_ventas")
    protected String orgVentas;
    @XmlElement(name = "canal_distribucion")
    protected String canalDistribucion;
    @XmlElement(required = true)
    protected List<DTConsultaStockReq.Detalle> detalle;

    /**
     * Obtiene el valor de la propiedad esSecundario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsSecundario() {
        return esSecundario;
    }

    /**
     * Define el valor de la propiedad esSecundario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsSecundario(String value) {
        this.esSecundario = value;
    }

    /**
     * Obtiene el valor de la propiedad gpoVendedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGpoVendedor() {
        return gpoVendedor;
    }

    /**
     * Define el valor de la propiedad gpoVendedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGpoVendedor(String value) {
        this.gpoVendedor = value;
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
     * Obtiene el valor de la propiedad canalDistribucion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanalDistribucion() {
        return canalDistribucion;
    }

    /**
     * Define el valor de la propiedad canalDistribucion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanalDistribucion(String value) {
        this.canalDistribucion = value;
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
     * {@link DTConsultaStockReq.Detalle }
     * 
     * 
     */
    public List<DTConsultaStockReq.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<DTConsultaStockReq.Detalle>();
        }
        return this.detalle;
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
     *         &lt;element name="unidad" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="material" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="18"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="posicion" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cantidad_solicitada" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="13"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="centro" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="almacen" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="multisociedad" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
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
    @XmlType(name = "", propOrder = {
        "unidad",
        "material",
        "posicion",
        "cantidadSolicitada",
        "centro",
        "almacen",
        "multisociedad"
    })
    public static class Detalle {

        protected String unidad;
        protected String material;
        protected String posicion;
        @XmlElement(name = "cantidad_solicitada")
        protected String cantidadSolicitada;
        protected String centro;
        protected String almacen;
        protected String multisociedad;

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
         * Obtiene el valor de la propiedad cantidadSolicitada.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCantidadSolicitada() {
            return cantidadSolicitada;
        }

        /**
         * Define el valor de la propiedad cantidadSolicitada.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCantidadSolicitada(String value) {
            this.cantidadSolicitada = value;
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
         * Obtiene el valor de la propiedad multisociedad.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMultisociedad() {
            return multisociedad;
        }

        /**
         * Define el valor de la propiedad multisociedad.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMultisociedad(String value) {
            this.multisociedad = value;
        }

    }

}
