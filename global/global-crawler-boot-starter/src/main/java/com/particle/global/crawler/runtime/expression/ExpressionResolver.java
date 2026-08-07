package com.particle.global.crawler.runtime.expression;

import java.util.Map;

/**
 * 表达式解析器接口
 * <p>
 * 两种实现：
 * - SpEL：条件表达式，如 #{price > 100}
 * - Hutool：模板替换，如 {{keyword}}
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 16:00
 */
public interface ExpressionResolver {

    /**
     * 解析表达式
     *
     * @param template  模板字符串
     * @param variables 变量上下文
     * @return 解析结果
     */
    Object resolve(String template, Map<String, Object> variables);

    /**
     * 解析为字符串
     */
    default String resolveAsString(String template, Map<String, Object> variables) {
        Object result = resolve(template, variables);
        return result != null ? result.toString() : null;
    }

    /**
     * 解析为布尔值（条件判断用）
     */
    default boolean resolveAsBoolean(String template, Map<String, Object> variables) {
        Object result = resolve(template, variables);
        if (result instanceof Boolean) return (Boolean) result;
        if (result instanceof String) return Boolean.parseBoolean((String) result);
        return result != null;
    }

    /**
     * 是否匹配该解析器（根据模板前缀判断）
     */
    boolean matches(String template);
}
