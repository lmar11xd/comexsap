package com.caasa.comexsap.exportaciones.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComexstProveedorExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public ComexstProveedorExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
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
     * This method corresponds to the database table COMEXST_PROVEEDOR
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
     * This method corresponds to the database table COMEXST_PROVEEDOR
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PROVEEDOR
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
     * This class corresponds to the database table COMEXST_PROVEEDOR
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

        public Criteria andProvnIdIsNull() {
            addCriterion("PROVN_ID is null");
            return (Criteria) this;
        }

        public Criteria andProvnIdIsNotNull() {
            addCriterion("PROVN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProvnIdEqualTo(Integer value) {
            addCriterion("PROVN_ID =", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdNotEqualTo(Integer value) {
            addCriterion("PROVN_ID <>", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdGreaterThan(Integer value) {
            addCriterion("PROVN_ID >", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROVN_ID >=", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdLessThan(Integer value) {
            addCriterion("PROVN_ID <", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdLessThanOrEqualTo(Integer value) {
            addCriterion("PROVN_ID <=", value, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdIn(List<Integer> values) {
            addCriterion("PROVN_ID in", values, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdNotIn(List<Integer> values) {
            addCriterion("PROVN_ID not in", values, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdBetween(Integer value1, Integer value2) {
            addCriterion("PROVN_ID between", value1, value2, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvnIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PROVN_ID not between", value1, value2, "provnId");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapIsNull() {
            addCriterion("PROVV_CODIGOSAP is null");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapIsNotNull() {
            addCriterion("PROVV_CODIGOSAP is not null");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapEqualTo(String value) {
            addCriterion("PROVV_CODIGOSAP =", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapNotEqualTo(String value) {
            addCriterion("PROVV_CODIGOSAP <>", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapGreaterThan(String value) {
            addCriterion("PROVV_CODIGOSAP >", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapGreaterThanOrEqualTo(String value) {
            addCriterion("PROVV_CODIGOSAP >=", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapLessThan(String value) {
            addCriterion("PROVV_CODIGOSAP <", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapLessThanOrEqualTo(String value) {
            addCriterion("PROVV_CODIGOSAP <=", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapLike(String value) {
            addCriterion("PROVV_CODIGOSAP like", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapNotLike(String value) {
            addCriterion("PROVV_CODIGOSAP not like", value, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapIn(List<String> values) {
            addCriterion("PROVV_CODIGOSAP in", values, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapNotIn(List<String> values) {
            addCriterion("PROVV_CODIGOSAP not in", values, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapBetween(String value1, String value2) {
            addCriterion("PROVV_CODIGOSAP between", value1, value2, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvCodigosapNotBetween(String value1, String value2) {
            addCriterion("PROVV_CODIGOSAP not between", value1, value2, "provvCodigosap");
            return (Criteria) this;
        }

        public Criteria andProvvNombreIsNull() {
            addCriterion("PROVV_NOMBRE is null");
            return (Criteria) this;
        }

        public Criteria andProvvNombreIsNotNull() {
            addCriterion("PROVV_NOMBRE is not null");
            return (Criteria) this;
        }

        public Criteria andProvvNombreEqualTo(String value) {
            addCriterion("PROVV_NOMBRE =", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreNotEqualTo(String value) {
            addCriterion("PROVV_NOMBRE <>", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreGreaterThan(String value) {
            addCriterion("PROVV_NOMBRE >", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreGreaterThanOrEqualTo(String value) {
            addCriterion("PROVV_NOMBRE >=", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreLessThan(String value) {
            addCriterion("PROVV_NOMBRE <", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreLessThanOrEqualTo(String value) {
            addCriterion("PROVV_NOMBRE <=", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreLike(String value) {
            addCriterion("PROVV_NOMBRE like", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreNotLike(String value) {
            addCriterion("PROVV_NOMBRE not like", value, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreIn(List<String> values) {
            addCriterion("PROVV_NOMBRE in", values, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreNotIn(List<String> values) {
            addCriterion("PROVV_NOMBRE not in", values, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreBetween(String value1, String value2) {
            addCriterion("PROVV_NOMBRE between", value1, value2, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvNombreNotBetween(String value1, String value2) {
            addCriterion("PROVV_NOMBRE not between", value1, value2, "provvNombre");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionIsNull() {
            addCriterion("PROVV_DESCRIPCION is null");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionIsNotNull() {
            addCriterion("PROVV_DESCRIPCION is not null");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionEqualTo(String value) {
            addCriterion("PROVV_DESCRIPCION =", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionNotEqualTo(String value) {
            addCriterion("PROVV_DESCRIPCION <>", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionGreaterThan(String value) {
            addCriterion("PROVV_DESCRIPCION >", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionGreaterThanOrEqualTo(String value) {
            addCriterion("PROVV_DESCRIPCION >=", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionLessThan(String value) {
            addCriterion("PROVV_DESCRIPCION <", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionLessThanOrEqualTo(String value) {
            addCriterion("PROVV_DESCRIPCION <=", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionLike(String value) {
            addCriterion("PROVV_DESCRIPCION like", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionNotLike(String value) {
            addCriterion("PROVV_DESCRIPCION not like", value, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionIn(List<String> values) {
            addCriterion("PROVV_DESCRIPCION in", values, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionNotIn(List<String> values) {
            addCriterion("PROVV_DESCRIPCION not in", values, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionBetween(String value1, String value2) {
            addCriterion("PROVV_DESCRIPCION between", value1, value2, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvDescripcionNotBetween(String value1, String value2) {
            addCriterion("PROVV_DESCRIPCION not between", value1, value2, "provvDescripcion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionIsNull() {
            addCriterion("PROVV_USUARIOCREACION is null");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionIsNotNull() {
            addCriterion("PROVV_USUARIOCREACION is not null");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionEqualTo(String value) {
            addCriterion("PROVV_USUARIOCREACION =", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionNotEqualTo(String value) {
            addCriterion("PROVV_USUARIOCREACION <>", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionGreaterThan(String value) {
            addCriterion("PROVV_USUARIOCREACION >", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionGreaterThanOrEqualTo(String value) {
            addCriterion("PROVV_USUARIOCREACION >=", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionLessThan(String value) {
            addCriterion("PROVV_USUARIOCREACION <", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionLessThanOrEqualTo(String value) {
            addCriterion("PROVV_USUARIOCREACION <=", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionLike(String value) {
            addCriterion("PROVV_USUARIOCREACION like", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionNotLike(String value) {
            addCriterion("PROVV_USUARIOCREACION not like", value, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionIn(List<String> values) {
            addCriterion("PROVV_USUARIOCREACION in", values, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionNotIn(List<String> values) {
            addCriterion("PROVV_USUARIOCREACION not in", values, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionBetween(String value1, String value2) {
            addCriterion("PROVV_USUARIOCREACION between", value1, value2, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariocreacionNotBetween(String value1, String value2) {
            addCriterion("PROVV_USUARIOCREACION not between", value1, value2, "provvUsuariocreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionIsNull() {
            addCriterion("PROVD_FECHACREACION is null");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionIsNotNull() {
            addCriterion("PROVD_FECHACREACION is not null");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionEqualTo(Date value) {
            addCriterion("PROVD_FECHACREACION =", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionNotEqualTo(Date value) {
            addCriterion("PROVD_FECHACREACION <>", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionGreaterThan(Date value) {
            addCriterion("PROVD_FECHACREACION >", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionGreaterThanOrEqualTo(Date value) {
            addCriterion("PROVD_FECHACREACION >=", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionLessThan(Date value) {
            addCriterion("PROVD_FECHACREACION <", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionLessThanOrEqualTo(Date value) {
            addCriterion("PROVD_FECHACREACION <=", value, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionIn(List<Date> values) {
            addCriterion("PROVD_FECHACREACION in", values, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionNotIn(List<Date> values) {
            addCriterion("PROVD_FECHACREACION not in", values, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionBetween(Date value1, Date value2) {
            addCriterion("PROVD_FECHACREACION between", value1, value2, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechacreacionNotBetween(Date value1, Date value2) {
            addCriterion("PROVD_FECHACREACION not between", value1, value2, "provdFechacreacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionIsNull() {
            addCriterion("PROVV_USUARIOMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionIsNotNull() {
            addCriterion("PROVV_USUARIOMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionEqualTo(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION =", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionNotEqualTo(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION <>", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionGreaterThan(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION >", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionGreaterThanOrEqualTo(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION >=", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionLessThan(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION <", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionLessThanOrEqualTo(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION <=", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionLike(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION like", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionNotLike(String value) {
            addCriterion("PROVV_USUARIOMODIFICACION not like", value, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionIn(List<String> values) {
            addCriterion("PROVV_USUARIOMODIFICACION in", values, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionNotIn(List<String> values) {
            addCriterion("PROVV_USUARIOMODIFICACION not in", values, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionBetween(String value1, String value2) {
            addCriterion("PROVV_USUARIOMODIFICACION between", value1, value2, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvvUsuariomodificacionNotBetween(String value1, String value2) {
            addCriterion("PROVV_USUARIOMODIFICACION not between", value1, value2, "provvUsuariomodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionIsNull() {
            addCriterion("PROVD_FECHAMODIFICACION is null");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionIsNotNull() {
            addCriterion("PROVD_FECHAMODIFICACION is not null");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionEqualTo(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION =", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionNotEqualTo(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION <>", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionGreaterThan(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION >", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionGreaterThanOrEqualTo(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION >=", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionLessThan(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION <", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionLessThanOrEqualTo(Date value) {
            addCriterion("PROVD_FECHAMODIFICACION <=", value, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionIn(List<Date> values) {
            addCriterion("PROVD_FECHAMODIFICACION in", values, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionNotIn(List<Date> values) {
            addCriterion("PROVD_FECHAMODIFICACION not in", values, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionBetween(Date value1, Date value2) {
            addCriterion("PROVD_FECHAMODIFICACION between", value1, value2, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvdFechamodificacionNotBetween(Date value1, Date value2) {
            addCriterion("PROVD_FECHAMODIFICACION not between", value1, value2, "provdFechamodificacion");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoIsNull() {
            addCriterion("PROVN_ESTADO is null");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoIsNotNull() {
            addCriterion("PROVN_ESTADO is not null");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoEqualTo(Short value) {
            addCriterion("PROVN_ESTADO =", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoNotEqualTo(Short value) {
            addCriterion("PROVN_ESTADO <>", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoGreaterThan(Short value) {
            addCriterion("PROVN_ESTADO >", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoGreaterThanOrEqualTo(Short value) {
            addCriterion("PROVN_ESTADO >=", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoLessThan(Short value) {
            addCriterion("PROVN_ESTADO <", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoLessThanOrEqualTo(Short value) {
            addCriterion("PROVN_ESTADO <=", value, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoIn(List<Short> values) {
            addCriterion("PROVN_ESTADO in", values, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoNotIn(List<Short> values) {
            addCriterion("PROVN_ESTADO not in", values, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoBetween(Short value1, Short value2) {
            addCriterion("PROVN_ESTADO between", value1, value2, "provnEstado");
            return (Criteria) this;
        }

        public Criteria andProvnEstadoNotBetween(Short value1, Short value2) {
            addCriterion("PROVN_ESTADO not between", value1, value2, "provnEstado");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table COMEXST_PROVEEDOR
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
     * This class corresponds to the database table COMEXST_PROVEEDOR
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