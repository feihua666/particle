package com.particle.dataquery.domain.dataapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据查询数据接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
public class DataQueryDataApiId extends Id {

	public DataQueryDataApiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据查询数据接口 领域模型id
	 * @param id
	 * @return
	 */
	public static DataQueryDataApiId of(Long id){
		return new DataQueryDataApiId(id);
	}
}
