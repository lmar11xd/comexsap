package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ComexstPuerto;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPuertoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComexstPuertoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    long countByExample(ComexstPuertoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer puernId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int insert(ComexstPuerto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int insertSelective(ComexstPuerto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    List<ComexstPuerto> selectByExample(ComexstPuertoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    ComexstPuerto selectByPrimaryKey(Integer puernId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComexstPuerto record, @Param("example") ComexstPuertoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComexstPuerto record, @Param("example") ComexstPuertoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComexstPuerto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PUERTO
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComexstPuerto record);
}