package com.particle.agi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2025-01-08 11:30:42
 */
@Slf4j
@SpringBootApplication
public class AgiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgiApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", AgiApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
