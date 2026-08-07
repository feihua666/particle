package com.particle.global.crawler.runtime;

import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.runtime.expression.CompositeExpressionResolver;
import com.particle.global.crawler.runtime.expression.ExpressionResolver;
import com.particle.global.crawler.runtime.session.auth.AuthConfig;
import com.particle.global.crawler.storage.data.DataStorage;
import com.particle.global.crawler.storage.raw.RawStorage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 运行时上下文
 * <p>
 * 贯穿整个 Pipeline 执行周期的全局上下文。
 * 持有配置、Driver、Storage、变量等核心资源。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Getter
public class RuntimeContext {

    /**
     * 运行时配置
     */
    private final CrawlRuntimeOptions options;

    /**
     * Driver 类型
     */
    private final DriverType driverType;

    /**
     * Driver 实例
     */
    private final CrawlDriver driver;

    /**
     * 原始数据存储
     */
    private final RawStorage rawStorage;

    /**
     * 结构化数据存储
     */
    private final DataStorage dataStorage;

    /**
     * 变量 Map（用于 Action 之间传递数据）
     */
    private final Map<String, Object> variables = new ConcurrentHashMap<>();

    /**
     * 认证配置,用于在使用 session 时，根据这些配置加载认证信息
     */
    @Setter
    private List<AuthConfig> authConfigs;

    /**
     * 表达式解析器
     */
    private final ExpressionResolver expressionResolver = new CompositeExpressionResolver();

    /**
     * 是否取消
     * 用于控制取消执行
     */
    @Setter
    private Boolean isCancel = false;

    /**
     * 创建运行时上下文
     */
    public RuntimeContext(CrawlRuntimeOptions options,
                          DriverType driverType,
                          CrawlDriver driver,
                          RawStorage rawStorage,
                          DataStorage dataStorage) {
        this.options = options;
        this.driverType = driverType;
        this.driver = driver;
        this.rawStorage = rawStorage;
        this.dataStorage = dataStorage;
    }

    /**
     * 获取变量 Map
     */
    public Map<String, Object> getVariables() {
        return variables;
    }

    /**
     * 设置变量
     */
    public void setVariable(String key, Object value) {
        variables.put(key, value);
    }

    /**
     * 获取变量
     */
    public <T> T getVariable(String key) {
        return (T) variables.get(key);
    }

    /**
     * 解析表达式
     */
    public String resolveExpression(String template) {
        if (template == null) return null;
        Object result = expressionResolver.resolve(template, variables);
        return result != null ? result.toString() : null;
    }

    /**
     * 解析条件表达式
     */
    public boolean resolveCondition(String expression) {
        return expressionResolver.resolveAsBoolean(expression, variables);
    }

}
