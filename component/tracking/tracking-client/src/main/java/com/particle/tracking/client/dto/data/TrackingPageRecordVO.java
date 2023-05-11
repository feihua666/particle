package com.particle.tracking.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 页面埋点记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@ApiModel
public class TrackingPageRecordVO extends AbstractBaseIdVO {

    @ApiModelProperty("用户id")
    private Long userId;
    
    @ApiModelProperty("操作用户昵称")
    private String userNickname;
    
    @ApiModelProperty("操作用户头像")
    private String userAvatar;
    
    @ApiModelProperty("是否用户触发")
    private Boolean isUserTrigger;
    
    @ApiModelProperty("会话标识")
    private String session;
    
    @ApiModelProperty("会话标识的md5摘要值")
    private String sessionMd5;
    
    @ApiModelProperty("设备串号")
    private String imei;
    
    @ApiModelProperty("设备id")
    private String deviceId;
    
    @ApiModelProperty("设备名称")
    private String deviceName;
    
    @ApiModelProperty("埋点页面编码")
    private String trackingPageCode;
    
    @ApiModelProperty("埋点前驱页面编码")
    private String preTrackingPageCode;
    
    @ApiModelProperty("操作系统及版本")
    private String operatingSystem;
    
    @ApiModelProperty("客户端版本")
    private String appVersion;
    
    @ApiModelProperty("行为类型")
    private String actionType;
    
    @ApiModelProperty("行为产生时间")
    private LocalDateTime actionAt;
        
    @ApiModelProperty("行为值")
    private String actionResult;
    
    @ApiModelProperty("行为位置 x")
    private Integer actionOnX;
    
    @ApiModelProperty("行为位置 y")
    private Integer actionOnY;
    
    @ApiModelProperty("网络类型")
    private String netType;
    
    @ApiModelProperty("位置经度")
    private String longitude;
    
    @ApiModelProperty("位置纬度")
    private String latitude;
    
    @ApiModelProperty("屏幕高度")
    private Integer screenHeight;
    
    @ApiModelProperty("屏幕宽度")
    private Integer screenWidth;
    
    @ApiModelProperty("进入页面时间")
    private LocalDateTime entryAt;
        
    @ApiModelProperty("离开页面时间")
    private LocalDateTime leaveAt;
        
    @ApiModelProperty("页面停留时长")
    private Long duration;
    
    @ApiModelProperty("额外数据")
    private String extInfoJson;
    
    @ApiModelProperty("追踪id")
    private String traceId;

    @ApiModelProperty("前端追踪id")
    private String frontTraceId;

    @ApiModelProperty("描述")
    private String remark;
    


}
