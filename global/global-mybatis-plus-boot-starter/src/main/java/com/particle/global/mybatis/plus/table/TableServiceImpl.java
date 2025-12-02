package com.particle.global.mybatis.plus.table;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.mapper.DynamicDOMapper;
import com.particle.global.mybatis.plus.mapper.MysqlTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * mysql表操作服务
 * </p>
 *
 * @author yangwei
 * @since 2025/11/6 19:10
 */
public class TableServiceImpl implements TableServivce{

    public enum DatabaseDialect {
        mysql, postgresql, oracle, sql_server
    }

    // 通用基础类型（所有数据库都支持）
    private static final Set<String> COMMON_BASE_TYPES = Set.of(
            // 整数类型
            "INT", "INTEGER", "BIGINT", "SMALLINT", "TINYINT", "MEDIUMINT",
            // 浮点类型
            "FLOAT", "DOUBLE", "REAL",
            // 字符串类型
            "CHAR", "VARCHAR", "TEXT", "LONGTEXT", "MEDIUMTEXT", "TINYTEXT",
            // 日期时间类型
            "DATE", "TIME", "DATETIME", "TIMESTAMP", "YEAR",
            // 布尔类型
            "BOOLEAN", "BOOL",
            // 二进制类型
            "BLOB", "LONGBLOB", "MEDIUMBLOB", "TINYBLOB"
    );

    // 带参数的类型正则表达式
    private static final Pattern[] PARAMETERIZED_TYPES = {
            Pattern.compile("^VARCHAR\\(\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^CHAR\\(\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^DECIMAL\\(\\d+,\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^NUMERIC\\(\\d+,\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^FLOAT\\(\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^DOUBLE\\(\\d+,\\d+\\)$", Pattern.CASE_INSENSITIVE),
            Pattern.compile("^BIT\\(\\d+\\)$", Pattern.CASE_INSENSITIVE)
    };

    // 数据库特定类型
    private static final Set<String> MYSQL_SPECIFIC_TYPES = Set.of(
            "ENUM", "SET", "JSON", "GEOMETRY", "POINT", "LINESTRING", "POLYGON"
    );

    private static final Set<String> POSTGRESQL_SPECIFIC_TYPES = Set.of(
            "SERIAL", "BIGSERIAL", "SMALLSERIAL", "UUID", "JSONB", "XML",
            "ARRAY", "CIDR", "INET", "MACADDR", "TSVECTOR", "TSQUERY",
            "MONEY", "BYTEA"
    );

    private static final Set<String> ORACLE_SPECIFIC_TYPES = Set.of(
            "NUMBER", "VARCHAR2", "NVARCHAR2", "CLOB", "NCLOB", "BINARY_FLOAT",
            "BINARY_DOUBLE", "RAW", "LONG RAW", "ROWID", "UROWID"
    );

    private static final Set<String> SQLSERVER_SPECIFIC_TYPES = Set.of(
            "NTEXT", "IMAGE", "UNIQUEIDENTIFIER", "SQL_VARIANT", "HIERARCHYID",
            "GEOMETRY", "GEOGRAPHY", "XML", "DATETIME2", "DATETIMEOFFSET",
            "SMALLDATETIME"
    );

    private static final Set<String> SQLITE_SPECIFIC_TYPES = Set.of(
            "BLOB", "NUMERIC"
    );
    /**
     * 数据库方言
     * 配置项参考：{@link DatabaseDialect}
     */
    @Value("${particle.mybatis-plus.table-service.dialect:mysql}")
    private String dialect;

    private MysqlTableMapper mysqlTableMapper;
    private DynamicDOMapper dynamicDOMapper;

