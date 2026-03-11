package com.particle.global.dag.runtime.condition;

import com.particle.global.dag.runtime.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * <p>
 * Spring Expression Language (SpEL) 条件评估器实现
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
public class SpelConditionEvaluator implements ConditionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(SpelConditionEvaluator.class);
    private final ExpressionParser parser = new SpelExpressionParser();

    @Override
    public boolean evaluate(String condition, ExecutionContext context) {
        if (condition == null || condition.trim().isEmpty()) {
            return true; // 空条件视为true
        }

        try {
            StandardEvaluationContext evalContext = new StandardEvaluationContext();
            evalContext.addPropertyAccessor(new MapPropertyAccessor());

            // 将 context 添加到变量 Map 中(如果还没有的话)
            Map<String, Object> variables = context.getVariables();
            if (variables != null && !variables.containsKey("context")) {
                variables.put("context", context);  // 添加 context 到变量中
            }

            // 设置根对象
            if (variables != null) {
                evalContext.setRootObject(variables);
            }

            // 同时注册为 SpEL 变量(双保险)
            evalContext.setVariable("context", context);

            Object result = parser.parseExpression(condition).getValue(evalContext);

            if (result instanceof Boolean) {
                return (Boolean) result;
            } else if (result == null) {
                return false;
            } else {
                return Boolean.parseBoolean(result.toString());
            }
        } catch (Exception e) {
            logger.error("Failed to evaluate SpEL condition '{}': {}", condition, e.getMessage());
            return false;
        }
    }

    /**
     * 自定义 Map 属性访问器,允许像访问对象属性一样访问 Map 的 key
     */
    private static class MapPropertyAccessor implements PropertyAccessor {

        @Override
        public Class<?>[] getSpecificTargetClasses() {
            return new Class<?>[]{Map.class};
        }

        @Override
        public boolean canRead(EvaluationContext context, Object target, String name) throws AccessException {
            return target instanceof Map;
        }

        @Override
        public TypedValue read(EvaluationContext context, Object target, String name) throws AccessException {
            Map<?, ?> map = (Map<?, ?>) target;
            Object value = map.get(name);
            return new TypedValue(value);
        }

        @Override
        public boolean canWrite(EvaluationContext context, Object target, String name) throws AccessException {
            return false; // 不支持写入
        }

        @Override
        public void write(EvaluationContext context, Object target, String name, Object newValue) throws AccessException {
            throw new AccessException("Write operations are not supported");
        }
    }
}
