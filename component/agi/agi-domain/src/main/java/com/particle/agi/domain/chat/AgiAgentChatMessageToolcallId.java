package com.particle.agi.domain.chat;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 智能体对话消息工具调用 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
public class AgiAgentChatMessageToolcallId extends Id {

	public AgiAgentChatMessageToolcallId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 智能体对话消息工具调用 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAgentChatMessageToolcallId of(Long id){
		return new AgiAgentChatMessageToolcallId(id);
	}
}
