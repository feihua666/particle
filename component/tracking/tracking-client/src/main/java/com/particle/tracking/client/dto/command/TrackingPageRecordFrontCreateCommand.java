package com.particle.tracking.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
public class TrackingPageRecordFrontCreateCommand extends AbstractBaseCommand {

    @NotNull(message = "是否用户触发 不能为空")
    @Schema(description = "是否用户触发",required = true)
    private Boolean isUserTrigger;

    @Schema(description = "设备串号")
    private String imei;

    @NotEmpty(message = "埋点页面编码 不能为空")
    @Schema(description = "埋点页面编码",required = true)
    private String trackingPageCode;


    @Schema(description = "埋点前驱页面编码")
    private String preTrackingPageCode;



    @NotEmpty(message = "客户端版本 不能为空")
    @Schema(description = "客户端版本",required = true)
    private String appVersion;


    @NotEmpty(message = "行为类型 不能为空")
    @Schema(description = "行为类型",required = true)
    private String actionType;


    /**
     * 为空后台默认添加当前时间
     */
    @Schema(description = "行为产生时间")
    private LocalDateTime actionAt;


    @Schema(description = "行为值")
    private String actionResult;


    @Schema(description = "行为位置 x")
    private Integer actionOnX;


    @Schema(description = "行为位置 y")
    private Integer actionOnY;


    @NotEmpty(message = "网络类型 不能为空")
    @Schema(description = "网络类型，如：2G、4G、wifi",required = true)
    private String netType;


    @Schema(description = "位置经度")
    private String longitude;


    @Schema(description = "位置纬度")
    private String latitude;


    @NotNull(message = "屏幕高度 不能为空")
    @Schema(description = "屏幕高度，获取不到请填0",required = true)
    private Integer screenHeight;


    @NotNull(message = "屏幕宽度 不能为空")
    @Schema(description = "屏幕宽度，获取不到请填0",required = true)
    private Integer screenWidth;


    @Schema(description = "进入页面时间")
    private LocalDateTime entryAt;


    @Schema(description = "离开页面时间")
    private LocalDateTime leaveAt;


    @Schema(description = "额外数据")
    private String extInfoJson;


    @Schema(description = "前端追踪id")
    private String frontTraceId;

    @Schema(description = "描述")
    private String remark;

}
