package com.particle.global.crawler.config;

import com.particle.global.crawler.runtime.CrawlRuntimeOptions;
import com.particle.global.crawler.runtime.config.BrowserConfig;
import com.particle.global.crawler.runtime.config.HttpConfig;
import com.particle.global.crawler.runtime.config.StorageConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 爬虫配置属性（Spring Boot 配置入口）
 * <p>
 * 仅作为 Spring Boot 配置绑定入口，实际配置类在 runtime.config 包中。
 * </p>
 *
 * <p>配置示例：</p>
 * <pre>
 * particle:
 *   crawler:
 *     http:
 *       timeout: 10000
 *       user-agent: Mozilla/5.0
 *     browser:
 *       headless: true
 *       timeout: 30000
 *     storage:
 *       raw-storage-path: ./data/raw
 * </pre>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
@Data
@ConfigurationProperties(prefix = "particle.global.crawler")
public class CrawlerProperties {

    /**
     * 默认运行时选项（全局配置）
     * <p>
     * 运行时会将此默认选项与传入的 CrawlRuntimeOptions 合并。
     * </p>
     */
    private CrawlRuntimeOptions defaultOptions = CrawlRuntimeOptions.defaultOptions();
}
