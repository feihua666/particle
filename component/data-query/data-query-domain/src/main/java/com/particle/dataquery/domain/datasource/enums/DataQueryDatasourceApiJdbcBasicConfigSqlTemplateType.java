package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * jdbc基础配置sql模板类型 字典项
 * 和大数据源 枚举 {@link com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-18 22:52:03
 */
public enum DataQueryDatasourceApiJdbcBasicConfigSqlTemplateType implements IDictItem {

	/**
	 * enjoy模板
	 */
	enjoy_template
	,
	/**
	 * mybatisScript模板
	 */
	mybatis_script_template,
	/**
	 * 无需处理，原生保留
	 */
	raw
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_jdbc_basic_config_sql_template_type.groupCode();
	}

	/**
	 * jdbc基础配置sql模板类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_jdbc_basic_config_sql_template_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

