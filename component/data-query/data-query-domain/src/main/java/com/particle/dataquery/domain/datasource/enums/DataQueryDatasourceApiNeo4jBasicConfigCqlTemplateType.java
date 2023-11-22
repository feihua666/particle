package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * neo4j基础配置cql模板类型 字典项
 * 和大数据源 枚举 {@link com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigCqlTemplateType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-11-22 10:19:34
 */
public enum DataQueryDatasourceApiNeo4jBasicConfigCqlTemplateType implements IDictItem {
	
	/**
	 * enjoy模板
	 */
	enjoy_template,
	/**
	 * 无需处理，原生保留
	 */
	raw,
	/**
	 * groovy脚本支持
	 */
	groovy_script_template;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_neo4j_basic_config_cql_template_type.groupCode();
	}

	/**
	 * neo4j基础配置cql模板类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_neo4j_basic_config_cql_template_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

