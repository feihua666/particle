package com.particle.dataquery.domain.dataapi.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据查询数据接口适配类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:32:30
 */
public enum DataQueryDataApiAdaptType implements IDictItem {

	/**
	 * 一对一直连
	 */
	single_direct
	,
	/**
	 * 多接口聚合
	 */
	multiple_aggregation
	,
	/**
	 * 自定义脚本
	 */
	custom_script
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_data_api_adapt_type.groupCode();
	}

	/**
	 * 数据查询数据接口适配类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_data_api_adapt_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

