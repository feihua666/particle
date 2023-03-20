package com.particle.dataquery.domain.datasource.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据源接口分类 字典项
 * </p>
 *
 * @author yw
 * @since 2023-03-15 14:58:01
 */
public enum DataQueryDatasourceApiCategory implements IDictItem {

	/**
	 * 无分类
	 */
	category_none
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_category.groupCode();
	}

	/**
	 * 数据源接口分类 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_category;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
