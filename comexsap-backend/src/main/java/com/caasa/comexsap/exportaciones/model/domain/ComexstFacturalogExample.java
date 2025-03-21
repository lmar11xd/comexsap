package com.caasa.comexsap.exportaciones.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstFacturalogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public ComexstFacturalogExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFlognIdIsNull() {
            addCriterion("FLOGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andFlognIdIsNotNull() {
            addCriterion("FLOGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFlognIdEqualTo(Integer value) {
            addCriterion("FLOGN_ID =", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdNotEqualTo(Integer value) {
            addCriterion("FLOGN_ID <>", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdGreaterThan(Integer value) {
            addCriterion("FLOGN_ID >", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_ID >=", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdLessThan(Integer value) {
            addCriterion("FLOGN_ID <", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdLessThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_ID <=", value, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdIn(List<Integer> values) {
            addCriterion("FLOGN_ID in", values, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdNotIn(List<Integer> values) {
            addCriterion("FLOGN_ID not in", values, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_ID between", value1, value2, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_ID not between", value1, value2, "flognId");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaIsNull() {
            addCriterion("FLOGN_IDESTADOFACTURA is null");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaIsNotNull() {
            addCriterion("FLOGN_IDESTADOFACTURA is not null");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaEqualTo(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA =", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaNotEqualTo(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA <>", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaGreaterThan(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA >", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA >=", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaLessThan(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA <", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaLessThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_IDESTADOFACTURA <=", value, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaIn(List<Integer> values) {
            addCriterion("FLOGN_IDESTADOFACTURA in", values, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaNotIn(List<Integer> values) {
            addCriterion("FLOGN_IDESTADOFACTURA not in", values, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_IDESTADOFACTURA between", value1, value2, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdestadofacturaNotBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_IDESTADOFACTURA not between", value1, value2, "flognIdestadofactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaIsNull() {
            addCriterion("FLOGV_FACTURA is null");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaIsNotNull() {
            addCriterion("FLOGV_FACTURA is not null");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaEqualTo(String value) {
            addCriterion("FLOGV_FACTURA =", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaNotEqualTo(String value) {
            addCriterion("FLOGV_FACTURA <>", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaGreaterThan(String value) {
            addCriterion("FLOGV_FACTURA >", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaGreaterThanOrEqualTo(String value) {
            addCriterion("FLOGV_FACTURA >=", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaLessThan(String value) {
            addCriterion("FLOGV_FACTURA <", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaLessThanOrEqualTo(String value) {
            addCriterion("FLOGV_FACTURA <=", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaLike(String value) {
            addCriterion("FLOGV_FACTURA like", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaNotLike(String value) {
            addCriterion("FLOGV_FACTURA not like", value, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaIn(List<String> values) {
            addCriterion("FLOGV_FACTURA in", values, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaNotIn(List<String> values) {
            addCriterion("FLOGV_FACTURA not in", values, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaBetween(String value1, String value2) {
            addCriterion("FLOGV_FACTURA between", value1, value2, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvFacturaNotBetween(String value1, String value2) {
            addCriterion("FLOGV_FACTURA not between", value1, value2, "flogvFactura");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoIsNull() {
            addCriterion("FLOGV_NOTACREDITO is null");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoIsNotNull() {
            addCriterion("FLOGV_NOTACREDITO is not null");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoEqualTo(String value) {
            addCriterion("FLOGV_NOTACREDITO =", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoNotEqualTo(String value) {
            addCriterion("FLOGV_NOTACREDITO <>", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoGreaterThan(String value) {
            addCriterion("FLOGV_NOTACREDITO >", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoGreaterThanOrEqualTo(String value) {
            addCriterion("FLOGV_NOTACREDITO >=", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoLessThan(String value) {
            addCriterion("FLOGV_NOTACREDITO <", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoLessThanOrEqualTo(String value) {
            addCriterion("FLOGV_NOTACREDITO <=", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoLike(String value) {
            addCriterion("FLOGV_NOTACREDITO like", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoNotLike(String value) {
            addCriterion("FLOGV_NOTACREDITO not like", value, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoIn(List<String> values) {
            addCriterion("FLOGV_NOTACREDITO in", values, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoNotIn(List<String> values) {
            addCriterion("FLOGV_NOTACREDITO not in", values, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoBetween(String value1, String value2) {
            addCriterion("FLOGV_NOTACREDITO between", value1, value2, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvNotacreditoNotBetween(String value1, String value2) {
            addCriterion("FLOGV_NOTACREDITO not between", value1, value2, "flogvNotacredito");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeIsNull() {
            addCriterion("FLOGV_MENSAJE is null");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeIsNotNull() {
            addCriterion("FLOGV_MENSAJE is not null");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeEqualTo(String value) {
            addCriterion("FLOGV_MENSAJE =", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeNotEqualTo(String value) {
            addCriterion("FLOGV_MENSAJE <>", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeGreaterThan(String value) {
            addCriterion("FLOGV_MENSAJE >", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeGreaterThanOrEqualTo(String value) {
            addCriterion("FLOGV_MENSAJE >=", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeLessThan(String value) {
            addCriterion("FLOGV_MENSAJE <", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeLessThanOrEqualTo(String value) {
            addCriterion("FLOGV_MENSAJE <=", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeLike(String value) {
            addCriterion("FLOGV_MENSAJE like", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeNotLike(String value) {
            addCriterion("FLOGV_MENSAJE not like", value, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeIn(List<String> values) {
            addCriterion("FLOGV_MENSAJE in", values, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeNotIn(List<String> values) {
            addCriterion("FLOGV_MENSAJE not in", values, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeBetween(String value1, String value2) {
            addCriterion("FLOGV_MENSAJE between", value1, value2, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlogvMensajeNotBetween(String value1, String value2) {
            addCriterion("FLOGV_MENSAJE not between", value1, value2, "flogvMensaje");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaIsNull() {
            addCriterion("FLOGN_IDTIPOFACTURA is null");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaIsNotNull() {
            addCriterion("FLOGN_IDTIPOFACTURA is not null");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaEqualTo(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA =", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaNotEqualTo(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA <>", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaGreaterThan(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA >", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaGreaterThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA >=", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaLessThan(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA <", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaLessThanOrEqualTo(Integer value) {
            addCriterion("FLOGN_IDTIPOFACTURA <=", value, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaIn(List<Integer> values) {
            addCriterion("FLOGN_IDTIPOFACTURA in", values, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaNotIn(List<Integer> values) {
            addCriterion("FLOGN_IDTIPOFACTURA not in", values, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_IDTIPOFACTURA between", value1, value2, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlognIdtipofacturaNotBetween(Integer value1, Integer value2) {
            addCriterion("FLOGN_IDTIPOFACTURA not between", value1, value2, "flognIdtipofactura");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionIsNull() {
            addCriterion("FLOGV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionIsNotNull() {
            addCriterion("FLOGV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionEqualTo(String value) {
            addCriterion("FLOGV_USUARIOCREACION =", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionNotEqualTo(String value) {
            addCriterion("FLOGV_USUARIOCREACION <>", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionGreaterThan(String value) {
            addCriterion("FLOGV_USUARIOCREACION >", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("FLOGV_USUARIOCREACION >=", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionLessThan(String value) {
            addCriterion("FLOGV_USUARIOCREACION <", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("FLOGV_USUARIOCREACION <=", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionLike(String value) {
            addCriterion("FLOGV_USUARIOCREACION like", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionNotLike(String value) {
            addCriterion("FLOGV_USUARIOCREACION not like", value, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionIn(List<String> values) {
            addCriterion("FLOGV_USUARIOCREACION in", values, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionNotIn(List<String> values) {
            addCriterion("FLOGV_USUARIOCREACION not in", values, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("FLOGV_USUARIOCREACION between", value1, value2, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("FLOGV_USUARIOCREACION not between", value1, value2, "flogvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionIsNull() {
            addCriterion("FLOGD_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionIsNotNull() {
            addCriterion("FLOGD_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionEqualTo(Date value) {
            addCriterion("FLOGD_FECHACREACION =", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionNotEqualTo(Date value) {
            addCriterion("FLOGD_FECHACREACION <>", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionGreaterThan(Date value) {
            addCriterion("FLOGD_FECHACREACION >", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("FLOGD_FECHACREACION >=", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionLessThan(Date value) {
            addCriterion("FLOGD_FECHACREACION <", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("FLOGD_FECHACREACION <=", value, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionIn(List<Date> values) {
            addCriterion("FLOGD_FECHACREACION in", values, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionNotIn(List<Date> values) {
            addCriterion("FLOGD_FECHACREACION not in", values, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionBetween(Date value1, Date value2) {
            addCriterion("FLOGD_FECHACREACION between", value1, value2, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("FLOGD_FECHACREACION not between", value1, value2, "flogdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionIsNull() {
            addCriterion("FLOGV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionIsNotNull() {
            addCriterion("FLOGV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionEqualTo(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION =", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION <>", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionGreaterThan(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION >", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION >=", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionLessThan(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION <", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION <=", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionLike(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION like", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionNotLike(String value) {
            addCriterion("FLOGV_USUARIOMODIFICACION not like", value, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionIn(List<String> values) {
            addCriterion("FLOGV_USUARIOMODIFICACION in", values, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("FLOGV_USUARIOMODIFICACION not in", values, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("FLOGV_USUARIOMODIFICACION between", value1, value2, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("FLOGV_USUARIOMODIFICACION not between", value1, value2, "flogvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionIsNull() {
            addCriterion("FLOGD_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionIsNotNull() {
            addCriterion("FLOGD_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionEqualTo(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION =", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionNotEqualTo(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION <>", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionGreaterThan(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION >", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION >=", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionLessThan(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION <", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("FLOGD_FECHAMODIFICACION <=", value, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionIn(List<Date> values) {
            addCriterion("FLOGD_FECHAMODIFICACION in", values, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionNotIn(List<Date> values) {
            addCriterion("FLOGD_FECHAMODIFICACION not in", values, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("FLOGD_FECHAMODIFICACION between", value1, value2, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlogdFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("FLOGD_FECHAMODIFICACION not between", value1, value2, "flogdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoIsNull() {
            addCriterion("FLOGN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoIsNotNull() {
            addCriterion("FLOGN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoEqualTo(Short value) {
            addCriterion("FLOGN_ESTADO =", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoNotEqualTo(Short value) {
            addCriterion("FLOGN_ESTADO <>", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoGreaterThan(Short value) {
            addCriterion("FLOGN_ESTADO >", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("FLOGN_ESTADO >=", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoLessThan(Short value) {
            addCriterion("FLOGN_ESTADO <", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoLessThanOrEqualTo(Short value) {
            addCriterion("FLOGN_ESTADO <=", value, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoIn(List<Short> values) {
            addCriterion("FLOGN_ESTADO in", values, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoNotIn(List<Short> values) {
            addCriterion("FLOGN_ESTADO not in", values, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoBetween(Short value1, Short value2) {
            addCriterion("FLOGN_ESTADO between", value1, value2, "flognEstado");
            return (Criteria) this;
        }

        public Criteria andFlognEstadoNotBetween(Short value1, Short value2) {
            addCriterion("FLOGN_ESTADO not between", value1, value2, "flognEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_FACTURALOG
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}