package com.particle.tracking.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Gt;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.Lt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 页面埋点记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@Schema
public class TrackingPageRecordPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "用户id")
    private Long userId;


    @Like
    @Schema(description = "操作用户昵称,左前缀匹配")
    private String userNickname;



    @Schema(description = "是否用户触发")
    private Boolean isUserTrigger;


    @Schema(description = "会话标识")
    private String session;


    @Schema(description = "会话标识的md5摘要值")
    private String sessionMd5;


    @Schema(description = "设备串号")
    private String imei;


    @Schema(description = "设备id")
    private String deviceId;


    @Like
    @Schema(description = "设备名称,左前缀匹配")
    private String deviceName;


    @Like
    @Schema(description = "埋点页面编码,左前缀匹配")
    private String trackingPageCode;


    @Schema(description = "埋点前驱页面编码")
    private String preTrackingPageCode;


    @Schema(description = "操作系统及版本")
    private String operatingSystem;


    @Schema(description = "客户端版本")
    private String appVersion;


    @Schema(description = "行为类型")
    private String actionType;


    @Gt("actionAt")
    @Schema(description = "行为开始时间")
    private LocalDateTime actionAtStart;

    @Lt("actionAt")
    @Schema(description = "行为结束时间")
    private LocalDateTime actionAtEnd;


    @Schema(description = "行为值")
    private String actionResult;


    @Schema(description = "行为位置 x")
    private Integer actionOnX;


    @Schema(description = "行为位置 y")
    private Integer actionOnY;


    @Schema(description = "网络类型")
    private String netType;


    @Schema(description = "位置经度")
    private String longitude;


    @Schema(description = "位置纬度")
    private String latitude;


    @Schema(description = "屏幕高度")
    private Integer screenHeight;


    @Schema(description = "屏幕宽度")
    private Integer screenWidth;


    @Schema(description = "进入页面时间")
    private LocalDateTime entryAt;


    @Schema(description = "离开页面时间")
    private LocalDateTime leaveAt;


    @Schema(description = "页面停留时长")
    private Long duration;


    @Schema(description = "额外数据")
    private String extInfoJson;


    @Schema(description = "追踪id")
    private String traceId;


    @Schema(description = "前端追踪id")
    private String frontTraceId;

    @Schema(description = "描述")
    private String remark;









}
