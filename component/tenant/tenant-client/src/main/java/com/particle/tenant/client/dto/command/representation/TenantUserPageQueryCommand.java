package com.particle.tenant.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@Schema
public class TenantUserPageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "生效日期，触发方式，一般为首次登录触发")
	private Long effectiveAtTriggerDictId;

	@Schema(description = "有效天数,0或空为不限制")
	private Integer effectiveDays;

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