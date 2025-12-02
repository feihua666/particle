package com.particle.data.domain.dynamicdata;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据指标分类 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
public class DynamicDataIndicatorCategoryId extends Id {

	public DynamicDataIndicatorCategoryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据指标分类 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicDataIndicatorCategoryId of(Long id){
		return new DynamicDataIndicatorCategoryId(id);
	}
}
