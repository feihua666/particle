package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据指标 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
public class DynamicDataIndicatorId extends Id {

	public DynamicDataIndicatorId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据指标 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicDataIndicatorId of(Long id){
		return new DynamicDataIndicatorId(id);
	}
}
