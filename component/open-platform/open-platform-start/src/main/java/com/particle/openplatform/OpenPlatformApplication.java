package com.particle.openplatform;

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
 * @since 2023-08-04 17:28:48
 */
@Slf4j
@SpringBootApplication
public class OpenPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenPlatformApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", OpenPlatformApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
