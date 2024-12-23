package com.particle.tracking.client.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 页面埋点记录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@Schema
public class TrackingPageRecordCreateCommand extends TrackingPageRecordFrontCreateCommand {



    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "操作用户昵称")
    private String userNickname;


    @Schema(description = "操作用户头像")
    private String userAvatar;



    @NotEmpty(message = "会话标识 不能为空")
    @Schema(description = "会话标识",requiredMode = Schema.RequiredMode.REQUIRED)
    private String session;


    @NotEmpty(message = "会话标识的md5摘要值 不能为空")
    @Schema(description = "会话标识的md5摘要值",requiredMode = Schema.RequiredMode.REQUIRED)
    private String sessionMd5;



    @NotEmpty(message = "设备id 不能为空")
    @Schema(description = "设备id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceId;


    @NotEmpty(message = "设备名称 不能为空")
    @Schema(description = "设备名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String deviceName;


    @NotEmpty(message = "操作系统及版本 不能为空")
    @Schema(description = "操作系统及版本",requiredMode = Schema.RequiredMode.REQUIRED)
    private String operatingSystem;


    @Schema(description = "页面停留时长")
    private Long duration;


    @NotEmpty(message = "追踪id 不能为空")
    @Schema(description = "追踪id",requiredMode = Schema.RequiredMode.REQUIRED)
    private String traceId;

    @NotEmpty(message = "请求ip 不能为空")
    @Schema(description = "请求ip",requiredMode = Schema.RequiredMode.REQUIRED)
    private String ip;
}
