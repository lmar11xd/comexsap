package com.caasa.comexsap.exportaciones.model.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstTipocambioExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public ComexstTipocambioExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
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
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
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
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_TIPOCAMBIO
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
     * This class corresponds to the database table COMEXST_TIPOCAMBIO
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

        public Criteria andTcamnIdIsNull() {
            addCriterion("TCAMN_ID is null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdIsNotNull() {
            addCriterion("TCAMN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdEqualTo(Integer value) {
            addCriterion("TCAMN_ID =", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdNotEqualTo(Integer value) {
            addCriterion("TCAMN_ID <>", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdGreaterThan(Integer value) {
            addCriterion("TCAMN_ID >", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_ID >=", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdLessThan(Integer value) {
            addCriterion("TCAMN_ID <", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdLessThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_ID <=", value, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdIn(List<Integer> values) {
            addCriterion("TCAMN_ID in", values, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdNotIn(List<Integer> values) {
            addCriterion("TCAMN_ID not in", values, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_ID between", value1, value2, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_ID not between", value1, value2, "tcamnId");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenIsNull() {
            addCriterion("TCAMN_IDMONEDAORIGEN is null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenIsNotNull() {
            addCriterion("TCAMN_IDMONEDAORIGEN is not null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN =", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenNotEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN <>", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenGreaterThan(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN >", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenGreaterThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN >=", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenLessThan(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN <", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenLessThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDAORIGEN <=", value, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenIn(List<Integer> values) {
            addCriterion("TCAMN_IDMONEDAORIGEN in", values, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenNotIn(List<Integer> values) {
            addCriterion("TCAMN_IDMONEDAORIGEN not in", values, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_IDMONEDAORIGEN between", value1, value2, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedaorigenNotBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_IDMONEDAORIGEN not between", value1, value2, "tcamnIdmonedaorigen");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoIsNull() {
            addCriterion("TCAMN_IDMONEDADESTINO is null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoIsNotNull() {
            addCriterion("TCAMN_IDMONEDADESTINO is not null");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO =", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoNotEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO <>", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoGreaterThan(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO >", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoGreaterThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO >=", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoLessThan(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO <", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoLessThanOrEqualTo(Integer value) {
            addCriterion("TCAMN_IDMONEDADESTINO <=", value, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoIn(List<Integer> values) {
            addCriterion("TCAMN_IDMONEDADESTINO in", values, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoNotIn(List<Integer> values) {
            addCriterion("TCAMN_IDMONEDADESTINO not in", values, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_IDMONEDADESTINO between", value1, value2, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnIdmonedadestinoNotBetween(Integer value1, Integer value2) {
            addCriterion("TCAMN_IDMONEDADESTINO not between", value1, value2, "tcamnIdmonedadestino");
            return (Criteria) this;
        }

        public Criteria andTcamnValorIsNull() {
            addCriterion("TCAMN_VALOR is null");
            return (Criteria) this;
        }

        public Criteria andTcamnValorIsNotNull() {
            addCriterion("TCAMN_VALOR is not null");
            return (Criteria) this;
        }

        public Criteria andTcamnValorEqualTo(BigDecimal value) {
            addCriterion("TCAMN_VALOR =", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorNotEqualTo(BigDecimal value) {
            addCriterion("TCAMN_VALOR <>", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorGreaterThan(BigDecimal value) {
            addCriterion("TCAMN_VALOR >", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TCAMN_VALOR >=", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorLessThan(BigDecimal value) {
            addCriterion("TCAMN_VALOR <", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TCAMN_VALOR <=", value, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorIn(List<BigDecimal> values) {
            addCriterion("TCAMN_VALOR in", values, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorNotIn(List<BigDecimal> values) {
            addCriterion("TCAMN_VALOR not in", values, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TCAMN_VALOR between", value1, value2, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamnValorNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TCAMN_VALOR not between", value1, value2, "tcamnValor");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaIsNull() {
            addCriterion("TCAMD_FECHA is null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaIsNotNull() {
            addCriterion("TCAMD_FECHA is not null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaEqualTo(Date value) {
            addCriterion("TCAMD_FECHA =", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaNotEqualTo(Date value) {
            addCriterion("TCAMD_FECHA <>", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaGreaterThan(Date value) {
            addCriterion("TCAMD_FECHA >", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaGreaterThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHA >=", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaLessThan(Date value) {
            addCriterion("TCAMD_FECHA <", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaLessThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHA <=", value, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaIn(List<Date> values) {
            addCriterion("TCAMD_FECHA in", values, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaNotIn(List<Date> values) {
            addCriterion("TCAMD_FECHA not in", values, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHA between", value1, value2, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamdFechaNotBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHA not between", value1, value2, "tcamdFecha");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionIsNull() {
            addCriterion("TCAMV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionIsNotNull() {
            addCriterion("TCAMV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionEqualTo(String value) {
            addCriterion("TCAMV_USUARIOCREACION =", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionNotEqualTo(String value) {
            addCriterion("TCAMV_USUARIOCREACION <>", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionGreaterThan(String value) {
            addCriterion("TCAMV_USUARIOCREACION >", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("TCAMV_USUARIOCREACION >=", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionLessThan(String value) {
            addCriterion("TCAMV_USUARIOCREACION <", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("TCAMV_USUARIOCREACION <=", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionLike(String value) {
            addCriterion("TCAMV_USUARIOCREACION like", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionNotLike(String value) {
            addCriterion("TCAMV_USUARIOCREACION not like", value, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionIn(List<String> values) {
            addCriterion("TCAMV_USUARIOCREACION in", values, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionNotIn(List<String> values) {
            addCriterion("TCAMV_USUARIOCREACION not in", values, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("TCAMV_USUARIOCREACION between", value1, value2, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("TCAMV_USUARIOCREACION not between", value1, value2, "tcamvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionIsNull() {
            addCriterion("TCAMD_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionIsNotNull() {
            addCriterion("TCAMD_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionEqualTo(Date value) {
            addCriterion("TCAMD_FECHACREACION =", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionNotEqualTo(Date value) {
            addCriterion("TCAMD_FECHACREACION <>", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionGreaterThan(Date value) {
            addCriterion("TCAMD_FECHACREACION >", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHACREACION >=", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionLessThan(Date value) {
            addCriterion("TCAMD_FECHACREACION <", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHACREACION <=", value, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionIn(List<Date> values) {
            addCriterion("TCAMD_FECHACREACION in", values, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionNotIn(List<Date> values) {
            addCriterion("TCAMD_FECHACREACION not in", values, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHACREACION between", value1, value2, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHACREACION not between", value1, value2, "tcamdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionIsNull() {
            addCriterion("TCAMV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionIsNotNull() {
            addCriterion("TCAMV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionEqualTo(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION =", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION <>", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionGreaterThan(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION >", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION >=", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionLessThan(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION <", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION <=", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionLike(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION like", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionNotLike(String value) {
            addCriterion("TCAMV_USUARIOMODIFICACION not like", value, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionIn(List<String> values) {
            addCriterion("TCAMV_USUARIOMODIFICACION in", values, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("TCAMV_USUARIOMODIFICACION not in", values, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("TCAMV_USUARIOMODIFICACION between", value1, value2, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("TCAMV_USUARIOMODIFICACION not between", value1, value2, "tcamvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionIsNull() {
            addCriterion("TCAMD_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionIsNotNull() {
            addCriterion("TCAMD_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionEqualTo(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION =", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionNotEqualTo(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION <>", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionGreaterThan(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION >", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION >=", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionLessThan(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION <", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("TCAMD_FECHAMODIFICACION <=", value, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionIn(List<Date> values) {
            addCriterion("TCAMD_FECHAMODIFICACION in", values, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionNotIn(List<Date> values) {
            addCriterion("TCAMD_FECHAMODIFICACION not in", values, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHAMODIFICACION between", value1, value2, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamdFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("TCAMD_FECHAMODIFICACION not between", value1, value2, "tcamdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoIsNull() {
            addCriterion("TCAMN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoIsNotNull() {
            addCriterion("TCAMN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoEqualTo(Short value) {
            addCriterion("TCAMN_ESTADO =", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoNotEqualTo(Short value) {
            addCriterion("TCAMN_ESTADO <>", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoGreaterThan(Short value) {
            addCriterion("TCAMN_ESTADO >", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("TCAMN_ESTADO >=", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoLessThan(Short value) {
            addCriterion("TCAMN_ESTADO <", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoLessThanOrEqualTo(Short value) {
            addCriterion("TCAMN_ESTADO <=", value, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoIn(List<Short> values) {
            addCriterion("TCAMN_ESTADO in", values, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoNotIn(List<Short> values) {
            addCriterion("TCAMN_ESTADO not in", values, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoBetween(Short value1, Short value2) {
            addCriterion("TCAMN_ESTADO between", value1, value2, "tcamnEstado");
            return (Criteria) this;
        }

        public Criteria andTcamnEstadoNotBetween(Short value1, Short value2) {
            addCriterion("TCAMN_ESTADO not between", value1, value2, "tcamnEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_TIPOCAMBIO
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
     * This class corresponds to the database table COMEXST_TIPOCAMBIO
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