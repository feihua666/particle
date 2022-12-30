package com.particle.user.client.login.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@ApiModel
public class UserLoginRecordVO extends AbstractBaseIdVO {


    @ApiModelProperty("用户id")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @ApiModelProperty("用户昵称")
    private String userNickname;

    @ApiModelProperty("登录时间")
    private LocalDateTime loginAt;

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @ApiModelProperty("设备名称，如：xxx的Iphone")
    private String deviceName;

    @ApiModelProperty("登录标识id")
    private Long userIdentifierId;

    @TransBy(tableName = TransTableNameConstants.component_user_identifier, byFieldName = "userIdentifierId", mapValueField = "identifier")
    @ApiModelProperty("登录标识")
    private String userIdentifier;

    @ApiModelProperty("操作系统及版本")
    private String operatingSystem;

    @ApiModelProperty("会话标识，登录成功后的会话")
    private String session;

    @ApiModelProperty("会话标识的md5摘要值，考虑到会话标识可能会很长")
    private String sessionMd5;

    @ApiModelProperty("登录是否成功")
    private Boolean isSuccess;

    @ApiModelProperty("失败原因")
    private String failedReason;

    @ApiModelProperty("追踪id")
    private String traceId;

    @ApiModelProperty("api数量，该次登录请求的api数，登录算第1次")
    private Long apiCount;

    @ApiModelProperty("最后活跃时间")
    private LocalDateTime lastActiveAt;

    @ApiModelProperty("退出登录时间")
    private LocalDateTime logoutAt;


}
