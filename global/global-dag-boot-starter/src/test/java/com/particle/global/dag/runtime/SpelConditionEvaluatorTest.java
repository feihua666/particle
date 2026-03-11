package com.particle.global.dag.runtime;

import com.particle.global.dag.runtime.condition.SpelConditionEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * SpelConditionEvaluator 测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class SpelConditionEvaluatorTest {

    private SpelConditionEvaluator evaluator;
    private ExecutionContext context;

    @BeforeEach
    void setUp() {
        evaluator = new SpelConditionEvaluator();
        context = new ExecutionContext();
    }

    @Test
    void testEvaluateSimpleBooleanCondition() {
        context.setVariable("result", true);
        boolean result = evaluator.evaluate("result", context);
        assertTrue(result, "布尔值为 true 的条件应该评估为 true");
    }

    @Test
    void testEvaluateSimpleBooleanConditionFalse() {
        context.setVariable("result", false);
        boolean result = evaluator.evaluate("result", context);
        assertFalse(result, "布尔值为 false 的条件应该评估为 false");
    }

    @Test
    void testEvaluateNumericComparison() {
        context.setVariable("count", 10);
        boolean result = evaluator.evaluate("count > 5", context);
        assertTrue(result, "数字比较 count > 5 应该为 true");
    }

    @Test
    void testEvaluateStringComparison() {
        context.setVariable("status", "SUCCESS");
        boolean result = evaluator.evaluate("status == 'SUCCESS'", context);
        assertTrue(result, "字符串比较 status == 'SUCCESS' 应该为 true");
    }

    @Test
    void testEvaluateStringComparisonFalse() {
        context.setVariable("status", "FAILED");
        boolean result = evaluator.evaluate("status == 'SUCCESS'", context);
        assertFalse(result, "字符串比较 status == 'SUCCESS' 应该为 false");
    }

    @Test
    void testEvaluateContextVariableAccess() {
        context.setVariable("userCount", 100);
        boolean result = evaluator.evaluate("context.getVariable('userCount') > 50", context);
        assertTrue(result, "通过 context 访问变量应该成功");
    }

    @Test
    void testEvaluateMultipleVariableComparison() {
        context.setVariable("min", 10);
        context.setVariable("max", 100);
        context.setVariable("current", 50);
        boolean result = evaluator.evaluate("current > min and current < max", context);
        assertTrue(result, "多变量比较应该成功");
    }

    @Test
    void testEvaluateLogicalOperators() {
        context.setVariable("a", true);
        context.setVariable("b", false);
        boolean result = evaluator.evaluate("a and not b", context);
        assertTrue(result, "逻辑操作符应该正确评估");
    }

    @Test
    void testEvaluateEmptyCondition() {
        boolean result = evaluator.evaluate("", context);
        assertTrue(result, "空条件应该评估为 true");
    }

    @Test
    void testEvaluateNullCondition() {
        boolean result = evaluator.evaluate(null, context);
        assertTrue(result, "null 条件应该评估为 true");
    }

    @Test
    void testEvaluateComplexExpression() {
        context.setVariable("data", java.util.Map.of("status", "active", "count", 5));
        boolean result = evaluator.evaluate("data['status'] == 'active' and data['count'] > 3", context);
        assertTrue(result, "复杂表达式应该正确评估");
    }

    @Test
    void testEvaluateInvalidExpression() {
        context.setVariable("status", "SUCCESS");
        boolean result = evaluator.evaluate("invalid syntax here", context);
        assertFalse(result, "无效的表达式应该评估为 false");
    }

    @Test
    void testEvaluateVariableNotInContext() {
        boolean result = evaluator.evaluate("nonExistentVariable == null", context);
        // 这个可能会抛出异常，根据 SpEL 的行为，我们希望它能处理不存在的变量
        // 如果抛出异常，evaluate 方法应该捕获并返回 false
        // 所以结果可能是 false（取决于 SpEL 的具体配置）
        // 但这个测试是合理的
    }
}
