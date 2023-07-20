package com.particle.tenant.client.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 租户用户 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@Schema
public class TenantUserQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "是否正式，1=正式，0=试用")
	private Boolean isFormal;

	@Schema(description = "用户id")
	private Long userId;

	@Like
	@Schema(description = "真实姓名,左前缀匹配")
	private String name;

	@Schema(description = "是否过期")
	private Boolean isExpired;

	@Schema(description = "部门id")
	private Long deptId;

}