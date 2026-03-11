package com.particle.global.dag.runtime.condition;

import com.particle.global.dag.runtime.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 条件评估器管理器，用于统一管理不同类型的条件评估器
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class ConditionEvaluatorManager {

    // 评估器类型常量
    public static final String TYPE_GROOVY = "groovy";
    public static final String TYPE_SPEL = "spel";
    public static final String TYPE_DEFAULT = TYPE_SPEL;

    // 条件前缀常量
    public static final String PREFIX_GROOVY = TYPE_GROOVY + ":";
    public static final String PREFIX_SPEL = TYPE_SPEL + ":";

    private static final Map<String, ConditionEvaluator> evaluators = new HashMap<>();

    static {
        // 注册默认评估器 - 使用 SpEL 作为默认评估器
        registerEvaluator(TYPE_GROOVY, new GroovyConditionEvaluator());
        registerEvaluator(TYPE_SPEL, new SpelConditionEvaluator());
    }

    /**
     * 注册条件评估器
     * @param type 评估器类型
     * @param evaluator 评估器实例
     */
    public static void registerEvaluator(String type, ConditionEvaluator evaluator) {
        evaluators.put(type.toLowerCase(), evaluator);
    }

    /**
     * 获取条件评估器
     * @param type 评估器类型
     * @return 评估器实例
     */
    public static ConditionEvaluator getEvaluator(String type) {
        if (type == null) {
            return evaluators.get(TYPE_DEFAULT);
        }

        ConditionEvaluator evaluator = evaluators.get(type.toLowerCase());
        return evaluator != null ? evaluator : evaluators.get(TYPE_DEFAULT);
    }

    /**
     * 评估条件
     * @param condition 条件表达式
     * @param context 执行上下文
     * @param type 评估器类型，默认为 "default"
     * @return 评估结果
     */
    public static boolean evaluate(String condition, ExecutionContext context, String type) {
        ConditionEvaluator evaluator = getEvaluator(type);
        return evaluator != null ? evaluator.evaluate(condition, context) : false;
    }

    /**
     * 使用默认评估器评估条件
     * @param condition 条件表达式
     * @param context 执行上下文
     * @return 评估结果
     */
    public static boolean evaluate(String condition, ExecutionContext context) {
        if (condition == null) {
            return true;
        }

        // 根据条件前缀判断使用哪个评估器
        if (condition.startsWith(PREFIX_GROOVY)) {
            String groovyExpression = condition.substring(PREFIX_GROOVY.length()).trim();
            ConditionEvaluator evaluator = getEvaluator(TYPE_GROOVY);
            return evaluator != null ? evaluator.evaluate(groovyExpression, context) : false;
        } else if (condition.startsWith(PREFIX_SPEL)) {
            String spelExpression = condition.substring(PREFIX_SPEL.length()).trim();
            ConditionEvaluator evaluator = getEvaluator(TYPE_SPEL);
            return evaluator != null ? evaluator.evaluate(spelExpression, context) : false;
        } else {
            // 默认使用评估器
            ConditionEvaluator evaluator = getEvaluator(null);
            return evaluator != null ? evaluator.evaluate(condition, context) : false;
        }
    }
}
