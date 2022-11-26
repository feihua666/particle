package com.particle.user.domain.login.gateway;

import com.particle.user.domain.login.UserLoginDevice;
import com.particle.user.domain.login.UserLoginDeviceId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 用户登录设备 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface UserLoginDeviceGateway extends IBaseGateway<UserLoginDeviceId,UserLoginDevice> {
}
