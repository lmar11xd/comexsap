package com.caasa.comexsap.exportaciones.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstCarpetadocumentoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public ComexstCarpetadocumentoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
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
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
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
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
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
     * This class corresponds to the database table COMEXST_CARPETADOCUMENTO
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

        public Criteria andCdocnIdIsNull() {
            addCriterion("CDOCN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCdocnIdIsNotNull() {
            addCriterion("CDOCN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCdocnIdEqualTo(Integer value) {
            addCriterion("CDOCN_ID =", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdNotEqualTo(Integer value) {
            addCriterion("CDOCN_ID <>", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdGreaterThan(Integer value) {
            addCriterion("CDOCN_ID >", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CDOCN_ID >=", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdLessThan(Integer value) {
            addCriterion("CDOCN_ID <", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdLessThanOrEqualTo(Integer value) {
            addCriterion("CDOCN_ID <=", value, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdIn(List<Integer> values) {
            addCriterion("CDOCN_ID in", values, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdNotIn(List<Integer> values) {
            addCriterion("CDOCN_ID not in", values, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdBetween(Integer value1, Integer value2) {
            addCriterion("CDOCN_ID between", value1, value2, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CDOCN_ID not between", value1, value2, "cdocnId");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaIsNull() {
            addCriterion("CDOCN_IDCARPETA is null");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaIsNotNull() {
            addCriterion("CDOCN_IDCARPETA is not null");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaEqualTo(Integer value) {
            addCriterion("CDOCN_IDCARPETA =", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaNotEqualTo(Integer value) {
            addCriterion("CDOCN_IDCARPETA <>", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaGreaterThan(Integer value) {
            addCriterion("CDOCN_IDCARPETA >", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaGreaterThanOrEqualTo(Integer value) {
            addCriterion("CDOCN_IDCARPETA >=", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaLessThan(Integer value) {
            addCriterion("CDOCN_IDCARPETA <", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaLessThanOrEqualTo(Integer value) {
            addCriterion("CDOCN_IDCARPETA <=", value, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaIn(List<Integer> values) {
            addCriterion("CDOCN_IDCARPETA in", values, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaNotIn(List<Integer> values) {
            addCriterion("CDOCN_IDCARPETA not in", values, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaBetween(Integer value1, Integer value2) {
            addCriterion("CDOCN_IDCARPETA between", value1, value2, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocnIdcarpetaNotBetween(Integer value1, Integer value2) {
            addCriterion("CDOCN_IDCARPETA not between", value1, value2, "cdocnIdcarpeta");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidIsNull() {
            addCriterion("CDOCV_NODEID is null");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidIsNotNull() {
            addCriterion("CDOCV_NODEID is not null");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidEqualTo(String value) {
            addCriterion("CDOCV_NODEID =", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidNotEqualTo(String value) {
            addCriterion("CDOCV_NODEID <>", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidGreaterThan(String value) {
            addCriterion("CDOCV_NODEID >", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidGreaterThanOrEqualTo(String value) {
            addCriterion("CDOCV_NODEID >=", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidLessThan(String value) {
            addCriterion("CDOCV_NODEID <", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidLessThanOrEqualTo(String value) {
            addCriterion("CDOCV_NODEID <=", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidLike(String value) {
            addCriterion("CDOCV_NODEID like", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidNotLike(String value) {
            addCriterion("CDOCV_NODEID not like", value, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidIn(List<String> values) {
            addCriterion("CDOCV_NODEID in", values, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidNotIn(List<String> values) {
            addCriterion("CDOCV_NODEID not in", values, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidBetween(String value1, String value2) {
            addCriterion("CDOCV_NODEID between", value1, value2, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNodeidNotBetween(String value1, String value2) {
            addCriterion("CDOCV_NODEID not between", value1, value2, "cdocvNodeid");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreIsNull() {
            addCriterion("CDOCV_NOMBRE is null");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreIsNotNull() {
            addCriterion("CDOCV_NOMBRE is not null");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreEqualTo(String value) {
            addCriterion("CDOCV_NOMBRE =", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreNotEqualTo(String value) {
            addCriterion("CDOCV_NOMBRE <>", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreGreaterThan(String value) {
            addCriterion("CDOCV_NOMBRE >", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreGreaterThanOrEqualTo(String value) {
            addCriterion("CDOCV_NOMBRE >=", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreLessThan(String value) {
            addCriterion("CDOCV_NOMBRE <", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreLessThanOrEqualTo(String value) {
            addCriterion("CDOCV_NOMBRE <=", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreLike(String value) {
            addCriterion("CDOCV_NOMBRE like", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreNotLike(String value) {
            addCriterion("CDOCV_NOMBRE not like", value, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreIn(List<String> values) {
            addCriterion("CDOCV_NOMBRE in", values, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreNotIn(List<String> values) {
            addCriterion("CDOCV_NOMBRE not in", values, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreBetween(String value1, String value2) {
            addCriterion("CDOCV_NOMBRE between", value1, value2, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvNombreNotBetween(String value1, String value2) {
            addCriterion("CDOCV_NOMBRE not between", value1, value2, "cdocvNombre");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionIsNull() {
            addCriterion("CDOCV_INFORMACION is null");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionIsNotNull() {
            addCriterion("CDOCV_INFORMACION is not null");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionEqualTo(String value) {
            addCriterion("CDOCV_INFORMACION =", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionNotEqualTo(String value) {
            addCriterion("CDOCV_INFORMACION <>", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionGreaterThan(String value) {
            addCriterion("CDOCV_INFORMACION >", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionGreaterThanOrEqualTo(String value) {
            addCriterion("CDOCV_INFORMACION >=", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionLessThan(String value) {
            addCriterion("CDOCV_INFORMACION <", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionLessThanOrEqualTo(String value) {
            addCriterion("CDOCV_INFORMACION <=", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionLike(String value) {
            addCriterion("CDOCV_INFORMACION like", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionNotLike(String value) {
            addCriterion("CDOCV_INFORMACION not like", value, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionIn(List<String> values) {
            addCriterion("CDOCV_INFORMACION in", values, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionNotIn(List<String> values) {
            addCriterion("CDOCV_INFORMACION not in", values, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionBetween(String value1, String value2) {
            addCriterion("CDOCV_INFORMACION between", value1, value2, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvInformacionNotBetween(String value1, String value2) {
            addCriterion("CDOCV_INFORMACION not between", value1, value2, "cdocvInformacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionIsNull() {
            addCriterion("CDOCV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionIsNotNull() {
            addCriterion("CDOCV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionEqualTo(String value) {
            addCriterion("CDOCV_USUARIOCREACION =", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionNotEqualTo(String value) {
            addCriterion("CDOCV_USUARIOCREACION <>", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionGreaterThan(String value) {
            addCriterion("CDOCV_USUARIOCREACION >", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("CDOCV_USUARIOCREACION >=", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionLessThan(String value) {
            addCriterion("CDOCV_USUARIOCREACION <", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("CDOCV_USUARIOCREACION <=", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionLike(String value) {
            addCriterion("CDOCV_USUARIOCREACION like", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionNotLike(String value) {
            addCriterion("CDOCV_USUARIOCREACION not like", value, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionIn(List<String> values) {
            addCriterion("CDOCV_USUARIOCREACION in", values, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionNotIn(List<String> values) {
            addCriterion("CDOCV_USUARIOCREACION not in", values, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("CDOCV_USUARIOCREACION between", value1, value2, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("CDOCV_USUARIOCREACION not between", value1, value2, "cdocvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionIsNull() {
            addCriterion("CDOCD_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionIsNotNull() {
            addCriterion("CDOCD_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionEqualTo(Date value) {
            addCriterion("CDOCD_FECHACREACION =", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionNotEqualTo(Date value) {
            addCriterion("CDOCD_FECHACREACION <>", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionGreaterThan(Date value) {
            addCriterion("CDOCD_FECHACREACION >", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("CDOCD_FECHACREACION >=", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionLessThan(Date value) {
            addCriterion("CDOCD_FECHACREACION <", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("CDOCD_FECHACREACION <=", value, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionIn(List<Date> values) {
            addCriterion("CDOCD_FECHACREACION in", values, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionNotIn(List<Date> values) {
            addCriterion("CDOCD_FECHACREACION not in", values, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionBetween(Date value1, Date value2) {
            addCriterion("CDOCD_FECHACREACION between", value1, value2, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("CDOCD_FECHACREACION not between", value1, value2, "cdocdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionIsNull() {
            addCriterion("CDOCV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionIsNotNull() {
            addCriterion("CDOCV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionEqualTo(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION =", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION <>", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionGreaterThan(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION >", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION >=", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionLessThan(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION <", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION <=", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionLike(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION like", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionNotLike(String value) {
            addCriterion("CDOCV_USUARIOMODIFICACION not like", value, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionIn(List<String> values) {
            addCriterion("CDOCV_USUARIOMODIFICACION in", values, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("CDOCV_USUARIOMODIFICACION not in", values, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("CDOCV_USUARIOMODIFICACION between", value1, value2, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("CDOCV_USUARIOMODIFICACION not between", value1, value2, "cdocvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionIsNull() {
            addCriterion("CDOCD_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionIsNotNull() {
            addCriterion("CDOCD_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionEqualTo(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION =", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionNotEqualTo(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION <>", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionGreaterThan(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION >", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION >=", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionLessThan(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION <", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("CDOCD_FECHAMODIFICACION <=", value, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionIn(List<Date> values) {
            addCriterion("CDOCD_FECHAMODIFICACION in", values, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionNotIn(List<Date> values) {
            addCriterion("CDOCD_FECHAMODIFICACION not in", values, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("CDOCD_FECHAMODIFICACION between", value1, value2, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocdFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("CDOCD_FECHAMODIFICACION not between", value1, value2, "cdocdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoIsNull() {
            addCriterion("CDOCN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoIsNotNull() {
            addCriterion("CDOCN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoEqualTo(Short value) {
            addCriterion("CDOCN_ESTADO =", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoNotEqualTo(Short value) {
            addCriterion("CDOCN_ESTADO <>", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoGreaterThan(Short value) {
            addCriterion("CDOCN_ESTADO >", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("CDOCN_ESTADO >=", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoLessThan(Short value) {
            addCriterion("CDOCN_ESTADO <", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoLessThanOrEqualTo(Short value) {
            addCriterion("CDOCN_ESTADO <=", value, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoIn(List<Short> values) {
            addCriterion("CDOCN_ESTADO in", values, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoNotIn(List<Short> values) {
            addCriterion("CDOCN_ESTADO not in", values, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoBetween(Short value1, Short value2) {
            addCriterion("CDOCN_ESTADO between", value1, value2, "cdocnEstado");
            return (Criteria) this;
        }

        public Criteria andCdocnEstadoNotBetween(Short value1, Short value2) {
            addCriterion("CDOCN_ESTADO not between", value1, value2, "cdocnEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_CARPETADOCUMENTO
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
     * This class corresponds to the database table COMEXST_CARPETADOCUMENTO
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