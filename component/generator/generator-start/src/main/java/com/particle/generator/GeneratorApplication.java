package com.particle.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 代码生成器项目启动类
 * </p>
 *
 * @author yangwei
 * @since 2022-05-16 21:50
 */
@Slf4j
@SpringBootApplication
public class GeneratorApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", GeneratorApplication.class.getSimpleName());
		log.info("===============================================");
	}


}
