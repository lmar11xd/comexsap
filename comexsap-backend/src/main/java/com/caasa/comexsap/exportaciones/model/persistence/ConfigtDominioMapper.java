package com.caasa.comexsap.exportaciones.model.persistence;

import com.caasa.comexsap.exportaciones.model.domain.ConfigtDominio;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtDominioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigtDominioMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    long countByExample(ConfigtDominioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer idDominio);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int insert(ConfigtDominio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int insertSelective(ConfigtDominio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    List<ConfigtDominio> selectByExample(ConfigtDominioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    ConfigtDominio selectByPrimaryKey(Integer idDominio);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ConfigtDominio record, @Param("example") ConfigtDominioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ConfigtDominio record, @Param("example") ConfigtDominioExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ConfigtDominio record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CONFIGT_DOMINIO
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ConfigtDominio record);
}