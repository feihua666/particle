package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http基础配置请求地址渲染类型 字典项
 * 和大数据源 枚举 {@link com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestUrlRenderType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-27 13:43:11
 */
public enum DataQueryDatasourceApiHttpBasicConfigRequestUrlRenderType implements IDictItem {

	/**
	 * enjoy模板
	 */
	enjoy_template
	,
	/**
	 * groovy脚本
	 */
	groovy_script
	,
	/**
	 * 原生文本
	 */
	raw
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_http_basic_config_request_url_render_type.groupCode();
	}

	/**
	 * http基础配置请求地址渲染类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_http_basic_config_request_url_render_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

