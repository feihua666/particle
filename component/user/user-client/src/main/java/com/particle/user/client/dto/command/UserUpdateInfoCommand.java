package com.particle.user.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.validation.props.PropValid;
import com.particle.user.client.login.dto.command.LoginUserUpdateAvatarCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateGenderCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateNameCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateNicknameCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息更新指令对象
 * 适用于单个字段更新的场景
 * </p>
 *
 * @author yw
 * @since 2023-07-13 09:25:09
 */
@PropValid
@Data
@ApiModel
public class UserUpdateInfoCommand extends IdCommand {

    @ApiModelProperty("姓名，真实姓名")
    private String name;

    @ApiModelProperty(value = "昵称，模糊查询")
    private String nickname;

    /**
     * 真实用户性别必填
     */
    @PropValid.DependCondition(message = "性别不能为空" ,dependProp = "isVirtual",ifEqual = "false")
    @ApiModelProperty("性别，字典id")
    private Long genderDictId;

    @ApiModelProperty("头像，建议图片相对路径")
    private String avatar;

    @ApiModelProperty("用户编号，可以做为员工编号")
    private String serialNo;

    @ApiModelProperty("是否虚拟用户，虚拟用户代表不是一个真正存在的用户")
    private Boolean isVirtual;

    @NotNull(message = "是否锁定不能为空")
    @ApiModelProperty("是否锁定，0=未锁定；1=锁定")
    private Boolean isLock;

    @PropValid.DependCondition(message = "锁定原因不能为空" ,dependProp = "isLock",ifEqual = "true")
    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty(value = "用户分类字典，标识是哪一类用户，比如后台用户等")
    private Long categoryDictId;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty(value = "用户来源，字典id")
    private Long sourceFromDictId;

    @NotNull(message = "是否过期不能为空")
    @ApiModelProperty("是否过期，过期后该密码不能登录")
    private Boolean isExpired;

    @PropValid.DependCondition(message = "过期原因不能为空" ,dependProp = "isExpired",ifEqual = "true")
    @ApiModelProperty("过期原因")
    private String expiredReason;

    @ApiModelProperty("到期时间，为空永不到期")
    private LocalDateTime expireAt;


    public static UserUpdateInfoCommand create(Long userId) {
        UserUpdateInfoCommand userUpdateInfoCommand = new UserUpdateInfoCommand();
        return userUpdateInfoCommand;
    }

    public static UserUpdateInfoCommand createByLoginUserUpdateAvatarCommand(LoginUserUpdateAvatarCommand avatarCommand,Long userId) {
        UserUpdateInfoCommand userUpdateInfoCommand = create(userId);
        userUpdateInfoCommand.setAvatar(avatarCommand.getAvatar());
        return userUpdateInfoCommand;
    }
    public static UserUpdateInfoCommand createByLoginUserUpdateGenderCommand(LoginUserUpdateGenderCommand genderCommand, Long userId) {
        UserUpdateInfoCommand userUpdateInfoCommand = create(userId);
        userUpdateInfoCommand.setGenderDictId(genderCommand.getGenderDictId());
        return userUpdateInfoCommand;
    }
    public static UserUpdateInfoCommand createByLoginUserUpdateNameCommand(LoginUserUpdateNameCommand nameCommand, Long userId) {
        UserUpdateInfoCommand userUpdateInfoCommand = create(userId);
        userUpdateInfoCommand.setName(nameCommand.getName());
        return userUpdateInfoCommand;
    }
    public static UserUpdateInfoCommand createByLoginUserUpdateNicknameCommand(LoginUserUpdateNicknameCommand nicknameCommand, Long userId) {
        UserUpdateInfoCommand userUpdateInfoCommand = create(userId);
        userUpdateInfoCommand.setNickname(nicknameCommand.getNickname());
        return userUpdateInfoCommand;
    }
}
