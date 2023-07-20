package com.particle.user.client.login.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class UserLoginRecordVO extends AbstractBaseIdVO {


    @Schema(description = "用户id")
    private Long userId;

    @TransBy(tableName = TransTableNameConstants.component_user, byFieldName = "userId", mapValueField = "nickname")
    @Schema(description = "用户昵称")
    private String userNickname;

    @Schema(description = "登录时间")
    private LocalDateTime loginAt;

    @Schema(description = "登录ip")
    private String loginIp;

    @Schema(description = "设备id，可以用来唯一标识一个设备")
    private String deviceId;

    @Schema(description = "设备名称，如：xxx的Iphone")
    private String deviceName;

    @Schema(description = "登录标识id")
    private Long userIdentifierId;

    @TransBy(tableName = TransTableNameConstants.component_user_identifier, byFieldName = "userIdentifierId", mapValueField = "identifier")
    @Schema(description = "登录标识")
    private String userIdentifier;

    @Schema(description = "操作系统及版本")
    private String operatingSystem;

    @Schema(description = "会话标识，登录成功后的会话")
    private String session;

    @Schema(description = "会话标识的md5摘要值，考虑到会话标识可能会很长")
    private String sessionMd5;

    @Schema(description = "登录是否成功")
    private Boolean isSuccess;

    @Schema(description = "失败原因")
    private String failedReason;

    @Schema(description = "追踪id")
    private String traceId;

    @Schema(description = "api数量，该次登录请求的api数，登录算第1次")
    private Long apiCount;

    @Schema(description = "最后活跃时间")
    private LocalDateTime lastActiveAt;

    @Schema(description = "退出登录时间")
    private LocalDateTime logoutAt;


}
