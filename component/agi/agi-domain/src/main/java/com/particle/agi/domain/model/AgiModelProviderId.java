package com.particle.agi.domain.model;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * AI模型提供商 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
public class AgiModelProviderId extends Id {

	public AgiModelProviderId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 AI模型提供商 领域模型id
	 * @param id
	 * @return
	 */
	public static AgiModelProviderId of(Long id){
		return new AgiModelProviderId(id);
	}
}
