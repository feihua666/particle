package com.particle.agi.domain.agent;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 智能体 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
public class AgiAgentId extends Id {

	public AgiAgentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 智能体 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAgentId of(Long id){
		return new AgiAgentId(id);
	}
}
