package com.particle.func.domain;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 功能菜单类型
 * </p>
 *
 * @author yangwei
 * @since 2022-07-19 18:40
 */
public enum FuncTypeEnum implements IDictItem {
	/**
	 * 菜单
	 */
	menu,
	/**
	 * 页面
	 */
	page,
	/**
	 * 按钮
	 */
	button,
	/**
	 * 字段
	 */
	field,
	/**
	 * 表单项
	 */
	form_item,
	/**
	 * 区域块
	 */
	div,
	/**
	 * 分组
	 */
	group,
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.func_type.groupCode();
	}

	/**
	 * 字典组
	 */
	public static enum Group implements IDictGroup {
		/**
		 * 业务种类字典组
		 */
		func_type;

		@Override
		public String groupCode() {
			return func_type.name();
		}
	}
}
