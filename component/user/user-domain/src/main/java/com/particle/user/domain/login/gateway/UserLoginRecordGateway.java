package com.particle.user.domain.login.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.user.domain.login.UserLoginRecord;
import com.particle.user.domain.login.UserLoginRecordId;

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
