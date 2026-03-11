package com.particle.gateway.webflux;

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
 * Gateway WebFlux 启动类
 * 基于 Spring WebFlux 的响应式网关服务
 * </p>
 *
 * @author yangwei
 * @since 2026/3/3 14:08
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWebfluxApplication.class, args);

        log.info("===============================================");
        log.info("{} stated", GatewayWebfluxApplication.class.getSimpleName());
        log.info("===============================================");
    }
    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }
}
