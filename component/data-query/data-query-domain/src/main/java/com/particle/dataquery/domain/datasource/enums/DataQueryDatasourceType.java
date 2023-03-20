package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据源类型 字典项
 * 和大数据源 {@link com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-17 23:08:39
 */
public enum DataQueryDatasourceType implements IDictItem {

	/**
	 * jdbc数据源
	 */
	datasource_jdbc
	,
	/**
	 * redis数据源
	 */
	datasource_redis
	,
	/**
	 * es数据源
	 */
	datasource_es
	,
	/**
	 * neo4j数据源
	 */
	datasource_neo4j
	,
	/**
	 * http数据源
	 */
	datasource_http
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_type.groupCode();
	}

	/**
	 * 数据源类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

