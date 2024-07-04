package com.particle.dataconstraint.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据范围自定义数据关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
public class DataScopeCustomDataRelId extends Id {

	public DataScopeCustomDataRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据范围自定义数据关系 领域模型id
	 * @param id
	 * @return
	 */
	public static DataScopeCustomDataRelId of(Long id){
		return new DataScopeCustomDataRelId(id);
	}
}
