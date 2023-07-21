package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户用户 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@Schema
public class TenantUserUpdateCommand extends AbstractBaseUpdateCommand {

	@Schema(description = "是否正式，1=正式，0=试用")
	private Boolean isFormal;

    @NotNull(message = "用户id 不能为空")
    @Schema(description = "用户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "真实姓名")
    private String name;

    @NotNull(message = "是否过期 不能为空")
    @Schema(description = "是否过期",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;


    @Schema(description = "到期时间")
    private LocalDateTime expireAt;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "生效日期，触发方式，一般为首次登录触发")
	private Long effectiveAtTriggerDictId;

	@Schema(description = "有效天数,0或空为不限制")
	private Integer effectiveDays;

    @Schema(description = "是否离职或退出")
    private Boolean isLeave;

    @Schema(description = "离职或退出原因")
    private String leaveReason;

    @Schema(description = "离职或退出时间")
    private LocalDateTime leaveAt;

    @NotNull(message = "租户id 不能为空")
    @Schema(description = "租户id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tenantId;
}