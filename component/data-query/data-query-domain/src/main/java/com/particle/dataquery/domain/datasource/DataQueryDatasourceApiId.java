package com.particle.dataquery.domain.datasource;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据查询数据源接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
public class DataQueryDatasourceApiId extends Id {

	public DataQueryDatasourceApiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据查询数据源接口 领域模型id
	 * @param id
	 * @return
	 */
	public static DataQueryDatasourceApiId of(Long id){
		return new DataQueryDatasourceApiId(id);
	}
}
