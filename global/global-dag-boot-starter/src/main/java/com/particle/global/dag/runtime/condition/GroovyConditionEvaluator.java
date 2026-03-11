package com.particle.global.dag.runtime.condition;

import com.particle.global.dag.runtime.ExecutionContext;
import com.particle.global.tool.script.GroovyTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Bindings;

/**
 * <p>
 * Groovy 条件评估器实现
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class GroovyConditionEvaluator implements ConditionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(GroovyConditionEvaluator.class);

    @Override
    public boolean evaluate(String condition, ExecutionContext context) {
        if (condition == null || condition.trim().isEmpty()) {
            return true; // 空条件视为true
        }

        try {
            // 将ExecutionContext的变量转换为Bindings
            Bindings bindings = GroovyTool.createBindings(context.getVariables());

            // 使用GroovyTool的compileAndEval方法来评估Groovy表达式
            // 第三个参数true表示使用缓存以提高性能
            Object result = GroovyTool.compileAndEval(condition, bindings, true);

            // 如果结果是布尔值，直接返回；否则转换为布尔值
            if (result instanceof Boolean) {
                return (Boolean) result;
            } else {
                // 对于非布尔结果，按照Groovy的真值规则进行转换
                // null、空字符串、数字0等被视为false，其他为true
                if (result == null) {
                    return false;
                } else if (result instanceof String) {
                    return !((String) result).isEmpty();
                } else if (result instanceof Number) {
                    return ((Number) result).doubleValue() != 0.0;
                } else {
                    // 其他类型的对象视为true
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("Failed to evaluate Groovy condition {}", condition, e);
            return false; // 评估失败时默认为false
        }
    }
}
