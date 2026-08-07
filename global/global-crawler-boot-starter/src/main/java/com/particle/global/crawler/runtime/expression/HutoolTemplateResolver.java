package com.particle.global.crawler.runtime.expression;

import java.util.Map;

/**
 * Hutool 模板解析器
 * <p>
 * 语法：{{varName}}
 * 用途：字符串模板替换，如 URL、输入文本、选择器
 * </p>
 *
 * <p>示例：</p>
 * <pre>
 * "https://baidu.com/s?wd={{keyword}}" -> "https://baidu.com/s?wd=Java"
 * "第{{index}}页" -> "第1页"
 * </pre>
 *
 * @author yangwei
 * @since 2026/05/12 16:00
 */
public class HutoolTemplateResolver implements ExpressionResolver {

    private static final String PREFIX = "{{";
    private static final String SUFFIX = "}}";

    @Override
    public Object resolve(String template, Map<String, Object> variables) {
        if (template == null) return null;
        return resolveTemplate(template, variables);
    }

    private String resolveTemplate(String template, Map<String, Object> variables) {
        if (!template.contains(PREFIX)) return template;

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < template.length()) {
            int start = template.indexOf(PREFIX, i);
            if (start == -1) {
                sb.append(template.substring(i));
                break;
            }
            int end = template.indexOf(SUFFIX, start);
            if (end == -1) {
                sb.append(template.substring(i));
                break;
            }
            // 追加占位符前的文本
            sb.append(template, i, start);
            // 取变量名
            String varName = template.substring(start + PREFIX.length(), end).trim();
            Object value = variables.get(varName);
            sb.append(value != null ? value : "");
            i = end + SUFFIX.length();
        }
        return sb.toString();
    }

    @Override
    public boolean matches(String template) {
        return template != null && template.contains(PREFIX) && template.contains(SUFFIX);
    }
}
