package com.particle.global.bootstrap.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Duration;

/**
 * Spring Boot 启动耗时监听器
 * @author yangwei
 * @since 2025/12/31 11:18
 */
@Slf4j
public class StartupTimeListener implements SpringApplicationRunListener {

    private long startTime;

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        start();
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        end();
    }

    private void start() {
        this.startTime = System.currentTimeMillis();
    }
    private void end() {
        // 计算启动耗时（毫秒）
        long endTime = System.currentTimeMillis();
        long startupTime = endTime - startTime;

        // 格式化输出（毫秒/秒，便于阅读）
        String timeMsg = startupTime < 1000
                ? startupTime + " ms"
                : String.format("%.2f s", startupTime / 1000.0);

        // 打印启动耗时（可结合日志框架，如 logback/log4j2）
        log.info("========================================");
        log.info("application started，duration：" + timeMsg);
        log.info("========================================");
    }

}
