package com.particle.agi.domain.chat;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 智能体对话消息工具 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
public class AgiAgentChatMessageToolId extends Id {

	public AgiAgentChatMessageToolId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 智能体对话消息工具 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAgentChatMessageToolId of(Long id){
		return new AgiAgentChatMessageToolId(id);
	}
}
