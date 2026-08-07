package com.particle.agi.domain.model;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * AI模型 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
public class AgiAiModelId extends Id {

	public AgiAiModelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 AI模型 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiAiModelId of(Long id){
		return new AgiAiModelId(id);
	}
}
