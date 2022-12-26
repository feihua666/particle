package com.particle.user.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
@ApiModel
public class UserUpdateCommand extends AbstractBaseUpdateCommand {

    @ApiModelProperty("姓名，真实姓名")
    private String name;

    @NotEmpty(message = "昵称 不能为空")
    @ApiModelProperty(value = "昵称，模糊查询",required = true)
    private String nickname;

    /**
     * 真实用户性别必填
     */
    @PropValid.DependCondition(message = "性别不能为空" ,dependProp = "isVirtual",ifEqual = "false")
    @ApiModelProperty("性别，字典id")
    private Long genderDictId;

    @ApiModelProperty("头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty("用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty("公司id，冗余字段，由dept_id对应公司派生")
    private Long compId;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @ApiModelProperty("锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @SetNullWhenNull
    @PropValid.DependCondition(message = "锁定原因不能为空" ,dependProp = "isLock",ifEqual = "true")
    @ApiModelProperty("锁定原因")
    private String lockReason;

    @NotNull(message = "用户分类不能为空")
    @ApiModelProperty(value = "用户分类字典，标识是哪一类用户，比如后台用户等",required = true)
    private Long categoryDictId;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @NotNull(message = "用户来源不能为空")
    @ApiModelProperty(value = "用户来源，字典id",required = true)
    private Long sourceFromDictId;

    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @SetNullWhenNull
    @PropValid.DependCondition(message = "过期原因不能为空" ,dependProp = "isExpired",ifEqual = "true")
    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;


}
