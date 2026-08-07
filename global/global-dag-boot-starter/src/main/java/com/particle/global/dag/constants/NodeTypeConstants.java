package com.particle.global.dag.constants;

/**
 * <p>
 * DAG节点类型常量
 * </p>
 *
 * @author Claude
 * @since 2026-01-12 15:30:00
 */
public class NodeTypeConstants {

    /**
     * 文本数据节点类型 — 携带文本，可输入
     */
    public static final String CONSTANT_INPUT_TEXT = "CONSTANT_INPUT_TEXT";

    /**
     * 图片数据节点类型 — 携带图片URL，可输入
     */
    public static final String CONSTANT_INPUT_IMAGE = "CONSTANT_INPUT_IMAGE";

    /**
     * 视频数据节点类型 — 携带视频URL，可输入
     */
    public static final String CONSTANT_INPUT_VIDEO = "CONSTANT_INPUT_VIDEO";

    /**
     * 延迟节点类型
     */
    public static final String DELAY = "DELAY";

    /**
     * Groovy 脚本节点类型
     */
    public static final String GROOVY_SCRIPT = "GROOVY_SCRIPT";

    /**
     * HTTP 配置节点类型
     */
    public static final String CONSTANT_INPUT_HTTP_CONFIG = "CONSTANT_INPUT_HTTP_CONFIG";
    /**
     * HTTP 请求节点类型
     */
    public static final String HTTP = "HTTP";




    /**
     * 数据转换节点类型
     */
    public static final String TRANSFORM = "TRANSFORM";

    /**
     * 数据库节点类型
     */
    public static final String DATABASE = "DATABASE";

    /**
     * AI 模型调用节点类型
     */
    public static final String AI = "AI";
}
