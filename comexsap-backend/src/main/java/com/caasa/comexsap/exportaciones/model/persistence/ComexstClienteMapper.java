package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ComexstCliente;
import com.caasa.comexsap.exportaciones.model.domain.ComexstClienteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComexstClienteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    long countByExample(ComexstClienteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer clienId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int insert(ComexstCliente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int insertSelective(ComexstCliente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    List<ComexstCliente> selectByExample(ComexstClienteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    ComexstCliente selectByPrimaryKey(Integer clienId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComexstCliente record, @Param("example") ComexstClienteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComexstCliente record, @Param("example") ComexstClienteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComexstCliente record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CLIENTE
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComexstCliente record);
}