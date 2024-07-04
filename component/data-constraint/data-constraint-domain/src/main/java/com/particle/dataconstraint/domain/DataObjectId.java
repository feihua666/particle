package com.particle.dataconstraint.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据对象 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
public class DataObjectId extends Id {

	public DataObjectId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据对象 领域模型id
	 * @param id
	 * @return
	 */
	public static DataObjectId of(Long id){
		return new DataObjectId(id);
	}
}
