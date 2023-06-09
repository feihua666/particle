package com.particle.global.scheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 全局定时任务计划自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 15:10
 */
@EnableScheduling
@Configuration
@ComponentScan
public class GlobalSchedulerAutoConfiguration {
}