    @Override
    public void createTable(String tableName,String comment) {
        validateTableName(tableName);
        String safeComment = escapeComment(comment);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            mysqlTableMapper.createTable(tableName,safeComment);
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }

    }

    @Override
    public void dropTable(String tableName) {
        validateTableName(tableName);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            mysqlTableMapper.dropTable(tableName);
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public boolean isExistTable(String tableName) {
        validateTableName(tableName);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            try {
                mysqlTableMapper.checkTableExists(tableName);
                return true;
            } catch (Exception e) {
                return false;
            }
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public void addColumn(String tableName, String columnName, String columnType,boolean isRequired, String defaultValue, String comment) {
        validateTableName(tableName);
        validateColumnName(columnName);
        validateColumnType(columnType);
        String safeComment = escapeComment(comment);
        String safeDefaultValue = defaultValue;
        boolean isColumnTypeStringType = isStringType(defaultValue);
        if (isColumnTypeStringType) {
            safeDefaultValue = "'" + defaultValue + "'";
        }
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            mysqlTableMapper.addColumn(tableName,columnName,columnType,isRequired,safeDefaultValue,safeComment);
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public void dropColumn(String tableName, String columnName) {
        validateTableName(tableName);
        validateColumnName(columnName);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            mysqlTableMapper.dropColumn(tableName,columnName);
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public boolean isExistColumn(String tableName, String columnName) {
        validateTableName(tableName);
        validateColumnName(columnName);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            try {
                mysqlTableMapper.checkColumnExists(tableName,columnName);
                return true;
            } catch (Exception e) {
                return false;
            }
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public int batchInsertData(String tableName, List<Map<String, Object>> dataList,List<String> columnNames,Boolean isPublic,Long batchId) {
        if (CollectionUtil.isEmpty(dataList)) {
            return 0;
        }
        validateTableName(tableName);
        if (DatabaseDialect.mysql.name().equals(dialect)) {
            List<DynamicDO> dynamicDOList = dataList.stream()
                    .map(item -> DynamicDO.create(tableName, columnNames, item,isPublic,batchId))
                    .collect(Collectors.toList());
            return dynamicDOMapper.insert(dynamicDOList).size();
        }else{
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "暂不支持该数据库" + dialect);
        }
    }

    @Override
    public Page<Map<String, Object>> selectPage(String tableName, List<String> columnNames,Boolean isPublic,Long batchId, Page page) {
        List<String> newColumnNames = wrapColumnNamesForSelect(columnNames);
        QueryWrapper<DynamicDO> queryWrapper = Wrappers.<DynamicDO>query()
                .select(newColumnNames)
                .eq(isPublic != null, DynamicDO.COLUMN_IS_PUBLIC, isPublic)
                .eq(batchId != null, DynamicDO.COLUMN_BATCH_ID, batchId)
                ;
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            Page<Map<String, Object>> selectPage = dynamicDOMapper.selectMapsPage(page, queryWrapper);
            return selectPage;
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    @Override
    public Map<String, Object> selectById(String tableName, List<String> columnNames, Long id) {
        List<String> newColumnNames = wrapColumnNamesForSelect(columnNames);
        QueryWrapper<DynamicDO> queryWrapper = Wrappers.<DynamicDO>query().select(newColumnNames).eq(DynamicDO.COLUMN_ID, id);
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            List<Map<String, Object>> selectMaps = dynamicDOMapper.selectMaps(queryWrapper);
            if (selectMaps.size() == 1) {
                return selectMaps.get(0);
            }else if (selectMaps.size() > 1) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR, "查询结果错误,结果多于一条，请检查数据");
            }
            return Collections.emptyMap();
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    @Override
    public int deleteById(String tableName, Long id) {
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            return dynamicDOMapper.deleteById(id);
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    @Override
    public Long count(String tableName,Boolean isPublic) {
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            return dynamicDOMapper.selectCount(Wrappers.<DynamicDO>query().eq(isPublic != null,DynamicDO.COLUMN_IS_PUBLIC, isPublic));
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    @Override
    public int publish(String tableName, Long batchId) {
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            return dynamicDOMapper.update(Wrappers.<DynamicDO>lambdaUpdate().set(DynamicDO::getIsPublic, true).eq(DynamicDO::getBatchId, batchId));
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    @Override
    public int unPublish(String tableName, Long batchId) {
        try {
            CustomDynamicTableNameHandler.setDynamicTableName(DynamicDO.table_name, tableName);
            return dynamicDOMapper.update(Wrappers.<DynamicDO>lambdaUpdate().set(DynamicDO::getIsPublic, false).eq(DynamicDO::getBatchId, batchId));
        }finally {
            CustomDynamicTableNameHandler.clearDynamicTableName();
        }
    }

    /**
     * 封装查询列名
     * @param columnNames
     * @return
     */
    private List<String> wrapColumnNamesForSelect(List<String> columnNames) {
        List<String> newColumnNames = new ArrayList(columnNames);
        newColumnNames.add(0,DynamicDO.COLUMN_ID);
        newColumnNames.add(DynamicDO.COLUMN_IS_PUBLIC);
        newColumnNames.add(DynamicDO.COLUMN_VERSION);
        newColumnNames.add(DynamicDO.COLUMN_CREATE_AT);
        newColumnNames.add(DynamicDO.COLUMN_CREATE_BY);
        newColumnNames.add(DynamicDO.COLUMN_UPDATE_AT);
        newColumnNames.add(DynamicDO.COLUMN_UPDATE_BY);
        return newColumnNames;
    }
    /**
     * 验证表名
     * @param tableName
     */
    private void validateTableName(String tableName) {
        if (tableName == null || !tableName.matches("^[a-zA-Z_][a-zA-Z0-9_]{0,63}$")) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR,"非法表名: " + tableName);
        }

        // 防止SQL关键字
        String[] forbidden = {"SELECT", "INSERT", "DELETE", "UPDATE", "DROP", "CREATE",
                "ALTER", "EXEC", "UNION", "FROM", "WHERE"};
        String upperName = tableName.toUpperCase();
        for (String keyword : forbidden) {
            if (upperName.equals(keyword)) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR,"表名不能是SQL关键字: " + tableName);
            }
        }
    }

    /**
     * 验证字段名
     * @param columnName
     */
    private void validateColumnName(String columnName) {
        if (columnName == null || !columnName.matches("^[a-zA-Z_][a-zA-Z0-9_]{0,63}$")) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR,"非法字段名: " + columnName);
        }
    }

    private void validateColumnType(String columnType) {
        if (!doValidateColumnType(columnType)) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SYSTEM_ERROR,"不支持的字段类型: " + columnType);
        }
    }
    /**
     * 验证字段类型（支持多种数据库）
     * @param columnType
     * @return
     */
    private boolean doValidateColumnType(String columnType) {
        if (columnType == null || columnType.trim().isEmpty()) {
            return false;
        }

        String upperType = columnType.toUpperCase().trim();

        // 1. 检查基础通用类型
        if (COMMON_BASE_TYPES.contains(upperType)) {
            return true;
        }

        // 2. 检查带参数的类型
        for (Pattern pattern : PARAMETERIZED_TYPES) {
            if (pattern.matcher(upperType).matches()) {
                return true;
            }
        }

        // 3. 检查数据库特定类型
        // MYSQL:
        if (MYSQL_SPECIFIC_TYPES.contains(upperType)) return true;
        if (upperType.startsWith("ENUM(")) return true;
        if (upperType.startsWith("SET(")) return true;

        // POSTGRESQL:
        if (POSTGRESQL_SPECIFIC_TYPES.contains(upperType)) return true;
        if (upperType.endsWith("[]")) return true; // 数组类型

        // ORACLE:
        if (ORACLE_SPECIFIC_TYPES.contains(upperType)) return true;
        if (upperType.startsWith("NUMBER")) return true;
        if (upperType.startsWith("TIMESTAMP")) return true;

        // SQLSERVER:
        if (SQLSERVER_SPECIFIC_TYPES.contains(upperType)) return true;
        if (upperType.startsWith("NVARCHAR")) return true;
        if (upperType.startsWith("DATETIME2")) return true;

        // SQLITE:
        if (SQLITE_SPECIFIC_TYPES.contains(upperType)) return true;

        return false;
    }
    /**
     * 转义注释内容
     * @param comment
     * @return
     */
    private String escapeComment(String comment) {
        if (comment == null) return "";

        // 长度限制
        if (comment.length() > 500) {
            comment = comment.substring(0, 500);
        }

        // 转义单引号（防止SQL注入）
        return comment.replace("'", "''");
    }
    /**
     * 判断字段类型是否为字符串类型
     * @param columnType
     * @return
     */
    private boolean isStringType(String columnType) {
        if (columnType == null) return false;

        String type = columnType.toUpperCase().split("\\s+")[0]; // 取第一个单词
        type = type.split("\\(")[0]; // 去除长度限制

        // 明确的字符串类型列表
        Set<String> stringTypes = Set.of(
                // 通用类型
                "CHAR", "VARCHAR", "TEXT", "CLOB",
                // MySQL
                "TINYTEXT", "MEDIUMTEXT", "LONGTEXT", "ENUM", "SET",
                // PostgreSQL
                "BPCHAR", "CITEXT", "VARCHAR", "UUID", "INET", "MACADDR",
                // SQL Server
                "NTEXT", "NCHAR", "NVARCHAR", "XML",
                // Oracle
                "NCLOB", "NVARCHAR2",
                // 日期时间类型
                "DATE", "TIME", "DATETIME", "TIMESTAMP", "YEAR",
                // JSON
                "JSON", "JSONB"
        );

        return stringTypes.contains(type);
    }
    @Autowired
    public void setMysqlTableMapper(MysqlTableMapper mysqlTableMapper) {
        this.mysqlTableMapper = mysqlTableMapper;
    }
    @Autowired
    public void setDynamicDOMapper(DynamicDOMapper dynamicDOMapper) {
        this.dynamicDOMapper = dynamicDOMapper;
    }
}
