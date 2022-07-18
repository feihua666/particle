package com.particle.test.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 简单用户 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@ApiModel(value="简单用户 通用创建指令对象")
public class UserSimpleCreateCommand extends AbstractBaseCommand {


    @ApiModelProperty("昵称，姓名,模糊查询")
    private String nickname;

    @ApiModelProperty("性别，字典id")
    private Long genderDictId;

    @ApiModelProperty("头像，图片绝对路径")
    private String avatar;

    @ApiModelProperty("锁定状态，0=未锁定；1=锁定")
    private Boolean isLock;

    @ApiModelProperty("锁定原因")
    private String lockReason;

    @ApiModelProperty("分组标识")
    private String groupFlag;

    @ApiModelProperty("用户来源，字典id")
    private Long sourceFromDictId;


}
