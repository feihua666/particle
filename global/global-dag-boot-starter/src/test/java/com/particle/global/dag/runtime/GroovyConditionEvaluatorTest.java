package com.particle.global.dag.runtime;

import com.particle.global.dag.runtime.condition.GroovyConditionEvaluator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 * GroovyConditionEvaluator 测试
 * </p>
 *
 * @author Claude
 * @since 2026-01-10 22:27:00
 */
public class GroovyConditionEvaluatorTest {

    private GroovyConditionEvaluator evaluator;
    private ExecutionContext context;

    @BeforeEach
    void setUp() {
        evaluator = new GroovyConditionEvaluator();
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
    void testEvaluateNumericLessThan() {
        context.setVariable("count", 3);
        boolean result = evaluator.evaluate("count < 5", context);
        assertTrue(result, "数字比较 count < 5 应该为 true");
    }

    @Test
    void testEvaluateNumericEquality() {
        context.setVariable("value", 42);
        boolean result = evaluator.evaluate("value == 42", context);
        assertTrue(result, "数字相等比较应该为 true");
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
    void testEvaluateStringContains() {
        context.setVariable("text", "hello world");
        boolean result = evaluator.evaluate("text.contains('world')", context);
        assertTrue(result, "字符串包含比较应该为 true");
    }

    @Test
    void testEvaluateDirectVariableAccess() {
        context.setVariable("userCount", 100);
        boolean result = evaluator.evaluate("userCount > 50", context);
        assertTrue(result, "直接访问变量应该成功");
    }

    @Test
    void testEvaluateMultipleVariableComparison() {
        context.setVariable("min", 10);
        context.setVariable("max", 100);
        context.setVariable("current", 50);
        boolean result = evaluator.evaluate("current > min && current < max", context);
        assertTrue(result, "多变量比较应该成功");
    }

    @Test
    void testEvaluateLogicalOperatorsAnd() {
        context.setVariable("a", true);
        context.setVariable("b", true);
        boolean result = evaluator.evaluate("a && b", context);
        assertTrue(result, "逻辑 AND 操作符应该正确评估");
    }

    @Test
    void testEvaluateLogicalOperatorsOr() {
        context.setVariable("a", false);
        context.setVariable("b", true);
        boolean result = evaluator.evaluate("a || b", context);
        assertTrue(result, "逻辑 OR 操作符应该正确评估");
    }

    @Test
    void testEvaluateLogicalOperatorsNot() {
        context.setVariable("a", false);
        boolean result = evaluator.evaluate("!a", context);
        assertTrue(result, "逻辑 NOT 操作符应该正确评估");
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
        boolean result = evaluator.evaluate("data['status'] == 'active' && data['count'] > 3", context);
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
        boolean result = evaluator.evaluate("thisVariableDoesNotExist == null", context);
        assertFalse(result, "不存在的变量在 Groovy 中会抛出异常，evaluate 方法会捕获并返回 false");
    }

    @Test
    void testEvaluateListSizeComparison() {
        context.setVariable("items", java.util.List.of("a", "b", "c"));
        boolean result = evaluator.evaluate("items.size() == 3", context);
        assertTrue(result, "列表大小比较应该正确评估");
    }

    @Test
    void testEvaluateStringLength() {
        context.setVariable("text", "hello");
        boolean result = evaluator.evaluate("text.length() == 5", context);
        assertTrue(result, "字符串长度比较应该正确评估");
    }

    @Test
    void testEvaluateArithmeticOperations() {
        context.setVariable("x", 10);
        context.setVariable("y", 5);
        boolean result = evaluator.evaluate("x + y == 15", context);
        assertTrue(result, "算术运算应该正确评估");
    }

    @Test
    void testEvaluateTernaryOperator() {
        context.setVariable("flag", true);
        boolean result = evaluator.evaluate("flag ? true : false", context);
        assertTrue(result, "三元运算符应该正确评估");
    }

    @Test
    void testEvaluateRegexMatch() {
        context.setVariable("email", "test@example.com");
        boolean result = evaluator.evaluate("email =~ /.*@.*/", context);
        assertTrue(result, "正则表达式匹配应该正确评估");
    }

    @Test
    void testEvaluateMethodCallOnString() {
        context.setVariable("text", "Hello World");
        boolean result = evaluator.evaluate("text.toLowerCase().contains('hello')", context);
        assertTrue(result, "字符串方法调用应该正确评估");
    }

    @Test
    void testEvaluateZeroAsFalse() {
        context.setVariable("value", 0);
        boolean result = evaluator.evaluate("value", context);
        assertFalse(result, "数值 0 应该被转换为 false");
    }

    @Test
    void testEvaluateNonZeroAsTrue() {
        context.setVariable("value", 42);
        boolean result = evaluator.evaluate("value", context);
        assertTrue(result, "非零数值应该被转换为 true");
    }

    @Test
    void testEvaluateEmptyStringAsFalse() {
        context.setVariable("value", "");
        boolean result = evaluator.evaluate("value", context);
        assertFalse(result, "空字符串应该被转换为 false");
    }

    @Test
    void testEvaluateNonEmptyStringAsTrue() {
        context.setVariable("value", "non-empty");
        boolean result = evaluator.evaluate("value", context);
        assertTrue(result, "非空字符串应该被转换为 true");
    }

    @Test
    void testEvaluateNullValueAsFalse() {
        context.setVariable("value", null);
        boolean result = evaluator.evaluate("value", context);
        assertFalse(result, "null 值应该被转换为 false");
    }
}
