package com.particle.global.session;

import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 简单map方式内存存储
 * </p>
 *
 * @author yangwei
 * @since 2022-07-29 15:27
 */
@EnableSpringHttpSession
public class MapSessionRepositoryConfig {
	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository(new ConcurrentHashMap<>());
	}
}
