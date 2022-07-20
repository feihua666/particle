package com.particle.particledemo;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2022-07-19 17:39:46
 */
@Slf4j
@SpringBootApplication
public class ParticleDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParticleDemoApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ParticleDemoApplication.class.getSimpleName());
		log.info("===============================================");
	}

}
