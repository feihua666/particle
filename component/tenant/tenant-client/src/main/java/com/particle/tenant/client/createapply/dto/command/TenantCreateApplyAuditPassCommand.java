package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 租户创建申请 默认审批通过指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-15 16:17:03
 */
@Data
@Schema
public class TenantCreateApplyAuditPassCommand extends AbstractBaseCommand {


    @NotNull(message = "审核意见 不能为空")
    @Schema(description = "审核意见",requiredMode = Schema.RequiredMode.REQUIRED)
    private String auditStatusComment;

    /**
     * 租户超级管理员角色编码
     */
    @Schema(description = "租户超级管理员角色编码",hidden = true)
    private String tenantSuperAdminRoleCode;

    @Schema(description = "审核人用户id",hidden = true)
    private Long auditUserId;
}
