package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums;

/**
 * <p>
 * elasticsearch 大数据源接口 cql 模板类型
 * </p>
 *
 * @author yangwei
 * @since 2023-11-21 11:22:11
 */
public enum ElasticsearchBigDatasourceApiConfigDslTemplateType {

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
	groovy_script_template
}
