package com.particle.user.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.user.domain.User;
import com.particle.user.domain.UserId;

/**
 * <p>
 * 用户 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface UserGateway extends IBaseGateway<UserId,User> {
}
