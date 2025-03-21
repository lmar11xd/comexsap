package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpetadocumento;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpetadocumentoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComexstCarpetadocumentoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    long countByExample(ComexstCarpetadocumentoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer cdocnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int insert(ComexstCarpetadocumento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int insertSelective(ComexstCarpetadocumento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    List<ComexstCarpetadocumento> selectByExample(ComexstCarpetadocumentoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    ComexstCarpetadocumento selectByPrimaryKey(Integer cdocnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComexstCarpetadocumento record, @Param("example") ComexstCarpetadocumentoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComexstCarpetadocumento record, @Param("example") ComexstCarpetadocumentoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComexstCarpetadocumento record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_CARPETADOCUMENTO
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComexstCarpetadocumento record);
}