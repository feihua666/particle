package com.particle.user.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import com.particle.user.client.identifier.dto.command.UserIdentifierSimpleCreateCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierSimpleUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@PropValid
@Data
@Schema
public class UserUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "姓名，真实姓名")
    private String name;

    @NotEmpty(message = "昵称 不能为空")
    @Schema(description = "昵称，模糊查询",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    /**
     * 真实用户性别必填
     */
    @SetNullWhenNull
    @PropValid.DependCondition(message = "性别不能为空" ,dependProp = "isVirtual",ifEqual = "false")
    @Schema(description = "性别，字典id")
    private Long genderDictId;

    @Schema(description = "头像，建议图片相对路径")
    private String avatar;

    @Schema(description = "用户编号，可以做为员工编号")
    private String serialNo;

    @Schema(description = "公司id，冗余字段，由dept_id对应公司派生")
    private Long compId;

    @Schema(description = "部门id")
    private Long deptId;

    @NotNull(message = "是否虚拟用户不能为空")
    @Schema(description = "是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @NotNull(message = "是否锁定不能为空")
    @Schema(description = "是否锁定，0=未锁定；1=锁定")
    private Boolean isLock;

    @SetNullWhenNull
    @PropValid.DependCondition(message = "锁定原因不能为空" ,dependProp = "isLock",ifEqual = "true")
    @Schema(description = "锁定原因")
    private String lockReason;

    @NotNull(message = "用户分类不能为空")
    @Schema(description = "用户分类字典，标识是哪一类用户，比如后台用户等",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryDictId;

    @Schema(description = "分组标识")
    private String groupFlag;

    @NotNull(message = "用户来源不能为空")
    @Schema(description = "用户来源，字典id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long sourceFromDictId;

    @NotNull(message = "是否过期不能为空")
    @Schema(description = "是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @SetNullWhenNull
    @PropValid.DependCondition(message = "过期原因不能为空" ,dependProp = "isExpired",ifEqual = "true")
    @Schema(description = "过期原因")
    private String expiredReason;

    @Schema(description = "到期时间，为空永不到期")
    private LocalDateTime expireAt;

	@Schema(description = "生效日期，从什么时候开始生效")
	private LocalDateTime effectiveAt;

    @SetNullWhenNull
	@Schema(description = "生效日期，触发方式，一般为首次登录触发")
	private Long effectiveAtTriggerDictId;

	@Schema(description = "有效天数,0或空为不限制")
	private Integer effectiveDays;

	@Schema(description = "备注")
	private String remark;

    /**
     * 账号信息
     */
    @Schema(description = "登录标识，支持多个")
    private List<UserIdentifierSimpleUpdateCommand> identifiers;

    /**
     * 角色id，在依赖 角色 组件时可用
     */
    @Schema(description = "角色id")
    private List<Long> roleIds;

    @Schema(description = "用户扩展信息")
    private UserExtraInfoCommand userExtraInfo;
}
