package com.particle.data.domain.dynamictable;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 动态数据表格字段 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
public class DynamicTableFieldId extends Id {

	public DynamicTableFieldId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 动态数据表格字段 领域模型id
	 * @param id
	 * @return
	 */
	public static DynamicTableFieldId of(Long id){
		return new DynamicTableFieldId(id);
	}
}
