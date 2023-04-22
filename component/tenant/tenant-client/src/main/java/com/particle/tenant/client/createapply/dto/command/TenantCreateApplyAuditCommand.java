package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户创建申请 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@ApiModel
public class TenantCreateApplyAuditCommand extends AbstractBaseUpdateCommand {


    @NotNull(message = "审核状态 不能为空")
    @ApiModelProperty(value = "审核状态",required = true)
    private Long auditStatusDictId;

    @NotNull(message = "审核意见 不能为空")
    @ApiModelProperty(value = "审核意见",required = true)
    private String auditStatusComment;

    /**
     * 租户超级管理员角色编码
     */
    @ApiModelProperty(value = "租户超级管理员角色编码",hidden = true)
    private String tenantSuperAdminRoleCode;

    @ApiModelProperty(value = "审核人用户id",hidden = true)
    private Long auditUserId;
}
