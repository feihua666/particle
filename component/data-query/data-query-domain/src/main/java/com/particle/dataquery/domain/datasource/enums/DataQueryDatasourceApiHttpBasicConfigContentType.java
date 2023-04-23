package com.particle.dataquery.domain.datasource.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http基础配置内容类型 字典项
 * 和大数据源枚举 {@link com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType} 保持一致
 * </p>
 *
 * @author yw
 * @since 2023-03-27 13:42:36
 */
public enum DataQueryDatasourceApiHttpBasicConfigContentType implements IDictItem {

	/**
	 * application/json
	 */
	application_json
	,
	/**
	 * multipart/form-data
	 */
	multipart_form_data
	,
	/**
	 * application/x-www-form-urlencoded
	 */
	application_x_www_form_urlencoded
	,
	/**
	 * text/plain
	 */
	text_plain
	,
	/**
	 * text/xml
	 */
	text_xml
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_http_basic_config_content_type.groupCode();
	}

	/**
	 * http基础配置内容类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_http_basic_config_content_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

