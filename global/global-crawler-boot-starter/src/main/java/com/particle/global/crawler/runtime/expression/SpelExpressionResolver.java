package com.particle.global.crawler.runtime.expression;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SpEL 表达式解析器
 * <p>
 * 语法：#{expression}
 * 用途：条件判断、数值计算、复杂逻辑
 * </p>
 *
 * <p>示例：</p>
 * <pre>
 * "#{price > 100}"               true/false
 * "#{title != null}"             true/false
 * "#{count > 0 && hasNext}"      true/false
 * "#{price * 0.9}"               计算结果
 * "#{items.size() > 0}"          true/false
 * </pre>
 *
 * @author yangwei
 * @since 2026/05/12 16:00
 */
public class SpelExpressionResolver implements ExpressionResolver {

    private static final String PREFIX = "#{";
    private static final String SUFFIX = "}";

    private final ExpressionParser parser = new SpelExpressionParser();

    // 缓存编译好的表达式，避免重复解析
    private final Map<String, Expression> expressionCache = new ConcurrentHashMap<>();

    @Override
    public Object resolve(String template, Map<String, Object> variables) {
        if (template == null) return null;

        String expression = extractExpression(template);
        if (expression == null) return template;

        try {
            Expression exp = expressionCache.computeIfAbsent(
                expression, parser::parseExpression
            );

            // 把变量注入到 EvaluationContext
            StandardEvaluationContext context = new StandardEvaluationContext();
            variables.forEach(context::setVariable);

            // SpEL 里用 #varName 访问变量
            return exp.getValue(context);

        } catch (Exception e) {
            throw new RuntimeException("SpEL 表达式解析失败: " + expression, e);
        }
    }

    /**
     * 提取 #{...} 内的表达式
     * 支持变量引用转换：#{price > 100} -> #price > 100（SpEL 变量用 # 前缀）
     */
    private String extractExpression(String template) {
        if (!template.startsWith(PREFIX) || !template.endsWith(SUFFIX)) {
            return null;
        }
        String inner = template.substring(PREFIX.length(), template.length() - SUFFIX.length()).trim();

        // 把变量名转成 SpEL 的 #varName 格式
        return convertToSpelVars(inner);
    }

    /**
     * 把表达式里的裸变量名转成 SpEL #varName 格式
     * 跳过已经有 # 前缀的、字符串字面量、数字
     */
    private String convertToSpelVars(String expression) {
        // 用正则匹配裸标识符（不以 # 开头，不在引号内）
        Pattern pattern = Pattern.compile("(?<![#'\"\\w])(\\b[a-zA-Z_][a-zA-Z0-9_.]*\\b)(?!['\"])");
        Matcher matcher = pattern.matcher(expression);
        StringBuffer sb = new StringBuffer();
        
        while (matcher.find()) {
            String match = matcher.group(1);
            // 跳过 SpEL 关键字
            if (isKeyword(match)) {
                matcher.appendReplacement(sb, match);
            } else {
                matcher.appendReplacement(sb, "#" + match);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private boolean isKeyword(String word) {
        return Set.of("null", "true", "false", "and", "or", "not",
                       "instanceof", "matches", "new").contains(word);
    }

    @Override
    public boolean matches(String template) {
        return template != null
                && template.startsWith(PREFIX)
                && template.endsWith(SUFFIX);
    }
}
