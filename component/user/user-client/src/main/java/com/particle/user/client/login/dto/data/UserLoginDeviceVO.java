package com.particle.user.client.login.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录设备 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@Schema
public class UserLoginDeviceVO extends AbstractBaseIdVO {


    @Schema(description = "用户id")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "首次登录时间")
    private LocalDateTime loginFirstAt;

    @Schema(description = "最后一次登录时间")
    private LocalDateTime loginLastAt;

    @Schema(description = "设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @Schema(description = "设备名称，如：xxx的Iphone")
    private String deviceName;

    @Schema(description = "验证通过时间")
    private LocalDateTime validateAt;

    @Schema(description = "操作系统及版本")
    private String operatingSystem;


}
