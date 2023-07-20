package com.particle.tenant.client.dto.data;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 主要用于登录用户获取租户信息
 * </p>
 *
 * @author yangwei
 * @since 2023-05-30 10:37
 */
@Data
@Schema
public class TenantLoginVO extends TenantVO{

	@Schema(description = "当前用户数量")
	private Integer userCount;
}
