package com.particle.global.crawler.parser;

/**
 * 选择器类型枚举
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum SelectorType {
    
    CSS("CSS选择器"),
    XPATH("XPath选择器"),
    REGEX("正则表达式"),
    JSON_PATH("JSON Path");
    
    private final String description;
    
    SelectorType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
