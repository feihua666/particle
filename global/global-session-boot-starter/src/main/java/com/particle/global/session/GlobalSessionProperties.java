package com.particle.global.session;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 全局session配置
 * </p>
 *
 * @author yangwei
 * @since 2023-07-24 13:36
 */
@ConfigurationProperties(prefix = "particle.session")
@Data
public class GlobalSessionProperties {

	/**
	 * jdbc配置
	 */
	private GlobalSessionJdbcProperties jdbc;

	/**
	 * jdbc配置
	 */
	@Data
	public static class GlobalSessionJdbcProperties {
		/**
		 * {@link SessionRepositoryConfiguration.DatabaseDialect}
		 */
		private String type;
	}

}
