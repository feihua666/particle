package com.particle.user.domain.login;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户登录设备 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@Entity
public class UserLoginDevice extends AggreateRoot {

	private UserLoginDeviceId id;
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


	/**
	 * 创建用户登录设备领域模型对象
	 * @return 用户登录设备领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static UserLoginDevice create(){
		return DomainFactory.create(UserLoginDevice.class);
	}
}
