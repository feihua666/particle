package com.particle.global.neo4j.test;

import com.particle.global.neo4j.dto.basic.INeo4jCurrentUserResolver;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 *
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
