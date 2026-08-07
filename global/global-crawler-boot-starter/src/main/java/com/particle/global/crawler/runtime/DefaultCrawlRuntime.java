package com.particle.global.crawler.runtime;

import cn.hutool.core.date.LocalDateTimeUtil;

import cn.hutool.core.lang.UUID;
import com.particle.global.crawler.common.enums.DriverType;
import com.particle.global.crawler.common.enums.SessionStatus;
import com.particle.global.crawler.config.CrawlerProperties;
import com.particle.global.crawler.driver.CrawlDriver;
import com.particle.global.crawler.driver.DriverFactory;
import com.particle.global.crawler.exception.SessionBusyException;
import com.particle.global.crawler.pipeline.CrawlPipeline;
import com.particle.global.crawler.runtime.session.CrawlSession;
import com.particle.global.crawler.runtime.session.CrawlSessionManager;
import com.particle.global.crawler.runtime.session.auth.AuthConfig;
import com.particle.global.crawler.storage.data.DataStorage;
import com.particle.global.crawler.storage.raw.RawStorage;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫运行时（核心入口）
 * <p>
 * 整合了 Driver 选择、Session 管理、Pipeline 执行的核心功能。
 * </p>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Slf4j
public class DefaultCrawlRuntime implements CrawlRuntime {

    /**
     * Driver 工厂
     */
    private final DriverFactory driverFactory;

    /**
     * 运行时执行器
     */
    private final RuntimeExecutor runtimeExecutor;

    /**
     * Session 管理器
     */
    private final CrawlSessionManager crawlSessionManager;

    /**
     * 爬虫配置
     */
    private final CrawlerProperties properties;

    /**
     * 原始数据存储
     */
    private RawStorage rawStorage;
    /**
     * 结构数据存储
     */
    private DataStorage dataStorage;

    public DefaultCrawlRuntime(DriverFactory driverFactory,
                               RuntimeExecutor runtimeExecutor,
                               CrawlSessionManager crawlSessionManager,
                               RawStorage rawStorage,
                               DataStorage dataStorage,
                               CrawlerProperties properties) {
        this.driverFactory = driverFactory;
        this.runtimeExecutor = runtimeExecutor;
        this.crawlSessionManager = crawlSessionManager;
        this.rawStorage = rawStorage;
        this.dataStorage = dataStorage;
        this.properties = properties;
    }

    /**
     * 执行 Pipeline
     *
     * @param pipeline Pipeline
     * @return 执行结果
     */
    public CrawlExecuteHandle execute(CrawlPipeline pipeline) {
        CrawlRuntimeOptions options = createDefaultOptions();
        return execute(pipeline, options);
    }

