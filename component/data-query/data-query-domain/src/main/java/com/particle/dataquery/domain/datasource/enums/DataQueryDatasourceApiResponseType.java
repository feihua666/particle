package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据源接口类型 字典项
 * 和大数据源响应类型保持一致 {@link com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseAssertType}
 * </p>
 *
 * @author yw
 * @since 2023-03-15 14:59:09
 */
public enum DataQueryDatasourceApiResponseType implements IDictItem {

	/**
	 * 单条响应数据接口
	 */
	single
	,
	/**
	 * 多条响应数据接口
	 */
	multiple
	,
	/**
	 * 分页响应数据接口
	 */
	page
	,
	/**
	 * 原生响应数据接口
	 */
	raw
	,
	/**
	 * 代理响应数据接口
	 */
	proxy
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_type.groupCode();
	}

	/**
	 * 数据源接口类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

