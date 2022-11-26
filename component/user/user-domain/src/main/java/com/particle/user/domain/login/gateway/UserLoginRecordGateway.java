package com.particle.user.domain.login.gateway;

import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 用户登录记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface UserLoginRecordGateway extends IBaseGateway<UserLoginRecordId,UserLoginRecord> {
}
