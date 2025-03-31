package com.particle.agi.domain.chat;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 智能体对话消息媒体 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
public class AgiAgentChatMessageMediaId extends Id {

	public AgiAgentChatMessageMediaId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 智能体对话消息媒体 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAgentChatMessageMediaId of(Long id){
		return new AgiAgentChatMessageMediaId(id);
	}
}
