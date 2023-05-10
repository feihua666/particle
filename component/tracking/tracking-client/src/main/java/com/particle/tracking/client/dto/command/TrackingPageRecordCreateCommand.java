package com.particle.tracking.client.dto.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 页面埋点记录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@ApiModel
public class TrackingPageRecordCreateCommand extends TrackingPageRecordFrontCreateCommand {



    @ApiModelProperty(value = "用户id")
    private Long userId;


    @ApiModelProperty(value = "操作用户昵称")
    private String userNickname;


    @ApiModelProperty(value = "操作用户头像")
    private String userAvatar;



    @NotEmpty(message = "会话标识 不能为空")
    @ApiModelProperty(value = "会话标识",required = true)
    private String session;


    @NotEmpty(message = "会话标识的md5摘要值 不能为空")
    @ApiModelProperty(value = "会话标识的md5摘要值",required = true)
    private String sessionMd5;



    @NotEmpty(message = "设备id 不能为空")
    @ApiModelProperty(value = "设备id",required = true)
    private String deviceId;


    @NotEmpty(message = "设备名称 不能为空")
    @ApiModelProperty(value = "设备名称",required = true)
    private String deviceName;


    @NotEmpty(message = "操作系统及版本 不能为空")
    @ApiModelProperty(value = "操作系统及版本",required = true)
    private String operatingSystem;


    @ApiModelProperty(value = "页面停留时长")
    private Long duration;


    @NotEmpty(message = "追踪id 不能为空")
    @ApiModelProperty(value = "追踪id",required = true)
    private String traceId;

    @NotEmpty(message = "请求ip 不能为空")
    @ApiModelProperty(value = "请求ip",required = true)
    private String ip;
}
