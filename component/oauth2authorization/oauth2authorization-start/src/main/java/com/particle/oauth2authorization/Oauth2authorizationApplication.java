package com.particle.oauth2authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 16:49:22
 */
@Slf4j
@SpringBootApplication
public class Oauth2authorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2authorizationApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", Oauth2authorizationApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
