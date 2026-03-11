package com.particle.dataconstraint;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2022-12-30 18:20:31
 */
@Slf4j
// 经尝试，该注解必须添加到启动类上，否则启动报错
// common-infrastructure 依赖了 global-cache-boot-starter，该依赖中定义了缓存注解，需要添加该注解
@EnableCaching
@SpringBootApplication
public class DataConstraintApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataConstraintApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", DataConstraintApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
