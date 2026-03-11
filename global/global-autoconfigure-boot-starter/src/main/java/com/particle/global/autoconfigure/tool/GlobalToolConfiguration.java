package com.particle.global.autoconfigure.tool;

import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.global.tool.spring.SpringContextHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局工具相关自动配置
 * </p>
 *
 * @author yangwei
 * @since 2026/3/6 20:49
 */
@Configuration(proxyBeanMethods = false)
public class GlobalToolConfiguration {


    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public SnowflakeIdTool snowflakeIdTool(){
        return new SnowflakeIdTool();
    }

    @Configuration
    @ConditionalOnClass(name = "io.micrometer.tracing.Tracer")
    public static class TraceToolConfig{
        @Bean
        @ConditionalOnBean(type = "io.micrometer.tracing.Tracer")
        public TraceTool.MicrometerTracingTraceIdGetter micrometerTracingTraceIdGetter(){
            return new TraceTool.MicrometerTracingTraceIdGetter();
        }
        @Bean
        @ConditionalOnBean(type = "io.micrometer.tracing.Tracer")
        public TraceTool.MicrometerTracingTraceScopedStarter micrometerTracingTraceScopedStarter(){
            return new TraceTool.MicrometerTracingTraceScopedStarter();
        }

        @Bean
        public TraceTool traceTool(){
            return new TraceTool();
        }
    }

}
