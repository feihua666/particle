package com.particle.global.security.tenant;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 授权的角色
 * 字符名和{@link com.particle.tenant.client.dto.data.TenantCurrentVO} 保持一致，改的时候一起改，对前端保持一致
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 11:19:19
 */
@Data
@Builder
@Schema(description = "授权的租户")
public class GrantedTenant implements Serializable {

	@Schema(description = "tenant id")
	private Long id;
	@Schema(description = "tenant code")
	private String code;
	@Schema(description = "tenant name")
	private String name;
	@Schema(description = "租户主题")
	private String tenantThemeJson;
	@Schema(description = "租户默认的页面路由")
	private String tenantDefaultRouteJson;
	@Schema(description = "租户logo地址")
	private String tenantLogoJson;
	@Schema(description = "额外配置json")
	private String configJson;


	public static GrantedTenant create(Long id, String code, String name,
									   String tenantThemeJson,
									   String tenantDefaultRouteJson,
									   String tenantLogoJson,
									   String configJson) {
		return GrantedTenant.builder().id(id)
				.code(code)
				.name(name)
				.tenantThemeJson(tenantThemeJson)
				.tenantDefaultRouteJson(tenantDefaultRouteJson)
				.tenantLogoJson(tenantLogoJson)
				.configJson(configJson)
				.build();
	}
}
