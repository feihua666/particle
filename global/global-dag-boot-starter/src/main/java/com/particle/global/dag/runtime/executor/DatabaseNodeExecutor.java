package com.particle.global.dag.runtime.executor;

import com.particle.global.dag.constants.NodeTypeConstants;
import com.particle.global.dag.model.DagNode;
import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.dag.runtime.NodeExecutionResult;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * <p>
 * 数据库操作节点执行器
 * </p>
 * <p>
 * 用于执行数据库查询、插入、更新、删除等操作，使用JdbcTemplate执行SQL
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 15:45:00
 */
public class DatabaseNodeExecutor implements NodeExecutor {

    private JdbcTemplate jdbcTemplate;

    public DatabaseNodeExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean supports(DagNode node) {
        String nodeType = node.getType();
        return NodeTypeConstants.DATABASE.equalsIgnoreCase(nodeType);
    }

    @Override
    public NodeExecutionResult execute(DagNode node, ExecutionContext context) throws Exception {
        try {
            if (jdbcTemplate == null) {
                return NodeExecutionResult.failure(new IllegalStateException("JdbcTemplate is not configured for DatabaseNodeExecutor"));
            }

            Map<String, Object> config = node.getConfig();
            if (config == null) {
                return NodeExecutionResult.failure(new IllegalArgumentException("Database node config is null"));
            }

            String sql = (String) config.get("sql");
            if (sql == null || sql.trim().isEmpty()) {
                return NodeExecutionResult.failure(new IllegalArgumentException("SQL statement is required"));
            }

            // 根据SQL类型执行相应的操作
            Object result = executeSql(sql, config);

            return NodeExecutionResult.success(result);
        } catch (Exception e) {
            return NodeExecutionResult.failure(e);
        }
    }

    private Object executeSql(String sql, Map<String, Object> config) {
        String sqlType = detectSqlType(sql);

        switch (sqlType.toUpperCase()) {
            case "SELECT":
                // 对于SELECT语句，执行查询
                return jdbcTemplate.queryForList(sql);
            case "INSERT":
            case "UPDATE":
            case "DELETE":
                // 对于DML语句，执行更新并返回影响的行数
                int affectedRows = jdbcTemplate.update(sql);
                return Map.of("affectedRows", affectedRows, "success", true);
            default:
                // 对于其他类型的SQL（如DDL），尝试执行更新
                int result = jdbcTemplate.update(sql);
                return Map.of("result", result, "success", true);
        }
    }

    private String detectSqlType(String sql) {
        String trimmedSql = sql.trim().toUpperCase();
        if (trimmedSql.startsWith("SELECT")) {
            return "SELECT";
        } else if (trimmedSql.startsWith("INSERT")) {
            return "INSERT";
        } else if (trimmedSql.startsWith("UPDATE")) {
            return "UPDATE";
        } else if (trimmedSql.startsWith("DELETE")) {
            return "DELETE";
        } else {
            // 对于其他SQL类型（如CREATE, ALTER, DROP等）
            return trimmedSql.split("\\s+")[0];
        }
    }
}
