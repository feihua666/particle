package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 分页信息适配适配类型 字典项，应该与 {@link com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-19 18:46:20
 */
public enum DataQueryDatasourceApiPageableAdapterType implements IDictItem {

	/**
	 * enjoy模板
	 */
	enjoy_template,
	/**
	 * groovy脚本
	 */
	groovy_template,
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_pageable_adapter_type.groupCode();
	}

	/**
	 * 分页信息适配适配类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_pageable_adapter_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

