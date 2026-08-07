package com.particle.global.crawler.pipeline;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.particle.global.crawler.action.CrawlAction;
import com.particle.global.crawler.runtime.RuntimeContext;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import org.openqa.selenium.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Pipeline 默认实现
 * <p>
 * 支持顺序执行多个 Action，维护变量状态，是最常用的 Pipeline 实现。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
public class DefaultPipeline implements CrawlPipeline {

    /**
     * Pipeline 名称
     */
    private String name;

    /**
     * Pipeline 描述
     */
    private String description;

    /**
     * Action 列表
     */
    private List<CrawlAction> actions = new ArrayList<>();

    /**
     * 初始变量
     */
    private Map<String, Object> variables = new HashMap<>();

    /**
     * 结果数据键
     */
    private List<String> resultDataKeys;

    public DefaultPipeline() {
    }

    public DefaultPipeline(String name) {
        this.name = name;
    }
    public DefaultPipeline(String name, List<String> resultDataKeys) {
        this.name = name;
        this.resultDataKeys = resultDataKeys;
    }
    @Override
    public CrawlPipeline name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CrawlPipeline description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CrawlPipeline addAction(CrawlAction action) {
        this.actions.add(action);
        return this;
    }

    @Override
    public CrawlPipeline variables(Map<String, Object> variables) {
        if (variables == null) {
            return this;
        }
        this.variables = variables;
        return this;
    }

    @Override
    public String toJson() {
        return JsonTool.toJsonStrForHttp( this, JsonTool.getObjectMapper());
    }

    @Override
    public List<String> resultDataKeys() {
        return resultDataKeys;
    }

}
