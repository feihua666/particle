package com.particle.global.mybatis.plus.table;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 一个表操作服务
 * </p>
 *
 * @author yangwei
 * @since 2025/11/6 19:00
 */
public interface TableServivce {

    /**
     * 创建表
     * @param tableName
     */
    void createTable(String tableName,String comment);
    /**
     * 删除表
     * @param tableName
     */
    void dropTable(String tableName);

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    boolean isExistTable(String tableName);

    /**
     * 添加字段
     * @param tableName
     * @param columnName
     * @param columnType
     */
    void addColumn(String tableName, String columnName, String columnType,boolean isRequired, String defaultValue, String comment);
    /**
     * 删除字段
     * @param tableName
     * @param columnName
     */
    void dropColumn(String tableName, String columnName);

    /**
     * 判断字段是否存在
     * @param tableName
     * @param columnName
     * @return
     */
    boolean isExistColumn(String tableName, String columnName);

    /**
     * 批量插入数据
     * 注意：批量插入数据时，列名不能重复，考虑到数据中的key可能会多，需要指定列名以确定插入的列
     * @param tableName
     * @param dataList key为列名，value为字段值
     * @param columnNames 列名
     * @param isPublic 数据是否发布
     * @param batchId 批次id，用于标识一个批次
     * @return
     */
    int batchInsertData(String tableName, List<Map<String, Object>> dataList,List<String> columnNames,Boolean isPublic,Long batchId);

    /**
     * 查询数据
     * @param tableName
     * @param columnNames
     * @param page
     * @return
     */
    Page<Map<String, Object>> selectPage(String tableName, List<String> columnNames,Boolean isPublic,Long batchId, Page page) ;

    /**
     * 根据id查询数据
     * @param tableName
     * @param columnNames
     * @param id
     * @return
     */
    Map<String, Object> selectById(String tableName, List<String> columnNames, Long id);

    /**
     * 删除数据
     * @param tableName
     * @param id
     * @return
     */
    int deleteById(String tableName, Long id);
    /**
     * 统计数据数量
     * @param tableName
     * @return
     */
    Long count(String tableName,Boolean isPublic);

    /**
     * 发布数据，按批次发布
     * @param tableName
     * @param batchId
     * @return
     */
    int publish(String tableName,Long batchId);
    /**
     * 取消发布数据，按批次取消发布
     * @param tableName
     * @param batchId
     * @return
     */
    int unPublish(String tableName,Long batchId);
}
