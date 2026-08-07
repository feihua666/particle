package com.particle.global.crawler.pipeline;

import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.runtime.RuntimeContext;

import java.util.List;
import java.util.Map;

/**
 * Pipeline 接口 - 定义爬虫流水线的契约
 * <p>
 * Pipeline 是多个 Action 的有序组合，代表一个完整的爬取流程。
 * 它可以是简单的线性流程，也可以是包含条件、循环的复杂流程。
 * </p>
 *
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public interface CrawlPipeline {

    /**
     * 获取 Pipeline 名称
     */
    String getName();

    /**
     * 设置 Pipeline 名称
     */
    CrawlPipeline name(String name);

    /**
     * 获取 Pipeline 描述
     */
    String getDescription();

    /**
     * 设置 Pipeline 描述
     */
    CrawlPipeline description(String description);

    /**
     * 获取 Pipeline 中的所有 Action
     */
    List<CrawlAction> getActions();

    /**
     * 添加 Action
     */
    CrawlPipeline addAction(CrawlAction action);

    /**
     * 获取初始变量
     */
    Map<String, Object> getVariables();

    /**
     * 设置初始变量
     */
    CrawlPipeline variables(Map<String, Object> variables);

    /**
     * 获取结果数据键
     */
    List<String> resultDataKeys();

    /**
     * 将 Pipeline 转换为 JSON 格式
     */
    String toJson();

}
