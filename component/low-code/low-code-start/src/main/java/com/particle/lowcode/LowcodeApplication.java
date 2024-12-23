package com.particle.lowcode;

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
 * @since 2023-01-03 13:17:50
 */
@Slf4j
@SpringBootApplication
public class LowcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LowcodeApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", LowcodeApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
