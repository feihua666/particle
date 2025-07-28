package com.particle.cms.client.constants;

/**
 * <p>
 * cms 常量
 * </p>
 *
 * @author yangwei
 * @since 2025-07-07 11:17:09
 */
public class CmsConstants {
    public static final String dot = ".";
    public static final String templatePathDefault = "/default";
    public static final String staticPath = "/static";
    /**
     * classpath:/templates/cms
     * freemarker 集成 springboot 默认在 resources/templates 下,我们这里配置了 resources/templates/cms/ 作为cms根模板路径
     */
    public static final String templateRootPath = "cms";
    public static final String template404Path = "/404";
    public static final String templateChannelPath = "/channel";
    public static final String templateContentPath = "/content";

    public static final String templateSuffix = ".ftlh";
    public static final String templateIndexHtml = "index" + templateSuffix;
    public static final String templateIndex404Html = "404" + templateSuffix;




    public static final String model_site = "site";
    public static final String model_channel = "channel";
    public static final String model_content = "content";
}
