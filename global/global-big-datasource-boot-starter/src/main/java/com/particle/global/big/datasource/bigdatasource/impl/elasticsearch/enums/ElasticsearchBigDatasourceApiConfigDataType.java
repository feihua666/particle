package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums;

import org.springframework.data.domain.PageRequest;

/**
 * <p>
 * elasticsearch 大数据源接口返回类型
 * </p>
 *
 * @author yangwei
 * @since 2023-11-21 13:46:24
 */
public enum ElasticsearchBigDatasourceApiConfigDataType {

	/**
	 * 单条数据
	 * 如果返回多条时会抛出异常
	 */
	single,
	/**
	 * 多条数据
	 */
	multiple,
	/**
	 * 分页数据
	 * 分页数据使用的是spring data commons的 {@link PageRequest} 作为查询条件，使用
	 */
	page,

}
