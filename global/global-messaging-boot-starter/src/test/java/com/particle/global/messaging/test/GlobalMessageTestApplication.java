package com.particle.global.messaging.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

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
 * @since 2022-10-08 15:15
 */
@Slf4j
@SpringBootApplication
public class GlobalMessageTestApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(GlobalMessageTestApplication.class, args);


		log.info("===============================================");
		log.info("{} stated", GlobalMessageTestApplication.class.getSimpleName());
		log.info("===============================================");

	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
