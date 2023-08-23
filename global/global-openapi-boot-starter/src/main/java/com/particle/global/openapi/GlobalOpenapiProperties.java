package com.particle.global.openapi;

import com.particle.global.openapi.api.impl.InMemoryGlobalOpenapiClientProviderImpl;
import com.particle.global.openapi.data.OpenapiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 开放接口相关配置
 * </p>
 *
 * @author yangwei
 * @since 2023-08-01 14:45
 */
@Data
@ConfigurationProperties(prefix = "particle.openapi")
public class GlobalOpenapiProperties {
	/**
	 * 将哪些额外 url 纳入开放接口
	 */
	private Set<String> urlPatterns = new LinkedHashSet<>();
	/**
	 * 提供配置,以供内存级别配置使用，这将使用 {@link InMemoryGlobalOpenapiClientProviderImpl} 实现
	 */
	private List<OpenapiClient> clients;

	/**
	 * 强制输出json，否则仅请求为可授受json时输出json
	 */
	private Boolean forceOutputJsonOnBaseException = true;
}
