package com.particle.global.neo4j.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 全局 neo4j 测试应用启动类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 17:52
 */
@Slf4j
@SpringBootApplication
public class GlobalNeo4jTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GlobalNeo4jTestApplication.class, args);


        log.info("===============================================");
        log.info("{} stated", GlobalNeo4jTestApplication.class.getSimpleName());
        log.info("===============================================");

    }
    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }
}
