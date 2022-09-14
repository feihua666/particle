package com.particle.particledemo;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.security.GlobalSecurityAutoConfiguration;
import com.particle.global.session.MapSessionRepositoryConfig;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
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
 * @author yw
 * @since 2022-07-19 17:39:46
 */
@Slf4j
@SpringBootApplication(exclude = {GlobalSecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
@Import(MapSessionRepositoryConfig.class)
public class ParticleDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParticleDemoApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ParticleDemoApplication.class.getSimpleName());
		log.info("===============================================");
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
