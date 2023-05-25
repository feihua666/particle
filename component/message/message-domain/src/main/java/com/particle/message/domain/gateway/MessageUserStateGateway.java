package com.particle.message.domain.gateway;

import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 用户消息读取状态 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
public interface MessageUserStateGateway extends IBaseGateway<MessageUserStateId,MessageUserState> {
}
