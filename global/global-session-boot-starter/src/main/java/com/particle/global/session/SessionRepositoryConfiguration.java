package com.particle.global.session;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 默认简单map方式内存存储
 * </p>
 *
 * @author yangwei
 * @since 2022-07-29 15:27
 */
@EnableSpringHttpSession
public class SessionRepositoryConfiguration {
	/**
	 * 默认简单map方式内存存储
	 * @return
	 */
	@ConditionalOnBean(SessionRepository.class)
	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository(new ConcurrentHashMap<>());
	}

	/**
	 * 使用 redis存储session时生效
	 */
	@Configuration
	@EnableRedisHttpSession
	@ConditionalOnProperty(prefix = "spring.session",name = "store-type",havingValue = "redis")
	@ConditionalOnClass(EnableRedisHttpSession.class)
	static class SessionRepositoryRedisConfiguration{
	}
	/**
	 * 使用 jdbc存储session时生效
	 */
	@Configuration
	@EnableJdbcHttpSession
	@ConditionalOnProperty(prefix = "spring.session",name = "store-type",havingValue = "jdbc")
	@ConditionalOnClass(EnableJdbcHttpSession.class)
	static class SessionRepositoryJdbcConfiguration{
	}
}
