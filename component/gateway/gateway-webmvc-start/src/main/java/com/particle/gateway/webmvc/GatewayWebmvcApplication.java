package com.particle.gateway.webmvc;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * Gateway WebMvc 启动类
 * 基于 Spring WebMvc 的网关服务
 * </p>
 *
 * @author yangwei
 * @since 2026/3/3 14:07
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayWebmvcApplication {
    public static void main(String[] args) {
        // 设置系统属性
        System.setProperty("http.maxConnections", "50");
        System.setProperty("http.keepAlive", "true");
        System.setProperty("http.timeout", "30000");
        SpringApplication.run(GatewayWebmvcApplication.class, args);

        log.info("===============================================");
        log.info("{} stated", GatewayWebmvcApplication.class.getSimpleName());
        log.info("===============================================");
    }
    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }
}
