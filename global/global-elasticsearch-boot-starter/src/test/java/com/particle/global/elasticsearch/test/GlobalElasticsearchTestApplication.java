package com.particle.global.elasticsearch.test;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 全局 elasticsearch 测试应用启动类
 * </p>
 *
 * @author yangwei
 * @since 2023/12/7 12:45
 */
@Slf4j
@SpringBootApplication
public class GlobalElasticsearchTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GlobalElasticsearchTestApplication.class, args);


        log.info("===============================================");
        log.info("{} stated", GlobalElasticsearchTestApplication.class.getSimpleName());
        log.info("===============================================");

    }
    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }
}
