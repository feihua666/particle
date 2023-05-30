package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 租户用户 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@ApiModel
public class TenantUserCreateCommand extends AbstractBaseCommand {


    @ApiModelProperty(value = "用户id，可以直接关联用户id或输入邮箱和手机号")
    private Long userId;

    @ApiModelProperty(value = "联系人邮箱，没有指定 applyUserId 时，用户创建用户登录账号")
    private String userEmail;

    @ApiModelProperty(value = "联系人电话，没有指定 applyUserId 时，用户创建用户登录账号")
    private String userPhone;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @NotNull(message = "是否过期 不能为空")
    @ApiModelProperty(value = "是否过期",required = true)
    private Boolean isExpired;

    @ApiModelProperty(value = "过期原因")
    private String expiredReason;

    @ApiModelProperty(value = "到期时间")
    private LocalDateTime expireAt;

    @ApiModelProperty("是否离职或退出")
    private Boolean isLeave;

    @ApiModelProperty("离职或退出原因")
    private String leaveReason;

    @ApiModelProperty("离职或退出时间")
    private LocalDateTime leaveAt;

    @ApiModelProperty("是否正式，1=正式，0=试用")
    private Boolean isFormal;

    @ApiModelProperty(value = "租户id，不填写默认当前租户")
    private Long tenantId;

    /**
     * 部门id， 在依赖 角色 组件时可用
     */
    @ApiModelProperty(value = "部门id")
    private Long deptId;
    /**
     * 角色id，在依赖 角色 组件时可用
     */
    @ApiModelProperty("角色id")
    private List<Long> roleIds;

    @ApiModelProperty(value = "当前登录用户id，用于发送消息时填写发送人",hidden = true)
    private Long currentUserId;


}