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
 * 用户登录设备表
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@TableName("component_user_login_device")
public class UserLoginDeviceDO extends BaseDO {
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 首次登录时间
    */
    private LocalDateTime loginFirstAt;
    /**
    * 最后一次登录时间
    */
    private LocalDateTime loginLastAt;
    /**
    * 设备id，可以用来唯一标识一个设备
    */
    private String deviceId;
    /**
    * 设备名称，如：xxx的Iphone
    */
    private String deviceName;
    /**
    * 验证通过时间
    */
    private LocalDateTime validateAt;
    /**
    * 操作系统及版本
    */
    private String operatingSystem;

}
