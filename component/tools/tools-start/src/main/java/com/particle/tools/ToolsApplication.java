package com.particle.tools;

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
 * @since 2023-02-27 15:01:28
 */
@Slf4j
@SpringBootApplication
public class ToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ToolsApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
