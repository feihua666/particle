package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 租户创建申请 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@Schema
public class TenantCreateApplyAuditCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "审核状态 不能为空")
    @Schema(description = "审核状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long auditStatusDictId;

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
