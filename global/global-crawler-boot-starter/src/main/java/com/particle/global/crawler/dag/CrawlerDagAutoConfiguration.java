package com.particle.global.crawler.dag;

import com.particle.global.dag.runtime.executor.NodeExecutor;
import com.particle.global.dag.runtime.executor.NodeExecutorRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 爬虫DAG自动配置
 */
@Configuration
@ConditionalOnClass(NodeExecutor.class)
public class CrawlerDagAutoConfiguration {

    /**
     * 注册页面抓取节点执行器
     */
    @Bean
    public NodeExecutor pageCrawlNodeExecutor() {
        return new PageCrawlNodeExecutor();
    }

    /**
     * 注册数据提取节点执行器
     */
    @Bean
    public NodeExecutor dataExtractNodeExecutor() {
        return new DataExtractNodeExecutor();
    }

    /**
     * 注册数据处理节点执行器
     */
    @Bean
    public NodeExecutor dataProcessNodeExecutor() {
        return new DataProcessNodeExecutor();
    }

    /**
     * 注册HTTP请求节点执行器
     */
    @Bean
    public NodeExecutor httpRequestNodeExecutor() {
        return new HttpRequestNodeExecutor();
    }

    /**
     * 注册数据存储节点执行器
     */
    @Bean
    public NodeExecutor dataStoreNodeExecutor() {
        return new DataStoreNodeExecutor();
    }

    /**
     * 注册延迟节点执行器
     */
    @Bean
    public NodeExecutor delayNodeExecutor() {
        return new DelayNodeExecutor();
    }

    /**
     * 注册条件判断节点执行器
     */
    @Bean
    public NodeExecutor conditionNodeExecutor() {
        return new ConditionNodeExecutor();
    }

    /**
     * 创建节点执行器注册表
     */
    @Bean
    public NodeExecutorRegistry crawlerNodeExecutorRegistry() {
        NodeExecutorRegistry registry = new NodeExecutorRegistry();

        // 注册所有爬虫相关的节点执行器
        registry.register(pageCrawlNodeExecutor());
        registry.register(dataExtractNodeExecutor());
        registry.register(dataProcessNodeExecutor());
        registry.register(dataStoreNodeExecutor());
        registry.register(httpRequestNodeExecutor());
        registry.register(delayNodeExecutor());
        registry.register(conditionNodeExecutor());

        return registry;
    }
}