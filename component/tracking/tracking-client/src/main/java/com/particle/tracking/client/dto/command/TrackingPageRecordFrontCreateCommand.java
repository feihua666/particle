package com.particle.tracking.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TrackingPageRecordFrontCreateCommand extends AbstractBaseCommand {

    @NotNull(message = "是否用户触发 不能为空")
    @ApiModelProperty(value = "是否用户触发",required = true)
    private Boolean isUserTrigger;

    @ApiModelProperty(value = "设备串号")
    private String imei;

    @NotEmpty(message = "埋点页面编码 不能为空")
    @ApiModelProperty(value = "埋点页面编码",required = true)
    private String trackingPageCode;


    @ApiModelProperty(value = "埋点前驱页面编码")
    private String preTrackingPageCode;



    @NotEmpty(message = "客户端版本 不能为空")
    @ApiModelProperty(value = "客户端版本",required = true)
    private String appVersion;


    @NotEmpty(message = "行为类型 不能为空")
    @ApiModelProperty(value = "行为类型",required = true)
    private String actionType;


    /**
     * 为空后台默认添加当前时间
     */
    @ApiModelProperty(value = "行为产生时间")
    private LocalDateTime actionAt;


    @ApiModelProperty(value = "行为值")
    private String actionResult;


    @ApiModelProperty(value = "行为位置 x")
    private Integer actionOnX;


    @ApiModelProperty(value = "行为位置 y")
    private Integer actionOnY;


    @NotEmpty(message = "网络类型 不能为空")
    @ApiModelProperty(value = "网络类型，如：2G、4G、wifi",required = true)
    private String netType;


    @ApiModelProperty(value = "位置经度")
    private String longitude;


    @ApiModelProperty(value = "位置纬度")
    private String latitude;


    @NotNull(message = "屏幕高度 不能为空")
    @ApiModelProperty(value = "屏幕高度，获取不到请填0",required = true)
    private Integer screenHeight;


    @NotNull(message = "屏幕宽度 不能为空")
    @ApiModelProperty(value = "屏幕宽度，获取不到请填0",required = true)
    private Integer screenWidth;


    @ApiModelProperty(value = "进入页面时间")
    private LocalDateTime entryAt;


    @ApiModelProperty(value = "离开页面时间")
    private LocalDateTime leaveAt;


    @ApiModelProperty(value = "额外数据")
    private String extInfoJson;


    @ApiModelProperty(value = "描述")
    private String remark;









}
