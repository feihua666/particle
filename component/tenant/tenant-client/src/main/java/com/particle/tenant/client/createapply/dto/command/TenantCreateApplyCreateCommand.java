package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 租户创建申请 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@ApiModel
public class TenantCreateApplyCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "租户名称 不能为空")
    @ApiModelProperty(value = "租户名称",required = true)
    private String name;


    @ApiModelProperty(value = "姓名，没有指定 applyUserId 时，用户创建用户昵称和姓名")
    private String userName;


    @ApiModelProperty(value = "邮箱，没有指定 applyUserId 时，用户创建用户登录账号")
    private String email;


    @ApiModelProperty(value = "手机号，没有指定 applyUserId 时，用户创建用户登录账号")
    private String mobile;


    @NotNull(message = "租户类型字典id 不能为空")
    @ApiModelProperty(value = "租户类型字典id",required = true)
    private Long tenantTypeDictId;


    @ApiModelProperty(value = "申请用户，如果为空意味着联系邮箱和联系电话必须填写一个，将用户创建关联用户")
    private Long applyUserId;

    /**
     * 默认为未审核，前端填写了也不起作用
     */
    @ApiModelProperty(value = "审核状态",hidden = true)
    private Long auditStatusDictId;

	@NotNull(message = "是否正式 不能为空")
	@ApiModelProperty(value = "是否正式，1=正式，0=试用",required = true)
	private Boolean isFormal;

	@ApiModelProperty("用户数量限制")
	private Integer userLimitCount;

	@ApiModelProperty("生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@ApiModelProperty("申请天数")
	private Integer effectiveDays;

	@ApiModelProperty("过期时间，从什么时候失效")
	private LocalDateTime expireAt;

	@ApiModelProperty("额外申请项json，如：应用和功能")
	private String extJson;

	/**
	 * extJson 的对象形式，如果有值，以该字段优先
	 */
	@ApiModelProperty("额外申请项json，如：应用和功能")
	private TenantCreateApplyExtJsonCommand extJsonObj;

    @ApiModelProperty(value = "描述")
    private String remark;


}