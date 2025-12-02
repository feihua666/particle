package com.particle.global.mybatis.plus.mapper;

import com.particle.global.dto.basic.DO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * mysql操作表的mapper
 * </p>
 *
 * @author yangwei
 * @since 2025/11/5 21:51
 */
@Mapper
public interface MysqlTableMapper {

    /**
     * 创建表
     * 注意：这里多出来的字段需要和{@link DO}中相应静态变量保持一致
     * @param tableName
     * @param comment
     * @return
     */
    @Update("CREATE TABLE IF NOT EXISTS `${tableName}`(\n" +
            "    `id` bigint NOT NULL COMMENT '主键ID',\n" +
            "    `is_public` tinyint(1) NOT NULL COMMENT '是否发布',\n" +
            "    `batch_id` bigint DEFAULT NULL COMMENT '批次id',\n" +
            "    `version` int NOT NULL COMMENT '乐观锁字段',\n" +
            "    `tenant_id` bigint DEFAULT NULL COMMENT '租户id',\n" +
            "    `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',\n" +
            "    `create_by` bigint DEFAULT NULL COMMENT '创建人',\n" +
            "    `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',\n" +
            "    `update_by` bigint DEFAULT NULL COMMENT '修改人',\n" +
            "    PRIMARY KEY (`id`) USING BTREE\n" +
            "    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='${comment}'")
    int createTable(@Param("tableName") String tableName,@Param("comment") String comment);

    @Update("DROP TABLE IF EXISTS `${tableName}`")
    int dropTable(@Param("tableName") String tableName);

    /**
     * 检查表是否存在
     * 这里仅做查询，如果报异常，则认为表不存在
     * @param tableName
     */
    @Select("select 1 from `${tableName}` limit 1")
    void checkTableExists(@Param("tableName") String tableName);

    @Update("<script>" +
            "ALTER TABLE `${tableName}` ADD COLUMN `${columnName}` ${columnType} " +
            "<if test='isRequired'>NOT NULL</if> " +
            "<if test='defaultValue != null'>DEFAULT ${defaultValue}</if> " +
            "COMMENT '${comment}'" +
            "</script>")
    int addColumn(@Param("tableName") String tableName,
                  @Param("columnName") String columnName,
                  @Param("columnType") String columnType,
                  @Param("isRequired") boolean isRequired,
                  @Param("defaultValue") String defaultValue,
                  @Param("comment") String comment);

    @Update("ALTER TABLE `${tableName}` DROP COLUMN`${columnName}`")
    int dropColumn(@Param("tableName") String tableName,@Param("columnName") String columnName);

    /**
     * 检查列是否存在
     * 这里仅做查询，如果报异常，则认为列不存在
     * @param tableName
     * @param columnName
     */
    @Select("select `${columnName}` from `${tableName}` limit 1")
    void checkColumnExists(@Param("tableName") String tableName,@Param("columnName") String columnName);



}
