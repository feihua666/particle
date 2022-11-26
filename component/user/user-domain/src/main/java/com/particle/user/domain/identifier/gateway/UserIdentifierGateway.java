package com.particle.user.domain.identifier.gateway;

import com.particle.user.domain.identifier.UserIdentifier;
import com.particle.user.domain.identifier.UserIdentifierId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 用户登录标识 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface UserIdentifierGateway extends IBaseGateway<UserIdentifierId,UserIdentifier> {
}
