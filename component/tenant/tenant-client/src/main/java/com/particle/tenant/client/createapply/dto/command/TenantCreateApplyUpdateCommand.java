package com.particle.tenant.client.createapply.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@ApiModel
public class TenantCreateApplyUpdateCommand extends AbstractBaseUpdateCommand {

    @NotEmpty(message = "租户名称 不能为空")
    @ApiModelProperty(value = "租户名称",required = true)
    private String name;

    @ApiModelProperty(value = "联系人姓名")
    private String contactUserName;

    @ApiModelProperty(value = "联系人邮箱")
    private String contactUserEmail;

    @ApiModelProperty(value = "联系人电话")
    private String contactUserPhone;

    @NotNull(message = "租户类型字典id 不能为空")
    @ApiModelProperty(value = "租户类型字典id",required = true)
    private Long tenantTypeDictId;

    @NotNull(message = "申请用户 不能为空")
    @ApiModelProperty(value = "申请用户",required = true)
    private Long applyUserId;

    @ApiModelProperty(value = "审核用户id")
    private Long auditUserId;

    @ApiModelProperty(value = "审核通过后创建的租户id")
    private Long appliedTenantId;


	@NotNull(message = "是否正式 不能为空")
	@ApiModelProperty(value = "是否正式，1=正式，0=试用",required = true)
	private Boolean isFormal;

	@ApiModelProperty("用户数量限制")
	private Integer userLimitCount;

	@ApiModelProperty("生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

	@ApiModelProperty("申请天数")
	private Integer effectiveDays;

	@ApiModelProperty("失效日期，从什么时候失效")
	private LocalDateTime invalidAt;

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