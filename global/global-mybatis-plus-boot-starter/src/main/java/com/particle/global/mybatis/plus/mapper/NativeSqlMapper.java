package com.particle.global.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 原生sql mapper
 * </p>
 *
 * @author yangwei
 * @since 2021-01-23 18:27
 */
public interface NativeSqlMapper {

    @Insert("${paramSQL}")
    int insert(@Param("paramSQL") String sql);

    @Update("${paramSQL}")
    int update(@Param("paramSQL") String sql);

    @Delete("${paramSQL}")
    int delete(@Param("paramSQL") String sql);

    @Select("${paramSQL}")
    Map<String,Object> selectOne(@Param("paramSQL") String sql);

    @Select("${paramSQL}")
    List<Map<String,Object>> selectList(@Param("paramSQL") String sql);

    @Select("SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}")
    Map<String,Object> selectOneByMyWrapper(@Param("tableName") String tableName,@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}")
    List<Map<String,Object>> selectListByMyWrapper(@Param("tableName") String tableName,@Param(Constants.WRAPPER) Wrapper wrapper);

}
