package com.particle.tracking.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 页面埋点记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@OrderBy(value = "createAt",asc = false)
@Data
@ApiModel
public class TrackingPageRecordQueryListCommand extends AbstractBaseQueryCommand {

    @ApiModelProperty(value = "用户id")
    private Long userId;


    @Like
    @ApiModelProperty(value = "操作用户昵称,左前缀匹配")
    private String userNickname;



    @ApiModelProperty(value = "是否用户触发")
    private Boolean isUserTrigger;


    @ApiModelProperty(value = "会话标识")
    private String session;


    @ApiModelProperty(value = "会话标识的md5摘要值")
    private String sessionMd5;


    @ApiModelProperty(value = "设备串号")
    private String imei;


    @ApiModelProperty(value = "设备id")
    private String deviceId;


    @Like
    @ApiModelProperty(value = "设备名称,左前缀匹配")
    private String deviceName;


    @Like
    @ApiModelProperty(value = "埋点页面编码,左前缀匹配")
    private String trackingPageCode;


    @ApiModelProperty(value = "埋点前驱页面编码")
    private String preTrackingPageCode;


    @ApiModelProperty(value = "操作系统及版本")
    private String operatingSystem;


    @ApiModelProperty(value = "客户端版本")
    private String appVersion;


    @ApiModelProperty(value = "行为类型")
    private String actionType;


    @Gt("actionAt")
    @ApiModelProperty(value = "行为开始时间")
    private LocalDateTime actionAtStart;

    @Lt("actionAt")
    @ApiModelProperty(value = "行为结束时间")
    private LocalDateTime actionAtEnd;

    @ApiModelProperty(value = "行为值")
    private String actionResult;


    @ApiModelProperty(value = "行为位置 x")
    private Integer actionOnX;


    @ApiModelProperty(value = "行为位置 y")
    private Integer actionOnY;


    @ApiModelProperty(value = "网络类型")
    private String netType;


    @ApiModelProperty(value = "位置经度")
    private String longitude;


    @ApiModelProperty(value = "位置纬度")
    private String latitude;


    @ApiModelProperty(value = "屏幕高度")
    private Integer screenHeight;


    @ApiModelProperty(value = "屏幕宽度")
    private Integer screenWidth;


    @ApiModelProperty(value = "进入页面时间")
    private LocalDateTime entryAt;


    @ApiModelProperty(value = "离开页面时间")
    private LocalDateTime leaveAt;


    @ApiModelProperty(value = "页面停留时长")
    private Long duration;


    @ApiModelProperty(value = "额外数据")
    private String extInfoJson;


    @ApiModelProperty(value = "追踪id")
    private String traceId;


    @ApiModelProperty("前端追踪id")
    private String frontTraceId;

    @ApiModelProperty(value = "描述")
    private String remark;

}
