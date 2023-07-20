package com.particle.tenant.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class TenantUserCreateCommand extends AbstractBaseCommand {


    @Schema(description = "用户id，可以直接关联用户id或输入邮箱和手机号")
    private Long userId;

    @Schema(description = "账号，没有指定 userId 时，用户创建用户登录账号")
    private String userAccount;

    @Schema(description = "邮箱，没有指定 userId 时，用户创建用户登录账号")
    private String userEmail;

    @Schema(description = "手机号，没有指定 userId 时，用户创建用户登录账号")
    private String userMobile;

    @Schema(description = "密码，没有指定 userId 时，用户创建用户登录密码")
    private String password;


    @Schema(description = "是否发送邮件通知，1=发送，0=不发送，仅存在邮箱时发送")
    private Boolean isSendEmailNotice;

    @Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
    private Boolean isSendMobileNotice;

    @Schema(description = "真实姓名")
    private String name;

    @NotNull(message = "是否过期 不能为空")
    @Schema(description = "是否过期",required = true)
    private Boolean isExpired;

    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间")
    private LocalDateTime expireAt;

    @Schema(description = "是否离职或退出")
    private Boolean isLeave;

    @Schema(description = "离职或退出原因")
    private String leaveReason;

    @Schema(description = "离职或退出时间")
    private LocalDateTime leaveAt;

    @Schema(description = "是否正式，1=正式，0=试用")
    private Boolean isFormal;

    @Schema(description = "租户id，不填写默认当前租户")
    private Long tenantId;

    /**
     * 部门id， 在依赖 角色 组件时可用
     */
    @Schema(description = "部门id")
    private Long deptId;
    /**
     * 角色id，在依赖 角色 组件时可用
     */
    @Schema(description = "角色id")
    private List<Long> roleIds;

    @Schema(description = "当前登录用户id，用于发送消息时填写发送人",hidden = true)
    private Long currentUserId;


}