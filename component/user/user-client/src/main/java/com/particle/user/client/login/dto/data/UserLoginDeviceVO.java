package com.particle.user.client.login.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录设备 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginDeviceVO extends AbstractBaseIdVO {


    @ApiModelProperty("用户id")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @ApiModelProperty("用户昵称")
    private String userNickname;

    @ApiModelProperty("首次登录时间")
    private LocalDateTime loginFirstAt;

    @ApiModelProperty("最后一次登录时间")
    private LocalDateTime loginLastAt;

    @ApiModelProperty("设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @ApiModelProperty("设备名称，如：xxx的Iphone")
    private String deviceName;

    @ApiModelProperty("验证通过时间")
    private LocalDateTime validateAt;

    @ApiModelProperty("操作系统及版本")
    private String operatingSystem;


}
