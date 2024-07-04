package com.particle.dataconstraint.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据范围 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
public class DataScopeId extends Id {

	public DataScopeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据范围 领域模型id
	 * @param id
	 * @return
	 */
	public static DataScopeId of(Long id){
		return new DataScopeId(id);
	}
}
