package com.particle.global.dto.login;


import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "授权的租户")
public class GrantedTenant extends DTO {

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

	@Schema(description = "是否正式，1=正式，0=试用")
	private Boolean isFormal;

	public static GrantedTenant create(Long id, String code, String name,
									   String tenantThemeJson,
									   String tenantDefaultRouteJson,
									   String tenantLogoJson,
									   String configJson,
									   Boolean isFormal) {
		GrantedTenant grantedTenant = new GrantedTenant();
		grantedTenant.id = id;
		grantedTenant.code = code;
		grantedTenant.name = name;
		grantedTenant.tenantThemeJson = tenantThemeJson;
		grantedTenant.tenantDefaultRouteJson = tenantDefaultRouteJson;
		grantedTenant.tenantLogoJson = tenantLogoJson;
		grantedTenant.configJson = configJson;
		grantedTenant.isFormal = isFormal;
		return grantedTenant;
	}
}
