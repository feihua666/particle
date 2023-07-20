package com.particle.tenant.client.tenantfunc.dto.command.representation;

import com.particle.common.client.dto.command.IdCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 租户分配功能菜单，根据租户ID查询已分配的功能菜单id
 * </p>
 *
 * @author yangwei
 * @since 2023-04-19 19:56
 */
@Data
@Schema
public class TenantFuncQueryFuncIdsByTenantIdCommand extends IdCommand {

	/**
	 * 可以根据功能应用id 过滤
	 */
	@Schema(description = "功能应用id")
	private Long funcApplicationId;
}
