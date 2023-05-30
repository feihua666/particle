package com.particle.tenant.client.dto.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TenantLoginVO extends TenantVO{

	@ApiModelProperty("当前用户数量")
	private Integer userCount;
}
