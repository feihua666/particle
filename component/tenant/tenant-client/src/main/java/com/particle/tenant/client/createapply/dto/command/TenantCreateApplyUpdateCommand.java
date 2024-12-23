package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

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
public class TenantCreateApplyUpdateCommand extends AbstractBaseUpdateCommand {

    @NotEmpty(message = "租户名称 不能为空")
    @Schema(description = "租户名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


	@Schema(description = "姓名，没有指定 applyUserId 时，用户创建用户昵称和姓名")
	private String userName;

	@Schema(description = "账号，没有指定 applyUserId 时，用户创建用户登录账号")
	private String account;


	@Schema(description = "邮箱，没有指定 applyUserId 时，用户创建用户登录账号")
	private String email;


	@Schema(description = "手机号，没有指定 applyUserId 时，用户创建用户登录账号")
	private String mobile;

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private String password;

	@Schema(description = "是否发送邮件通知，1=发送，0=不发送，仅存在邮箱时发送")
	private Boolean isSendEmailNotice;

	@Schema(description = "密码，没有指定 applyUserId 时，用户创建用户登录密码")
	private Boolean isSendMobileNotice;


	@NotNull(message = "租户类型字典id 不能为空")
	@Schema(description = "租户类型字典id",requiredMode = Schema.RequiredMode.REQUIRED)
	private Long tenantTypeDictId;


	@Schema(description = "申请用户，如果主空意味着联系邮箱和联系电话必须填写一个，将用户创建关联用户")
	private Long applyUserId;

	@NotNull(message = "是否正式 不能为空")
	@Schema(description = "是否正式，1=正式，0=试用",requiredMode = Schema.RequiredMode.REQUIRED)
	private Boolean isFormal;

	@Schema(description = "用户数量限制")
	private Integer userLimitCount;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@Schema(description = "申请天数")
	private Integer effectiveDays;

	@Schema(description = "过期时间，从什么时候失效")
	private LocalDateTime expireAt;

	@Schema(description = "额外申请项json，如：应用和功能")
	private String extJson;
	/**
	 * extJson 的对象形式，如果有值，以该字段优先
	 */
	@Schema(description = "额外申请项json，如：应用和功能")
	private TenantCreateApplyExtJsonCommand extJsonObj;

    @Schema(description = "描述")
    private String remark;

}
