package com.particle.data.domain.dynamictable;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据表格 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
public class DynamicTableId extends Id {

	public DynamicTableId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据表格 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicTableId of(Long id){
		return new DynamicTableId(id);
	}
}
