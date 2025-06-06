package com.particle.particledemo;

import com.particle.global.captcha.security.DefaultCaptchaSecurityChecker;
import com.particle.global.session.SessionRepositoryConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

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
// @SpringBootApplication
// 必须在启动类添加 切面支持，否则有可能 component 注解的扫描不到
@EnableAspectJAutoProxy
// 经尝试，该注解必须添加到启动类上，否则启动报错
@EnableCaching
// 如果需要添加安全保护，需要注释下方排除代码
//@SpringBootApplication(exclude = {GlobalSecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
// neo4j默认引入包就会自动配置，并未提供停用的功能，这里排除掉，如果需要再添加上即可
// elasticsearch默认引入包就会自动配置，且actuator也自动装配进行建康检查打印异常（但并不影响启动）并未提供停用的功能，这里排除掉，如果需要再添加上即可
@SpringBootApplication(exclude = {Neo4jAutoConfiguration.class, ElasticsearchRestClientAutoConfiguration.class})
@Import(SessionRepositoryConfiguration.class)
public class ParticleProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParticleProjectApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", ParticleProjectApplication.class.getSimpleName());
		log.info("===============================================");
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}

	@Bean
	public DefaultCaptchaSecurityChecker defaultCaptchaSecurityChecker(){
		return new DefaultCaptchaSecurityChecker();
	}
}
