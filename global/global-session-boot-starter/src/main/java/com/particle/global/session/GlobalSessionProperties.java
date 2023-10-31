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
	 * SessionIdResolver 可选值参见：{@link SessionIdResolver}
	 */
	private String sessionIdResolver;




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

	public static enum SessionIdResolver{
		/**
		 * 包括 header 和 cookie
		 */
		all,
		/**
		 * 仅 header，设置该模式每次调用接口需要在请求头默认传递 {@link com.particle.global.swagger.SwaggerInfo#token},以支持session保持
		 */
		header,
		/**
		 * 仅 cookie,设置该模式前端在接口和前端页面同一个域名下，前端开发无需特别关注
		 */
		cookie;
	}


}
