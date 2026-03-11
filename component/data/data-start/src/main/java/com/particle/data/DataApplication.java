package com.particle.data;

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
 * @since 2024-07-13 22:53:56
 */
@Slf4j
// 经尝试，该注解必须添加到启动类上，否则启动报错
// common-infrastructure 依赖了 global-cache-boot-starter，该依赖中定义了缓存注解，需要添加该注解
@EnableCaching
@SpringBootApplication
@EnableFeignClients(basePackages = {
		"com.particle.dict.adapter.feign.client.rpc",
		"com.particle.dataquery.adapter.feign.client.dataapi.rpc",
		"com.particle.area.adapter.feign.client.rpc",
		"com.particle.tenant.adapter.feign.client.rpc",
		// 主要是将支持 global openapi 实现转发
		"com.particle.openplatform.adapter.feign.client.globalopenapi.rpc",
})
public class DataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);

		log.info("===============================================");
		log.info("{} stated", DataApplication.class.getSimpleName());
		log.info("===============================================");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
	}
}
