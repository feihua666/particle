package com.particle.global.crawler.runtime.expression;

import java.util.List;
import java.util.Map;

/**
 * 组合解析器
 * <p>
 * 根据模板前缀自动分发到对应实现
 * </p>
 *
 * <p>示例：</p>
 * <pre>
 * {{keyword}}       -> HutoolTemplateResolver
 * #{price > 0}      -> SpelExpressionResolver
 * 普通字符串         -> 原样返回
 * </pre>
 *
 * @author yangwei
 * @since 2026/05/12 16:00
 */
public class CompositeExpressionResolver implements ExpressionResolver {

    private final List<ExpressionResolver> resolvers;

    public CompositeExpressionResolver() {
        this.resolvers = List.of(
            new SpelExpressionResolver(),      // 先匹配 #{} 条件
            new HutoolTemplateResolver()       // 再匹配 {{}} 模板
        );
    }

    @Override
    public Object resolve(String template, Map<String, Object> variables) {
        if (template == null) return null;
        for (ExpressionResolver resolver : resolvers) {
            if (resolver.matches(template)) {
                return resolver.resolve(template, variables);
            }
        }
        return template; // 普通字符串原样返回
    }

    @Override
    public boolean matches(String template) {
        return resolvers.stream().anyMatch(r -> r.matches(template));
    }
}
