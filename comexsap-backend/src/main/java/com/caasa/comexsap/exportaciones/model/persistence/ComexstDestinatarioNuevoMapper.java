package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ComexstDestinatarioNuevo;
import com.caasa.comexsap.exportaciones.model.domain.ComexstDestinatarioNuevoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComexstDestinatarioNuevoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    long countByExample(ComexstDestinatarioNuevoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer destnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int insert(ComexstDestinatarioNuevo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int insertSelective(ComexstDestinatarioNuevo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    List<ComexstDestinatarioNuevo> selectByExample(ComexstDestinatarioNuevoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    ComexstDestinatarioNuevo selectByPrimaryKey(Integer destnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComexstDestinatarioNuevo record, @Param("example") ComexstDestinatarioNuevoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComexstDestinatarioNuevo record, @Param("example") ComexstDestinatarioNuevoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComexstDestinatarioNuevo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_DESTINATARIO_NUEVO
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComexstDestinatarioNuevo record);
}