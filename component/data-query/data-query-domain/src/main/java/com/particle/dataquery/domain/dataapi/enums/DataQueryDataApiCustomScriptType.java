package com.particle.dataquery.domain.dataapi.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据查询数据接口自定义脚本类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-23 21:58:53
 */
public enum DataQueryDataApiCustomScriptType implements IDictItem {
	/**
	 * groovy脚本
	 */
	groovy_script
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_dataapi_custom_script_type.groupCode();
	}

	/**
	 * 数据查询数据接口自定义脚本类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_dataapi_custom_script_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

