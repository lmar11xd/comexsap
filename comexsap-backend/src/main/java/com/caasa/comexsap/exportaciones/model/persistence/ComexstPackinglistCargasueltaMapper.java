package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ComexstPackinglistCargasuelta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPackinglistCargasueltaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComexstPackinglistCargasueltaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    long countByExample(ComexstPackinglistCargasueltaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer plcsnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int insert(ComexstPackinglistCargasuelta record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int insertSelective(ComexstPackinglistCargasuelta record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    List<ComexstPackinglistCargasuelta> selectByExample(ComexstPackinglistCargasueltaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    ComexstPackinglistCargasuelta selectByPrimaryKey(Integer plcsnId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ComexstPackinglistCargasuelta record, @Param("example") ComexstPackinglistCargasueltaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ComexstPackinglistCargasuelta record, @Param("example") ComexstPackinglistCargasueltaExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ComexstPackinglistCargasuelta record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COMEXST_PACKINGLIST_CARGASUELTA
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ComexstPackinglistCargasuelta record);
}