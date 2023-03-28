package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http认证脚本类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-29 00:48:29
 */
public enum DataQueryDatasourceHttpAuthScriptType implements IDictItem {

	/**
	 * groovy脚本
	 */
	groovy_script
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
		return Group.dataquery_datasource_http_auth_script_type.groupCode();
	}

	/**
	 * http认证脚本类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_http_auth_script_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

