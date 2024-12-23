package com.particle.componentadmin;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2023-04-13 15:41:30
 */
@Slf4j
@SpringBootApplication
public class ComponentAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentAdminApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ComponentAdminApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
