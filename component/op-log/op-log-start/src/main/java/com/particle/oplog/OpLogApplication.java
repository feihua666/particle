package com.particle.oplog;

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
 * @since 2023-05-08 18:31:05
 */
@Slf4j
@SpringBootApplication
public class OpLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpLogApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", OpLogApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
