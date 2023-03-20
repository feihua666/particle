package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据源接口参数校验类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-19 22:27:28
 */
public enum DataQueryDatasourceApiParamValidateType implements IDictItem {

	/**
	 * javascript断言
	 */
	javascript_assert
	,
	/**
	 * enjoy模板
	 */
	enjoy_template
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_param_validate_type.groupCode();
	}

	/**
	 * 数据源接口参数校验类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_param_validate_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

