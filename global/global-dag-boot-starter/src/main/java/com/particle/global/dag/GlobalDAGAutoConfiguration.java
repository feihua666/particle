package com.particle.global.dag;

import com.particle.global.dag.engine.DagEngine;
import com.particle.global.dag.engine.DefaultDagEngine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * DAG 引擎自动配置类
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Configuration
public class GlobalDAGAutoConfiguration {

    /**
     * DAG 引擎 Bean
     * @return DAG 引擎实例
     */
    @Bean
    @ConditionalOnMissingBean
    public DagEngine dagEngine() {
        return new DefaultDagEngine();
    }
}
