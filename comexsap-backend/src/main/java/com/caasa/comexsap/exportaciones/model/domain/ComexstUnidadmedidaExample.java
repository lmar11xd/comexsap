package com.caasa.comexsap.exportaciones.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstUnidadmedidaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public ComexstUnidadmedidaExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
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
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
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
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_UNIDADMEDIDA
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
     * This class corresponds to the database table COMEXST_UNIDADMEDIDA
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

        public Criteria andUmednIdIsNull() {
            addCriterion("UMEDN_ID is null");
            return (Criteria) this;
        }

        public Criteria andUmednIdIsNotNull() {
            addCriterion("UMEDN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUmednIdEqualTo(Integer value) {
            addCriterion("UMEDN_ID =", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdNotEqualTo(Integer value) {
            addCriterion("UMEDN_ID <>", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdGreaterThan(Integer value) {
            addCriterion("UMEDN_ID >", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("UMEDN_ID >=", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdLessThan(Integer value) {
            addCriterion("UMEDN_ID <", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdLessThanOrEqualTo(Integer value) {
            addCriterion("UMEDN_ID <=", value, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdIn(List<Integer> values) {
            addCriterion("UMEDN_ID in", values, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdNotIn(List<Integer> values) {
            addCriterion("UMEDN_ID not in", values, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdBetween(Integer value1, Integer value2) {
            addCriterion("UMEDN_ID between", value1, value2, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmednIdNotBetween(Integer value1, Integer value2) {
            addCriterion("UMEDN_ID not between", value1, value2, "umednId");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapIsNull() {
            addCriterion("UMEDV_CODIGOSAP is null");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapIsNotNull() {
            addCriterion("UMEDV_CODIGOSAP is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapEqualTo(String value) {
            addCriterion("UMEDV_CODIGOSAP =", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapNotEqualTo(String value) {
            addCriterion("UMEDV_CODIGOSAP <>", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapGreaterThan(String value) {
            addCriterion("UMEDV_CODIGOSAP >", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_CODIGOSAP >=", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapLessThan(String value) {
            addCriterion("UMEDV_CODIGOSAP <", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_CODIGOSAP <=", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapLike(String value) {
            addCriterion("UMEDV_CODIGOSAP like", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapNotLike(String value) {
            addCriterion("UMEDV_CODIGOSAP not like", value, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapIn(List<String> values) {
            addCriterion("UMEDV_CODIGOSAP in", values, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapNotIn(List<String> values) {
            addCriterion("UMEDV_CODIGOSAP not in", values, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapBetween(String value1, String value2) {
            addCriterion("UMEDV_CODIGOSAP between", value1, value2, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvCodigosapNotBetween(String value1, String value2) {
            addCriterion("UMEDV_CODIGOSAP not between", value1, value2, "umedvCodigosap");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaIsNull() {
            addCriterion("UMEDV_UNIDADMEDIDA is null");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaIsNotNull() {
            addCriterion("UMEDV_UNIDADMEDIDA is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaEqualTo(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA =", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaNotEqualTo(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA <>", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaGreaterThan(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA >", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA >=", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaLessThan(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA <", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA <=", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaLike(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA like", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaNotLike(String value) {
            addCriterion("UMEDV_UNIDADMEDIDA not like", value, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaIn(List<String> values) {
            addCriterion("UMEDV_UNIDADMEDIDA in", values, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaNotIn(List<String> values) {
            addCriterion("UMEDV_UNIDADMEDIDA not in", values, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaBetween(String value1, String value2) {
            addCriterion("UMEDV_UNIDADMEDIDA between", value1, value2, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvUnidadmedidaNotBetween(String value1, String value2) {
            addCriterion("UMEDV_UNIDADMEDIDA not between", value1, value2, "umedvUnidadmedida");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorIsNull() {
            addCriterion("UMEDV_CANTIDADNUMERADOR is null");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorIsNotNull() {
            addCriterion("UMEDV_CANTIDADNUMERADOR is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR =", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorNotEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR <>", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorGreaterThan(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR >", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR >=", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorLessThan(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR <", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR <=", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorLike(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR like", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorNotLike(String value) {
            addCriterion("UMEDV_CANTIDADNUMERADOR not like", value, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorIn(List<String> values) {
            addCriterion("UMEDV_CANTIDADNUMERADOR in", values, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorNotIn(List<String> values) {
            addCriterion("UMEDV_CANTIDADNUMERADOR not in", values, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorBetween(String value1, String value2) {
            addCriterion("UMEDV_CANTIDADNUMERADOR between", value1, value2, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidadnumeradorNotBetween(String value1, String value2) {
            addCriterion("UMEDV_CANTIDADNUMERADOR not between", value1, value2, "umedvCantidadnumerador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorIsNull() {
            addCriterion("UMEDV_CANTIDADDENOMINADOR is null");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorIsNotNull() {
            addCriterion("UMEDV_CANTIDADDENOMINADOR is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR =", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorNotEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR <>", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorGreaterThan(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR >", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR >=", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorLessThan(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR <", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR <=", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorLike(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR like", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorNotLike(String value) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR not like", value, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorIn(List<String> values) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR in", values, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorNotIn(List<String> values) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR not in", values, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorBetween(String value1, String value2) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR between", value1, value2, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvCantidaddenominadorNotBetween(String value1, String value2) {
            addCriterion("UMEDV_CANTIDADDENOMINADOR not between", value1, value2, "umedvCantidaddenominador");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionIsNull() {
            addCriterion("UMEDV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionIsNotNull() {
            addCriterion("UMEDV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionEqualTo(String value) {
            addCriterion("UMEDV_USUARIOCREACION =", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionNotEqualTo(String value) {
            addCriterion("UMEDV_USUARIOCREACION <>", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionGreaterThan(String value) {
            addCriterion("UMEDV_USUARIOCREACION >", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_USUARIOCREACION >=", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionLessThan(String value) {
            addCriterion("UMEDV_USUARIOCREACION <", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_USUARIOCREACION <=", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionLike(String value) {
            addCriterion("UMEDV_USUARIOCREACION like", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionNotLike(String value) {
            addCriterion("UMEDV_USUARIOCREACION not like", value, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionIn(List<String> values) {
            addCriterion("UMEDV_USUARIOCREACION in", values, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionNotIn(List<String> values) {
            addCriterion("UMEDV_USUARIOCREACION not in", values, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("UMEDV_USUARIOCREACION between", value1, value2, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("UMEDV_USUARIOCREACION not between", value1, value2, "umedvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionIsNull() {
            addCriterion("UMEDD_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionIsNotNull() {
            addCriterion("UMEDD_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionEqualTo(Date value) {
            addCriterion("UMEDD_FECHACREACION =", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionNotEqualTo(Date value) {
            addCriterion("UMEDD_FECHACREACION <>", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionGreaterThan(Date value) {
            addCriterion("UMEDD_FECHACREACION >", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("UMEDD_FECHACREACION >=", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionLessThan(Date value) {
            addCriterion("UMEDD_FECHACREACION <", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("UMEDD_FECHACREACION <=", value, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionIn(List<Date> values) {
            addCriterion("UMEDD_FECHACREACION in", values, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionNotIn(List<Date> values) {
            addCriterion("UMEDD_FECHACREACION not in", values, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionBetween(Date value1, Date value2) {
            addCriterion("UMEDD_FECHACREACION between", value1, value2, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("UMEDD_FECHACREACION not between", value1, value2, "umeddFechacreacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionIsNull() {
            addCriterion("UMEDV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionIsNotNull() {
            addCriterion("UMEDV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionEqualTo(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION =", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION <>", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionGreaterThan(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION >", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION >=", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionLessThan(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION <", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION <=", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionLike(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION like", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionNotLike(String value) {
            addCriterion("UMEDV_USUARIOMODIFICACION not like", value, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionIn(List<String> values) {
            addCriterion("UMEDV_USUARIOMODIFICACION in", values, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("UMEDV_USUARIOMODIFICACION not in", values, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("UMEDV_USUARIOMODIFICACION between", value1, value2, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmedvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("UMEDV_USUARIOMODIFICACION not between", value1, value2, "umedvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionIsNull() {
            addCriterion("UMEDD_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionIsNotNull() {
            addCriterion("UMEDD_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionEqualTo(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION =", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionNotEqualTo(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION <>", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionGreaterThan(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION >", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION >=", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionLessThan(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION <", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("UMEDD_FECHAMODIFICACION <=", value, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionIn(List<Date> values) {
            addCriterion("UMEDD_FECHAMODIFICACION in", values, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionNotIn(List<Date> values) {
            addCriterion("UMEDD_FECHAMODIFICACION not in", values, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("UMEDD_FECHAMODIFICACION between", value1, value2, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmeddFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("UMEDD_FECHAMODIFICACION not between", value1, value2, "umeddFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoIsNull() {
            addCriterion("UMEDN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoIsNotNull() {
            addCriterion("UMEDN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoEqualTo(Short value) {
            addCriterion("UMEDN_ESTADO =", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoNotEqualTo(Short value) {
            addCriterion("UMEDN_ESTADO <>", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoGreaterThan(Short value) {
            addCriterion("UMEDN_ESTADO >", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("UMEDN_ESTADO >=", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoLessThan(Short value) {
            addCriterion("UMEDN_ESTADO <", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoLessThanOrEqualTo(Short value) {
            addCriterion("UMEDN_ESTADO <=", value, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoIn(List<Short> values) {
            addCriterion("UMEDN_ESTADO in", values, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoNotIn(List<Short> values) {
            addCriterion("UMEDN_ESTADO not in", values, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoBetween(Short value1, Short value2) {
            addCriterion("UMEDN_ESTADO between", value1, value2, "umednEstado");
            return (Criteria) this;
        }

        public Criteria andUmednEstadoNotBetween(Short value1, Short value2) {
            addCriterion("UMEDN_ESTADO not between", value1, value2, "umednEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_UNIDADMEDIDA
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
     * This class corresponds to the database table COMEXST_UNIDADMEDIDA
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