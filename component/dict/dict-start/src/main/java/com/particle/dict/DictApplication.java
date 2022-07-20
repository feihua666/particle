package com.particle.dict;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2022-07-18 22:54:08
 */
@Slf4j
@SpringBootApplication
public class DictApplication {
	public static void main(String[] args) {
		SpringApplication.run(DictApplication.class, args);

		log.info("===============================================");
		log.info("{} stated",DictApplication.class.getSimpleName());
		log.info("===============================================");
	}

}
