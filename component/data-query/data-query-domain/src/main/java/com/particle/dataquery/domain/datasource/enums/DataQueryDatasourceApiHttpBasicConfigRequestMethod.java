package com.particle.dataquery.domain.datasource.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http基础配置请求方法 字典项
 * 和大数据源枚举 {@link com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-27 13:41:24
 */
public enum DataQueryDatasourceApiHttpBasicConfigRequestMethod implements IDictItem {

	/**
	 * post请求
	 */
	post,
	/**
	 * delete请求
	 */
	delete,
	/**
	 * put请求
	 */
	put,
	/**
	 * patch请求
	 */
	patch,
	/**
	 * get请求
	 */
	get
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_http_basic_config_request_method.groupCode();
	}

	/**
	 * http基础配置请求方法 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_http_basic_config_request_method;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

