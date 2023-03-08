package com.particle.dataquery.domain.provider;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据查询供应商 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
public class DataQueryProviderId extends Id {

	public DataQueryProviderId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据查询供应商 领域模型id
	 * @param id
	 * @return
	 */
	public static DataQueryProviderId of(Long id){
		return new DataQueryProviderId(id);
	}
}
