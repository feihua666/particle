package com.particle.dataquery.domain.datasource.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * jdbc基础配置数据类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-19 15:37:05
 */
public enum DataQueryDatasourceApiJdbcBasicConfigDataType implements IDictItem {

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
		return Group.dataquery_datasource_api_jdbc_basic_config_data_type.groupCode();
	}

	/**
	 * jdbc基础配置数据类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_jdbc_basic_config_data_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

