package com.particle.global.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 原生sql mapper
 * 注意：原生是支持 #{} 方式取值的，也就是在 paramSQL中可以使用 #{}取值
 * </p>
 *
 * @author yangwei
 * @since 2021-01-23 18:27
 */
@Mapper
public interface NativeSqlMapper {

    public static final String paramSQL = "paramSQL";
    public static final String paramSQLExpressin = "${" + paramSQL + "}";

    @Insert(paramSQLExpressin)
    int insert(@Param(paramSQL) String sql);

    @Update(paramSQLExpressin)
    int update(@Param(paramSQL) String sql);

    @Delete(paramSQLExpressin)
    int delete(@Param(paramSQL) String sql);

    @Select(paramSQLExpressin)
    Map<String,Object> selectOneBySql(@Param(paramSQL) String sql);

    @Select(paramSQLExpressin)
    Map<String,Object> selectOne(@Param(paramSQL) String sql,@Param("data") Object data);

    @Select(paramSQLExpressin)
    List<Map<String,Object>> selectListBySql(@Param(paramSQL) String sql);

    @Select(paramSQLExpressin)
    List<Map<String,Object>> selectList(@Param(paramSQL) String sql,@Param("data") Object data);

    @Select(paramSQLExpressin)
    <P extends IPage<Map<String, Object>>> P selectPageBySql(P page, @Param(paramSQL) String sql);


    @Select(paramSQLExpressin)
    <P extends IPage<Map<String, Object>>> P selectPage(P page, @Param(paramSQL) String sql,@Param("data") Object data);

    @Select("SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}")
    Map<String,Object> selectOneByMyWrapper(@Param("tableName") String tableName,@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}")
    List<Map<String,Object>> selectListByMyWrapper(@Param("tableName") String tableName,@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT ${ew.sqlSelect} FROM ${tableName} ${ew.customSqlSegment}")
    <P extends IPage<Map<String, Object>>> P selectPageByMyWrapper(P page, @Param("tableName") String tableName,@Param(Constants.WRAPPER) Wrapper wrapper);

}
