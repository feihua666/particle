package com.particle.global.dag;

import com.particle.global.dag.engine.DagEngine;
import com.particle.global.dag.engine.DefaultDagEngine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * DAG引擎自动配置类
 * </p>
 *
 * @author Claude
 * @since 2026-01-09 10:22:40
 */
@Configuration
public class GlobalDAGAutoConfiguration {

    /**
     * DAG引擎Bean
     * @return DAG引擎实例
     */
    // @Bean
    // @ConditionalOnBean
    public DagEngine dagEngine() {
        return new DefaultDagEngine();
    }
}
