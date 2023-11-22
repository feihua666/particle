package com.particle.dataquery.domain.datasource.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * neo4j基础配置数据类型 字典项
 * 和 大数据源 {@link com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigDataType} 相匹配
 * </p>
 *
 * @author yw
 * @since 2023-11-22 10:16:35
 */
public enum DataQueryDatasourceApiNeo4jBasicConfigDataType implements IDictItem {

	/**
	 * 单条数据
	 */
	single
	,
	/**
	 * 多条数据
	 */
	multiple
	,
	/**
	 * 分页
	 */
	page
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_neo4j_basic_config_data_type.groupCode();
	}

	/**
	 * neo4j基础配置数据类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_neo4j_basic_config_data_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