    /**
     * 执行 Pipeline（带选项）
     *
     * @param pipeline Pipeline
     * @param options  运行时选项（可以为 null，使用默认选项）
     * @return 执行结果
     */
    public CrawlExecuteHandle execute(CrawlPipeline pipeline, CrawlRuntimeOptions options) {

        log.info("开始执行 Pipeline: {}", pipeline.getName());

        CrawlExecuteHandle crawlExecuteHandle = new CrawlExecuteHandle();
        crawlExecuteHandle.setPipeline(pipeline);
        crawlExecuteHandle.setStartTime(LocalDateTime.now());
        crawlExecuteHandle.setStatus(RuntimeStatus.RUNNING);

        CrawlSession crawlSession = null;
        RuntimeContext runtimeContext = null;
        DriverType driverType = null;
        CrawlDriver driver = null;
        Boolean isAutoClose = null;
        try {
            // 合并默认选项和运行时选项
            CrawlRuntimeOptions mergedOptions = mergeOptions(options);


            // session 处理
            if (mergedOptions.getSession().getIsUseSession()) {
                String sessionId = mergedOptions.getSession().getSessionId();

                crawlSession = crawlSessionManager.getSession(sessionId);
                // session 不存在，创建 session
                if (crawlSession == null) {
                    crawlSession = crawlSessionManager.createSession(mergedOptions);
                }
                // session 存在直接使用
                else{
                    if (crawlSession.getStatus() != SessionStatus.IDLE) {
                        throw new SessionBusyException("Session is busy,sessionId=" + sessionId);
                    }
                }

                crawlSession.setStatus(SessionStatus.RUNNING);
                driverType = crawlSession.getDriverType();
                driver = crawlSession.getDriver();
                // 使用 session 不能关闭
                isAutoClose = false;
            }
            // 不使用 session
            else{
                // 创建 Driver
                driver = driverFactory.create(mergedOptions);
                isAutoClose = mergedOptions.getDeriver().getIsAutoClose();
            }

            // 创建 Runtime Context
            runtimeContext = new RuntimeContext(
                    mergedOptions,
                    driverType,
                    driver,
                    rawStorage,
                    dataStorage
            );

            crawlExecuteHandle.setRuntimeContext(runtimeContext);
            // 合并初始变量
            if (pipeline.getVariables() != null) {
                runtimeContext.getVariables().putAll(pipeline.getVariables());
                List<AuthConfig> authConfigList = new ArrayList<>();
                List<AuthConfig> varAuthConfigs = AuthConfig.parse(pipeline.getVariables());
                List<AuthConfig> sessionAuthConfigs = options.getSession().getAuthConfigs();
                if (varAuthConfigs != null) {
                    authConfigList.addAll(varAuthConfigs);
                }
                if (sessionAuthConfigs != null) {
                    authConfigList.addAll(sessionAuthConfigs);
                }
                runtimeContext.setAuthConfigs(authConfigList);
            }
            // 在真正执行之前，尝试加载认证信息
            if (crawlSession != null) {
                crawlSession.loadAuth(runtimeContext.getAuthConfigs());
            }
            // 执行 Pipeline
            runtimeExecutor.execute(pipeline, runtimeContext);

            // 结果数据
            List<String> resultedDataKeys = pipeline.resultDataKeys();
            if (resultedDataKeys != null) {
                Map<String, Object> resultedData = new HashMap<>();
                for (String resultedDataKey : resultedDataKeys) {
                    Object variable = runtimeContext.getVariable(resultedDataKey);
                    resultedData.put(resultedDataKey, variable);
                }
                crawlExecuteHandle.setResultData(resultedData);
            }

            log.info("Pipeline 执行成功: {}", pipeline.getName());
            crawlExecuteHandle.setStatus(RuntimeStatus.SUCCESS);
            return crawlExecuteHandle;

        } catch (Exception e) {
            crawlExecuteHandle.setStatus(RuntimeStatus.FAILED);
            log.error("Pipeline 执行失败: {}", pipeline.getName(), e);
            return crawlExecuteHandle;

        } finally {
            crawlExecuteHandle.setEndTime(LocalDateTime.now());
            if (isAutoClose != null && isAutoClose) {
                // 清理 Driver
                if (driver != null) {
                    try {
                        driver.close();
                    } catch (Exception e) {
                        log.warn("关闭 Driver 失败", e);
                    }
                }
            }
            if (crawlSession != null) {
                // 在执行完后，尝试保存认证信息
                if (runtimeContext != null) {
                    crawlSession.saveAuth(runtimeContext.getAuthConfigs());
                }

                crawlSession.setStatus(SessionStatus.IDLE);
            }
            log.info("Pipeline 执行完成: {}, 耗时: {}ms",
                    pipeline.getName(),
                    LocalDateTimeUtil.between(crawlExecuteHandle.getStartTime(), crawlExecuteHandle.getEndTime()).toMillis());
        }
    }

    /**
     * 创建默认选项
     * <p>
     * driverType 默认不设置，让系统根据 Pipeline 的 capabilities 自动选择。
     * </p>
     */
    private CrawlRuntimeOptions createDefaultOptions() {
        return CrawlRuntimeOptions.defaultOptions();
    }

    /**
     * 合并默认选项和运行时选项
     *
     * @param runtimeOptions 运行时选项
     * @return 合并后的选项
     */
    private CrawlRuntimeOptions mergeOptions(CrawlRuntimeOptions runtimeOptions) {
        if (runtimeOptions == null) {
            runtimeOptions = new CrawlRuntimeOptions();
        }
        // 运行时选项优先，默认选项作为兜底
        return runtimeOptions.merge(properties.getDefaultOptions());
    }

    @Override
    public CrawlerProperties getProperties() {
        return properties;
    }
}
