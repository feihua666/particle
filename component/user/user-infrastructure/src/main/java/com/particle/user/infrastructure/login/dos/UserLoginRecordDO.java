package com.particle.user.infrastructure.login.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 用户登录记录表
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@TableName("component_user_login_record")
public class UserLoginRecordDO extends BaseDO {
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 登录时间
    */
    private LocalDateTime loginAt;
    /**
    * 登录ip
    */
    private String loginIp;
    /**
    * 设备id，可以用来唯一标识一个设备
    */
    private String deviceId;
    /**
    * 设备名称，如：xxx的Iphone
    */
    private String deviceName;
    /**
    * 登录标识id
    */
    private Long userIdentifierId;
    /**
    * 操作系统及版本
    */
    private String operatingSystem;
    /**
    * 会话标识，登录成功后的会话
    */
    private String session;
    /**
    * 会话标识的md5摘要值，考虑到会话标识可能会很长
    */
    private String sessionMd5;
    /**
    * 登录是否成功
    */
    private Boolean isSuccess;
    /**
    * 失败原因
    */
    private String failedReason;
    /**
    * 追踪id
    */
    private String traceId;
    /**
    * api数量，该次登录请求的api数，登录算第1次
    */
    private Long apiCount;
    /**
    * 最后活跃时间
    */
    private LocalDateTime lastActiveAt;
    /**
    * 退出登录时间
    */
    private LocalDateTime logoutAt;

}
