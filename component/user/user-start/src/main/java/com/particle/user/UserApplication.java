package com.particle.user;

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
 * @since 2022-07-19 18:54:10
 */
@Slf4j
@SpringBootApplication
public class UserApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", UserApplication.class.getSimpleName());
		log.info("===============================================");
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
