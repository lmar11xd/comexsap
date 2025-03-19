
package com.caasa.soap.exportaciones;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DT_modi_pedidos_req complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DT_modi_pedidos_req"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="testrun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cabecera" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="num_pedido"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="org_ventas" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="canal_dist" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="sector" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tipo_doc" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="moneda" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="5"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_precio" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="grupo_vend" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="oficina" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="lista_precio" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cond_pago" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cond_exp" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="grupo" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_pref" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_fact" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_doc" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ord_compra" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="mot_pedido" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cotizacion" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="18"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="grupo1" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="bloque_entr" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="zona_ventas" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="grupo_cli" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tipo_pago" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="centro_sum" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="incoterm1" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="incoterm2" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="28"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_occli" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_descObr" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="35"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_despa" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="12"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_cont" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="cabecera_x" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="updateflag"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="currency"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="sales_grp"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="sales_off"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ship_cond"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="req_date_h"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="bill_date"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="price_date"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ord_reason"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ass_number"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cust_grp1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="dlv_block"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="price_list"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="purch_no_c"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="doc_date"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cust_group"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="sales_dist"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pymt_meth"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="incoterms1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="incoterms2"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pmnttrms"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="purch_date"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="purch_no_s"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ref_1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cust_grp5"&gt;
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
 *         &lt;element name="Detalle" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="posicion"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pos_sup" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="material"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="18"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cantidad"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="15"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="centro"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="almacen"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="unidad"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="ruta" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tdes_exs"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tdes_fsu" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tdes_lim" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tipo_nc" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_lote" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_dmo"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="num_pmo"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="mot_rech" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pstyp"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="detalle_x" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="itm_number" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="updateflag" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="material" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pos_sup" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="target_qty" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="target_qu" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="plant" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="store_loc" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="route" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="overdlvtol" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="unddlv_tol" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="unlmt_dlv" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="usage_ind" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="batch" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="vgbel" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="vgpos" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="augru" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pstyp" minOccurs="0"&gt;
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
 *         &lt;element name="interlocutor" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="cod_inte" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="tipo_int" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="2"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="updateflag" minOccurs="0"&gt;
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
 *         &lt;element name="condiciones" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="cond_pos" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cond_pr" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cond_bp" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="11"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cond_val" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="13"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="koein" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="5"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="kpein" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="5"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="kmein" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="updateflag"&gt;
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
 *         &lt;element name="textos" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="id_texto" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="texto" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="250"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
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
 *                   &lt;element name="posnr" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="fecha_prefe" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="8"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cantidad" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="13"/&gt;
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
@XmlType(name = "DT_modi_pedidos_req", propOrder = {
    "testrun",
    "cabecera",
    "cabeceraX",
    "detalle",
    "detalleX",
    "interlocutor",
    "condiciones",
    "textos",
    "repartos"
})
public class DTModiPedidosReq {

    protected String testrun;
    @XmlElement(required = true)
    protected List<DTModiPedidosReq.Cabecera> cabecera;
    @XmlElement(name = "cabecera_x", required = true)
    protected List<DTModiPedidosReq.CabeceraX> cabeceraX;
    @XmlElement(name = "Detalle", required = true)
    protected List<DTModiPedidosReq.Detalle> detalle;
    @XmlElement(name = "detalle_x", required = true)
    protected List<DTModiPedidosReq.DetalleX> detalleX;
    @XmlElement(required = true)
    protected List<DTModiPedidosReq.Interlocutor> interlocutor;
    @XmlElement(required = true)
    protected List<DTModiPedidosReq.Condiciones> condiciones;
    @XmlElement(required = true)
    protected List<DTModiPedidosReq.Textos> textos;
    @XmlElement(required = true)
    protected List<DTModiPedidosReq.Repartos> repartos;

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
     * {@link DTModiPedidosReq.Cabecera }
     * 
     * 
     */
    public List<DTModiPedidosReq.Cabecera> getCabecera() {
        if (cabecera == null) {
            cabecera = new ArrayList<DTModiPedidosReq.Cabecera>();
        }
        return this.cabecera;
    }

