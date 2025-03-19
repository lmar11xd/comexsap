package com.caasa.comexsap.exportaciones.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstPackingcontenedorExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public ComexstPackingcontenedorExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
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
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
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
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGCONTENEDOR
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
     * This class corresponds to the database table COMEXST_PACKINGCONTENEDOR
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

        public Criteria andPconnIdIsNull() {
            addCriterion("PCONN_ID is null");
            return (Criteria) this;
        }

        public Criteria andPconnIdIsNotNull() {
            addCriterion("PCONN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPconnIdEqualTo(Integer value) {
            addCriterion("PCONN_ID =", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdNotEqualTo(Integer value) {
            addCriterion("PCONN_ID <>", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdGreaterThan(Integer value) {
            addCriterion("PCONN_ID >", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PCONN_ID >=", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdLessThan(Integer value) {
            addCriterion("PCONN_ID <", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdLessThanOrEqualTo(Integer value) {
            addCriterion("PCONN_ID <=", value, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdIn(List<Integer> values) {
            addCriterion("PCONN_ID in", values, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdNotIn(List<Integer> values) {
            addCriterion("PCONN_ID not in", values, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_ID between", value1, value2, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_ID not between", value1, value2, "pconnId");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingIsNull() {
            addCriterion("PCONN_IDPACKING is null");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingIsNotNull() {
            addCriterion("PCONN_IDPACKING is not null");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingEqualTo(Integer value) {
            addCriterion("PCONN_IDPACKING =", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingNotEqualTo(Integer value) {
            addCriterion("PCONN_IDPACKING <>", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingGreaterThan(Integer value) {
            addCriterion("PCONN_IDPACKING >", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingGreaterThanOrEqualTo(Integer value) {
            addCriterion("PCONN_IDPACKING >=", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingLessThan(Integer value) {
            addCriterion("PCONN_IDPACKING <", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingLessThanOrEqualTo(Integer value) {
            addCriterion("PCONN_IDPACKING <=", value, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingIn(List<Integer> values) {
            addCriterion("PCONN_IDPACKING in", values, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingNotIn(List<Integer> values) {
            addCriterion("PCONN_IDPACKING not in", values, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_IDPACKING between", value1, value2, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdpackingNotBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_IDPACKING not between", value1, value2, "pconnIdpacking");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorIsNull() {
            addCriterion("PCONN_IDCONTENEDOR is null");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorIsNotNull() {
            addCriterion("PCONN_IDCONTENEDOR is not null");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorEqualTo(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR =", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorNotEqualTo(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR <>", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorGreaterThan(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR >", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorGreaterThanOrEqualTo(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR >=", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorLessThan(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR <", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorLessThanOrEqualTo(Integer value) {
            addCriterion("PCONN_IDCONTENEDOR <=", value, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorIn(List<Integer> values) {
            addCriterion("PCONN_IDCONTENEDOR in", values, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorNotIn(List<Integer> values) {
            addCriterion("PCONN_IDCONTENEDOR not in", values, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_IDCONTENEDOR between", value1, value2, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconnIdcontenedorNotBetween(Integer value1, Integer value2) {
            addCriterion("PCONN_IDCONTENEDOR not between", value1, value2, "pconnIdcontenedor");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionIsNull() {
            addCriterion("PCONV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionIsNotNull() {
            addCriterion("PCONV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionEqualTo(String value) {
            addCriterion("PCONV_USUARIOCREACION =", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionNotEqualTo(String value) {
            addCriterion("PCONV_USUARIOCREACION <>", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionGreaterThan(String value) {
            addCriterion("PCONV_USUARIOCREACION >", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("PCONV_USUARIOCREACION >=", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionLessThan(String value) {
            addCriterion("PCONV_USUARIOCREACION <", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("PCONV_USUARIOCREACION <=", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionLike(String value) {
            addCriterion("PCONV_USUARIOCREACION like", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionNotLike(String value) {
            addCriterion("PCONV_USUARIOCREACION not like", value, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionIn(List<String> values) {
            addCriterion("PCONV_USUARIOCREACION in", values, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionNotIn(List<String> values) {
            addCriterion("PCONV_USUARIOCREACION not in", values, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("PCONV_USUARIOCREACION between", value1, value2, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("PCONV_USUARIOCREACION not between", value1, value2, "pconvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionIsNull() {
            addCriterion("PCOND_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionIsNotNull() {
            addCriterion("PCOND_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionEqualTo(Date value) {
            addCriterion("PCOND_FECHACREACION =", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionNotEqualTo(Date value) {
            addCriterion("PCOND_FECHACREACION <>", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionGreaterThan(Date value) {
            addCriterion("PCOND_FECHACREACION >", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("PCOND_FECHACREACION >=", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionLessThan(Date value) {
            addCriterion("PCOND_FECHACREACION <", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("PCOND_FECHACREACION <=", value, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionIn(List<Date> values) {
            addCriterion("PCOND_FECHACREACION in", values, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionNotIn(List<Date> values) {
            addCriterion("PCOND_FECHACREACION not in", values, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionBetween(Date value1, Date value2) {
            addCriterion("PCOND_FECHACREACION between", value1, value2, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("PCOND_FECHACREACION not between", value1, value2, "pcondFechacreacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionIsNull() {
            addCriterion("PCONV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionIsNotNull() {
            addCriterion("PCONV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionEqualTo(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION =", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION <>", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionGreaterThan(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION >", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION >=", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionLessThan(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION <", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION <=", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionLike(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION like", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionNotLike(String value) {
            addCriterion("PCONV_USUARIOMODIFICACION not like", value, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionIn(List<String> values) {
            addCriterion("PCONV_USUARIOMODIFICACION in", values, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("PCONV_USUARIOMODIFICACION not in", values, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("PCONV_USUARIOMODIFICACION between", value1, value2, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPconvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("PCONV_USUARIOMODIFICACION not between", value1, value2, "pconvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionIsNull() {
            addCriterion("PCOND_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionIsNotNull() {
            addCriterion("PCOND_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionEqualTo(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION =", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionNotEqualTo(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION <>", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionGreaterThan(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION >", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION >=", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionLessThan(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION <", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("PCOND_FECHAMODIFICACION <=", value, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionIn(List<Date> values) {
            addCriterion("PCOND_FECHAMODIFICACION in", values, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionNotIn(List<Date> values) {
            addCriterion("PCOND_FECHAMODIFICACION not in", values, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("PCOND_FECHAMODIFICACION between", value1, value2, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPcondFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("PCOND_FECHAMODIFICACION not between", value1, value2, "pcondFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoIsNull() {
            addCriterion("PCONN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoIsNotNull() {
            addCriterion("PCONN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoEqualTo(Short value) {
            addCriterion("PCONN_ESTADO =", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoNotEqualTo(Short value) {
            addCriterion("PCONN_ESTADO <>", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoGreaterThan(Short value) {
            addCriterion("PCONN_ESTADO >", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("PCONN_ESTADO >=", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoLessThan(Short value) {
            addCriterion("PCONN_ESTADO <", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoLessThanOrEqualTo(Short value) {
            addCriterion("PCONN_ESTADO <=", value, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoIn(List<Short> values) {
            addCriterion("PCONN_ESTADO in", values, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoNotIn(List<Short> values) {
            addCriterion("PCONN_ESTADO not in", values, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoBetween(Short value1, Short value2) {
            addCriterion("PCONN_ESTADO between", value1, value2, "pconnEstado");
            return (Criteria) this;
        }

        public Criteria andPconnEstadoNotBetween(Short value1, Short value2) {
            addCriterion("PCONN_ESTADO not between", value1, value2, "pconnEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_PACKINGCONTENEDOR
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
     * This class corresponds to the database table COMEXST_PACKINGCONTENEDOR
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