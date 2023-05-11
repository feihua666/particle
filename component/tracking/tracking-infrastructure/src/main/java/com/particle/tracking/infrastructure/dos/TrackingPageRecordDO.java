package com.particle.tracking.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 页面埋点记录表
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Data
@TableName("component_tracking_page_record")
public class TrackingPageRecordDO extends BaseDO {

    /**
    * 用户id，已注册用户必填
    */
    private Long userId;

    /**
    * 操作用户昵称
    */
    private String userNickname;

    /**
    * 操作用户头像
    */
    private String userAvatar;

    /**
    * 是否用户触发，可以表名是否用户参与
    */
    private Boolean isUserTrigger;

    /**
    * 会话标识，登录成功后的会话
    */
    private String session;

    /**
    * 会话标识的md5摘要值，考虑到会话标识可能会很长
    */
    private String sessionMd5;

    /**
    * 设备串号，安卓:（imei），IOS:（idfa），未注册用户必填
    */
    private String imei;

    /**
    * 设备id，可以用来唯一标识一个设备
    */
    private String deviceId;

    /**
    * 设备名称，如：xxx的Iphone
    */
    private String deviceName;

    /**
    * 埋点页面编码
    */
    private String trackingPageCode;

    /**
    * 埋点前驱页面编码
    */
    private String preTrackingPageCode;

    /**
    * 操作系统及版本
    */
    private String operatingSystem;

    /**
    * 客户端版本
    */
    private String appVersion;

    /**
    * 行为类型，例如曝光、停留、点击、收藏、下载、购买、加购、评价等
    */
    private String actionType;

    /**
    * 行为产生时间
    */
    private LocalDateTime actionAt;
    
    /**
    * 行为值，例如点击次数，停留时长，购买件数、购买金额等
    */
    private String actionResult;

    /**
    * 行为位置 x
    */
    private Integer actionOnX;

    /**
    * 行为位置 y
    */
    private Integer actionOnY;

    /**
    * 网络类型，2G/3G/4G/WIFI
    */
    private String netType;
    /**
     * 请求ip
     */
    private String ip;

    /**
    * 位置经度
    */
    private String longitude;

    /**
    * 位置纬度
    */
    private String latitude;

    /**
    * 屏幕高度，一般单位为像素
    */
    private Integer screenHeight;

    /**
    * 屏幕宽度，一般单位为像素
    */
    private Integer screenWidth;

    /**
    * 进入页面时间
    */
    private LocalDateTime entryAt;
    
    /**
    * 离开页面时间
    */
    private LocalDateTime leaveAt;
    
    /**
    * 页面停留时长，单位ms
    */
    private Long duration;

    /**
    * 额外数据，用于描述该事件的额外数据，比如加载成功后的结果数据
    */
    private String extInfoJson;

    /**
    * 追踪id
    */
    private String traceId;

    /**
     * 前端追踪id，一般从二级页面开始设置后续操作都使用该id
     */
    private String frontTraceId;
    /**
    * 描述
    */
    private String remark;


}
