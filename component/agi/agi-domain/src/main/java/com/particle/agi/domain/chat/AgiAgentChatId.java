package com.particle.agi.domain.chat;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 智能体对话 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
public class AgiAgentChatId extends Id {

	public AgiAgentChatId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 智能体对话 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAgentChatId of(Long id){
		return new AgiAgentChatId(id);
	}
}
