package com.particle.area;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2022-07-14 13:27:50
 */
@Slf4j
@SpringBootApplication
public class AreaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AreaApplication.class, args);

		log.info("===============================================");
		log.info("{} stated",AreaApplication.class.getSimpleName());
		log.info("===============================================");
	}
}
