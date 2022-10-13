package com.particle.global.messaging.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration;

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
@SpringBootApplication(exclude = TestSupportBinderAutoConfiguration.class)
public class GlobalMessageTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(GlobalMessageTestApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", GlobalMessageTestApplication.class.getSimpleName());
		log.info("===============================================");
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
