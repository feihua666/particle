package com.particle.usagecount;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 09:50:14
 */
@Slf4j
// 经尝试，该注解必须添加到启动类上，否则启动报错
// common-infrastructure 依赖了 global-cache-boot-starter，该依赖中定义了缓存注解，需要添加该注解
@EnableCaching
@SpringBootApplication
@EnableFeignClients(basePackages = {
		"com.particle.dict.adapter.feign.client.rpc",
		"com.particle.tenant.adapter.feign.client.rpc",
})
public class UsageCountApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsageCountApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", UsageCountApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
