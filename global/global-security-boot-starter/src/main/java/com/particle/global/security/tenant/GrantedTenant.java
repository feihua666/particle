package com.particle.global.security.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 授权的角色
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 11:19:19
 */
@Data
@Builder
@ApiModel("授权的租户")
public class GrantedTenant implements Serializable {

	@ApiModelProperty("tenant id")
	private Long id;
	@ApiModelProperty("tenant code")
	private String code;
	@ApiModelProperty("tenant name")
	private String name;
	@ApiModelProperty("租户主题")
	private String tenantThemeJson;
	@ApiModelProperty("租户默认的页面路由")
	private String tenantDefaultRouteJson;
	@ApiModelProperty("租户logo地址")
	private String tenantLogoJson;
	@ApiModelProperty("额外配置json")
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
