package com.particle.componenttemplate;

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

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 17:46
 */
@Slf4j
@SpringBootApplication
public class ComponentTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentTemplateApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ComponentTemplateApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
