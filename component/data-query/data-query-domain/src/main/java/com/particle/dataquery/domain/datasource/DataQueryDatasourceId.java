package com.particle.dataquery.domain.datasource;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 数据查询数据源 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
public class DataQueryDatasourceId extends Id {

	public DataQueryDatasourceId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 数据查询数据源 领域模型id
	 * @param id
	 * @return
	 */
	public static DataQueryDatasourceId of(Long id){
		return new DataQueryDatasourceId(id);
	}
}
