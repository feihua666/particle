package com.particle.global.crawler.action;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

import com.particle.global.crawler.pipeline.action.ActionTypeIdResolver;
import com.particle.global.crawler.runtime.RuntimeContext;

/**
 * 爬虫动作接口
 * <p>
 * 所有爬虫 Action 都必须实现此接口。
 * Action 是爬虫流程的最小执行单元。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@JsonTypeInfo(
        // 使用名称标识类型
        use = JsonTypeInfo.Id.CUSTOM,
        // 使用现有属性
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        // 使用哪个字段作为类型标识
        property = "type",
        // 生成的类型字段（property = "type"）也参与正常的反序列化，并赋值给 Java 对象中同名的字段
        visible = true
)

@JsonTypeIdResolver(ActionTypeIdResolver.class)
public interface CrawlAction {

    /**
     * 获取 Action 类型
     *
     * @return Action 类型枚举
     */
    String getType();

    /**
     * 执行动作
     *
     * @param context 执行上下文
     * @return 执行结果
     */
    ActionResult execute(RuntimeContext context);
}