    /**
     * Gets the value of the cabeceraX property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cabeceraX property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCabeceraX().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTModiPedidosReq.CabeceraX }
     * 
     * 
     */
    public List<DTModiPedidosReq.CabeceraX> getCabeceraX() {
        if (cabeceraX == null) {
            cabeceraX = new ArrayList<DTModiPedidosReq.CabeceraX>();
        }
        return this.cabeceraX;
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
     * {@link DTModiPedidosReq.Detalle }
     * 
     * 
     */
    public List<DTModiPedidosReq.Detalle> getDetalle() {
        if (detalle == null) {
            detalle = new ArrayList<DTModiPedidosReq.Detalle>();
        }
        return this.detalle;
    }

    /**
     * Gets the value of the detalleX property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detalleX property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleX().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTModiPedidosReq.DetalleX }
     * 
     * 
     */
    public List<DTModiPedidosReq.DetalleX> getDetalleX() {
        if (detalleX == null) {
            detalleX = new ArrayList<DTModiPedidosReq.DetalleX>();
        }
        return this.detalleX;
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
     * {@link DTModiPedidosReq.Interlocutor }
     * 
     * 
     */
    public List<DTModiPedidosReq.Interlocutor> getInterlocutor() {
        if (interlocutor == null) {
            interlocutor = new ArrayList<DTModiPedidosReq.Interlocutor>();
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
     * {@link DTModiPedidosReq.Condiciones }
     * 
     * 
     */
    public List<DTModiPedidosReq.Condiciones> getCondiciones() {
        if (condiciones == null) {
            condiciones = new ArrayList<DTModiPedidosReq.Condiciones>();
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
     * {@link DTModiPedidosReq.Textos }
     * 
     * 
     */
    public List<DTModiPedidosReq.Textos> getTextos() {
        if (textos == null) {
            textos = new ArrayList<DTModiPedidosReq.Textos>();
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
     * {@link DTModiPedidosReq.Repartos }
     * 
     * 
     */
    public List<DTModiPedidosReq.Repartos> getRepartos() {
        if (repartos == null) {
            repartos = new ArrayList<DTModiPedidosReq.Repartos>();
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
     *         &lt;element name="num_pedido"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="org_ventas" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="canal_dist" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="sector" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tipo_doc" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="moneda" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="5"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_precio" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="grupo_vend" minOccurs="0"&gt;
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
     *         &lt;element name="lista_precio" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cond_pago" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cond_exp" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="grupo" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_pref" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_fact" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_doc" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ord_compra" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="mot_pedido" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cotizacion" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="18"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="grupo1" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="bloque_entr" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="zona_ventas" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="grupo_cli" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tipo_pago" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="centro_sum" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="incoterm1" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="incoterm2" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="28"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_occli" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_descObr" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="35"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_despa" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="12"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_cont" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
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
        "numPedido",
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
        "numCont"
    })
    public static class Cabecera {

        @XmlElement(name = "num_pedido", required = true)
        protected String numPedido;
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
     *         &lt;element name="updateflag"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="currency"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="sales_grp"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="sales_off"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ship_cond"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="req_date_h"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="bill_date"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="price_date"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ord_reason"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ass_number"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cust_grp1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="dlv_block"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="price_list"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="purch_no_c"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="doc_date"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cust_group"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="sales_dist"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pymt_meth"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="incoterms1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="incoterms2"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pmnttrms"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="purch_date"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="purch_no_s"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ref_1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cust_grp5"&gt;
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
        "updateflag",
        "currency",
        "salesGrp",
        "salesOff",
        "shipCond",
        "reqDateH",
        "billDate",
        "priceDate",
        "ordReason",
        "assNumber",
        "custGrp1",
        "dlvBlock",
        "priceList",
        "purchNoC",
        "docDate",
        "custGroup",
        "salesDist",
        "pymtMeth",
        "incoterms1",
        "incoterms2",
        "pmnttrms",
        "purchDate",
        "purchNoS",
        "ref1",
        "custGrp5"
    })
    public static class CabeceraX {

        @XmlElement(required = true)
        protected String updateflag;
        @XmlElement(required = true)
        protected String currency;
        @XmlElement(name = "sales_grp", required = true)
        protected String salesGrp;
        @XmlElement(name = "sales_off", required = true)
        protected String salesOff;
        @XmlElement(name = "ship_cond", required = true)
        protected String shipCond;
        @XmlElement(name = "req_date_h", required = true)
        protected String reqDateH;
        @XmlElement(name = "bill_date", required = true)
        protected String billDate;
        @XmlElement(name = "price_date", required = true)
        protected String priceDate;
        @XmlElement(name = "ord_reason", required = true)
        protected String ordReason;
        @XmlElement(name = "ass_number", required = true)
        protected String assNumber;
        @XmlElement(name = "cust_grp1", required = true)
        protected String custGrp1;
        @XmlElement(name = "dlv_block", required = true)
        protected String dlvBlock;
        @XmlElement(name = "price_list", required = true)
        protected String priceList;
        @XmlElement(name = "purch_no_c", required = true)
        protected String purchNoC;
        @XmlElement(name = "doc_date", required = true)
        protected String docDate;
        @XmlElement(name = "cust_group", required = true)
        protected String custGroup;
        @XmlElement(name = "sales_dist", required = true)
        protected String salesDist;
        @XmlElement(name = "pymt_meth", required = true)
        protected String pymtMeth;
        @XmlElement(required = true)
        protected String incoterms1;
        @XmlElement(required = true)
        protected String incoterms2;
        @XmlElement(required = true)
        protected String pmnttrms;
        @XmlElement(name = "purch_date", required = true)
        protected String purchDate;
        @XmlElement(name = "purch_no_s", required = true)
        protected String purchNoS;
        @XmlElement(name = "ref_1", required = true)
        protected String ref1;
        @XmlElement(name = "cust_grp5", required = true)
        protected String custGrp5;

        /**
         * Obtiene el valor de la propiedad updateflag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUpdateflag() {
            return updateflag;
        }

        /**
         * Define el valor de la propiedad updateflag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUpdateflag(String value) {
            this.updateflag = value;
        }

        /**
         * Obtiene el valor de la propiedad currency.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Define el valor de la propiedad currency.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrency(String value) {
            this.currency = value;
        }

        /**
         * Obtiene el valor de la propiedad salesGrp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesGrp() {
            return salesGrp;
        }

        /**
         * Define el valor de la propiedad salesGrp.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesGrp(String value) {
            this.salesGrp = value;
        }

        /**
         * Obtiene el valor de la propiedad salesOff.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesOff() {
            return salesOff;
        }

        /**
         * Define el valor de la propiedad salesOff.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesOff(String value) {
            this.salesOff = value;
        }

        /**
         * Obtiene el valor de la propiedad shipCond.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShipCond() {
            return shipCond;
        }

        /**
         * Define el valor de la propiedad shipCond.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShipCond(String value) {
            this.shipCond = value;
        }

        /**
         * Obtiene el valor de la propiedad reqDateH.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReqDateH() {
            return reqDateH;
        }

        /**
         * Define el valor de la propiedad reqDateH.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReqDateH(String value) {
            this.reqDateH = value;
        }

        /**
         * Obtiene el valor de la propiedad billDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillDate() {
            return billDate;
        }

        /**
         * Define el valor de la propiedad billDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillDate(String value) {
            this.billDate = value;
        }

        /**
         * Obtiene el valor de la propiedad priceDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPriceDate() {
            return priceDate;
        }

        /**
         * Define el valor de la propiedad priceDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPriceDate(String value) {
            this.priceDate = value;
        }

        /**
         * Obtiene el valor de la propiedad ordReason.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrdReason() {
            return ordReason;
        }

        /**
         * Define el valor de la propiedad ordReason.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrdReason(String value) {
            this.ordReason = value;
        }

        /**
         * Obtiene el valor de la propiedad assNumber.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAssNumber() {
            return assNumber;
        }

        /**
         * Define el valor de la propiedad assNumber.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAssNumber(String value) {
            this.assNumber = value;
        }

        /**
         * Obtiene el valor de la propiedad custGrp1.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustGrp1() {
            return custGrp1;
        }

        /**
         * Define el valor de la propiedad custGrp1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustGrp1(String value) {
            this.custGrp1 = value;
        }

        /**
         * Obtiene el valor de la propiedad dlvBlock.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDlvBlock() {
            return dlvBlock;
        }

        /**
         * Define el valor de la propiedad dlvBlock.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDlvBlock(String value) {
            this.dlvBlock = value;
        }

        /**
         * Obtiene el valor de la propiedad priceList.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPriceList() {
            return priceList;
        }

        /**
         * Define el valor de la propiedad priceList.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPriceList(String value) {
            this.priceList = value;
        }

        /**
         * Obtiene el valor de la propiedad purchNoC.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPurchNoC() {
            return purchNoC;
        }

        /**
         * Define el valor de la propiedad purchNoC.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPurchNoC(String value) {
            this.purchNoC = value;
        }

        /**
         * Obtiene el valor de la propiedad docDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDocDate() {
            return docDate;
        }

        /**
         * Define el valor de la propiedad docDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDocDate(String value) {
            this.docDate = value;
        }

        /**
         * Obtiene el valor de la propiedad custGroup.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustGroup() {
            return custGroup;
        }

        /**
         * Define el valor de la propiedad custGroup.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustGroup(String value) {
            this.custGroup = value;
        }

        /**
         * Obtiene el valor de la propiedad salesDist.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesDist() {
            return salesDist;
        }

        /**
         * Define el valor de la propiedad salesDist.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesDist(String value) {
            this.salesDist = value;
        }

        /**
         * Obtiene el valor de la propiedad pymtMeth.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPymtMeth() {
            return pymtMeth;
        }

        /**
         * Define el valor de la propiedad pymtMeth.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPymtMeth(String value) {
            this.pymtMeth = value;
        }

        /**
         * Obtiene el valor de la propiedad incoterms1.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIncoterms1() {
            return incoterms1;
        }

        /**
         * Define el valor de la propiedad incoterms1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIncoterms1(String value) {
            this.incoterms1 = value;
        }

        /**
         * Obtiene el valor de la propiedad incoterms2.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIncoterms2() {
            return incoterms2;
        }

        /**
         * Define el valor de la propiedad incoterms2.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIncoterms2(String value) {
            this.incoterms2 = value;
        }

        /**
         * Obtiene el valor de la propiedad pmnttrms.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPmnttrms() {
            return pmnttrms;
        }

        /**
         * Define el valor de la propiedad pmnttrms.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPmnttrms(String value) {
            this.pmnttrms = value;
        }

        /**
         * Obtiene el valor de la propiedad purchDate.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPurchDate() {
            return purchDate;
        }

        /**
         * Define el valor de la propiedad purchDate.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPurchDate(String value) {
            this.purchDate = value;
        }

        /**
         * Obtiene el valor de la propiedad purchNoS.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPurchNoS() {
            return purchNoS;
        }

        /**
         * Define el valor de la propiedad purchNoS.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPurchNoS(String value) {
            this.purchNoS = value;
        }

        /**
         * Obtiene el valor de la propiedad ref1.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRef1() {
            return ref1;
        }

        /**
         * Define el valor de la propiedad ref1.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRef1(String value) {
            this.ref1 = value;
        }

        /**
         * Obtiene el valor de la propiedad custGrp5.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustGrp5() {
            return custGrp5;
        }

        /**
         * Define el valor de la propiedad custGrp5.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustGrp5(String value) {
            this.custGrp5 = value;
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
     *         &lt;element name="cond_pos" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cond_pr" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cond_bp" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="11"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cond_val" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="13"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="koein" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="5"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="kpein" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="5"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="kmein" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="updateflag"&gt;
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
        "condPos",
        "condPr",
        "condBp",
        "condVal",
        "koein",
        "kpein",
        "kmein",
        "updateflag"
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
        @XmlElement(required = true)
        protected String updateflag;

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

        /**
         * Obtiene el valor de la propiedad updateflag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUpdateflag() {
            return updateflag;
        }

        /**
         * Define el valor de la propiedad updateflag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUpdateflag(String value) {
            this.updateflag = value;
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
     *         &lt;element name="posicion"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pos_sup" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="material"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="18"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cantidad"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="15"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="centro"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="almacen"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="unidad"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="ruta" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tdes_exs"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tdes_fsu" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tdes_lim" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tipo_nc" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_lote" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_dmo"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="num_pmo"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="mot_rech" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pstyp"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
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
        "pstyp"
    })
    public static class Detalle {

        @XmlElement(required = true)
        protected String posicion;
        @XmlElement(name = "pos_sup")
        protected String posSup;
        @XmlElement(required = true)
        protected String material;
        @XmlElement(required = true)
        protected String cantidad;
        @XmlElement(required = true)
        protected String centro;
        @XmlElement(required = true)
        protected String almacen;
        @XmlElement(required = true)
        protected String unidad;
        protected String ruta;
        @XmlElement(name = "tdes_exs", required = true)
        protected String tdesExs;
        @XmlElement(name = "tdes_fsu")
        protected String tdesFsu;
        @XmlElement(name = "tdes_lim")
        protected String tdesLim;
        @XmlElement(name = "tipo_nc")
        protected String tipoNc;
        @XmlElement(name = "num_lote")
        protected String numLote;
        @XmlElement(name = "num_dmo", required = true)
        protected String numDmo;
        @XmlElement(name = "num_pmo", required = true)
        protected String numPmo;
        @XmlElement(name = "mot_rech")
        protected String motRech;
        @XmlElement(required = true)
        protected String pstyp;

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
         * Obtiene el valor de la propiedad pstyp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPstyp() {
            return pstyp;
        }

        /**
         * Define el valor de la propiedad pstyp.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPstyp(String value) {
            this.pstyp = value;
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
     *         &lt;element name="itm_number" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="updateflag" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="material" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pos_sup" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="target_qty" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="target_qu" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="plant" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="store_loc" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="route" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="overdlvtol" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="unddlv_tol" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="unlmt_dlv" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="usage_ind" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="batch" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="vgbel" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="vgpos" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="augru" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pstyp" minOccurs="0"&gt;
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
        "itmNumber",
        "updateflag",
        "material",
        "posSup",
        "targetQty",
        "targetQu",
        "plant",
        "storeLoc",
        "route",
        "overdlvtol",
        "unddlvTol",
        "unlmtDlv",
        "usageInd",
        "batch",
        "vgbel",
        "vgpos",
        "augru",
        "pstyp"
    })
    public static class DetalleX {

        @XmlElement(name = "itm_number")
        protected String itmNumber;
        protected String updateflag;
        protected String material;
        @XmlElement(name = "pos_sup")
        protected String posSup;
        @XmlElement(name = "target_qty")
        protected String targetQty;
        @XmlElement(name = "target_qu")
        protected String targetQu;
        protected String plant;
        @XmlElement(name = "store_loc")
        protected String storeLoc;
        protected String route;
        protected String overdlvtol;
        @XmlElement(name = "unddlv_tol")
        protected String unddlvTol;
        @XmlElement(name = "unlmt_dlv")
        protected String unlmtDlv;
        @XmlElement(name = "usage_ind")
        protected String usageInd;
        protected String batch;
        protected String vgbel;
        protected String vgpos;
        protected String augru;
        protected String pstyp;

        /**
         * Obtiene el valor de la propiedad itmNumber.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getItmNumber() {
            return itmNumber;
        }

        /**
         * Define el valor de la propiedad itmNumber.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setItmNumber(String value) {
            this.itmNumber = value;
        }

        /**
         * Obtiene el valor de la propiedad updateflag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUpdateflag() {
            return updateflag;
        }

        /**
         * Define el valor de la propiedad updateflag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUpdateflag(String value) {
            this.updateflag = value;
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
         * Obtiene el valor de la propiedad targetQty.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTargetQty() {
            return targetQty;
        }

        /**
         * Define el valor de la propiedad targetQty.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTargetQty(String value) {
            this.targetQty = value;
        }

        /**
         * Obtiene el valor de la propiedad targetQu.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTargetQu() {
            return targetQu;
        }

        /**
         * Define el valor de la propiedad targetQu.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTargetQu(String value) {
            this.targetQu = value;
        }

        /**
         * Obtiene el valor de la propiedad plant.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPlant() {
            return plant;
        }

        /**
         * Define el valor de la propiedad plant.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPlant(String value) {
            this.plant = value;
        }

        /**
         * Obtiene el valor de la propiedad storeLoc.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStoreLoc() {
            return storeLoc;
        }

        /**
         * Define el valor de la propiedad storeLoc.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStoreLoc(String value) {
            this.storeLoc = value;
        }

        /**
         * Obtiene el valor de la propiedad route.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRoute() {
            return route;
        }

        /**
         * Define el valor de la propiedad route.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRoute(String value) {
            this.route = value;
        }

        /**
         * Obtiene el valor de la propiedad overdlvtol.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOverdlvtol() {
            return overdlvtol;
        }

        /**
         * Define el valor de la propiedad overdlvtol.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOverdlvtol(String value) {
            this.overdlvtol = value;
        }

        /**
         * Obtiene el valor de la propiedad unddlvTol.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnddlvTol() {
            return unddlvTol;
        }

        /**
         * Define el valor de la propiedad unddlvTol.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnddlvTol(String value) {
            this.unddlvTol = value;
        }

        /**
         * Obtiene el valor de la propiedad unlmtDlv.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnlmtDlv() {
            return unlmtDlv;
        }

        /**
         * Define el valor de la propiedad unlmtDlv.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnlmtDlv(String value) {
            this.unlmtDlv = value;
        }

        /**
         * Obtiene el valor de la propiedad usageInd.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsageInd() {
            return usageInd;
        }

        /**
         * Define el valor de la propiedad usageInd.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsageInd(String value) {
            this.usageInd = value;
        }

        /**
         * Obtiene el valor de la propiedad batch.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBatch() {
            return batch;
        }

        /**
         * Define el valor de la propiedad batch.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBatch(String value) {
            this.batch = value;
        }

        /**
         * Obtiene el valor de la propiedad vgbel.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVgbel() {
            return vgbel;
        }

        /**
         * Define el valor de la propiedad vgbel.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVgbel(String value) {
            this.vgbel = value;
        }

        /**
         * Obtiene el valor de la propiedad vgpos.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVgpos() {
            return vgpos;
        }

        /**
         * Define el valor de la propiedad vgpos.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVgpos(String value) {
            this.vgpos = value;
        }

        /**
         * Obtiene el valor de la propiedad augru.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAugru() {
            return augru;
        }

        /**
         * Define el valor de la propiedad augru.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAugru(String value) {
            this.augru = value;
        }

        /**
         * Obtiene el valor de la propiedad pstyp.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPstyp() {
            return pstyp;
        }

        /**
         * Define el valor de la propiedad pstyp.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPstyp(String value) {
            this.pstyp = value;
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
     *         &lt;element name="cod_inte" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="tipo_int" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="2"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="updateflag" minOccurs="0"&gt;
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
        "codInte",
        "tipoInt",
        "updateflag"
    })
    public static class Interlocutor {

        @XmlElement(name = "cod_inte")
        protected String codInte;
        @XmlElement(name = "tipo_int")
        protected String tipoInt;
        protected String updateflag;

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

        /**
         * Obtiene el valor de la propiedad updateflag.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUpdateflag() {
            return updateflag;
        }

        /**
         * Define el valor de la propiedad updateflag.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUpdateflag(String value) {
            this.updateflag = value;
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
     *         &lt;element name="posnr" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="fecha_prefe" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="8"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cantidad" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="13"/&gt;
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
     *         &lt;element name="id_texto" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="texto" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="250"/&gt;
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
