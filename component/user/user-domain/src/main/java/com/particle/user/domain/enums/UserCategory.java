package com.particle.user.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 用户分类 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-15 13:35:06
 */
public enum UserCategory implements IDictItem {

	/**
	 * 其它分类
	 */
	other
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.user_category.groupCode();
	}

	/**
	 * 用户分类 字典组
	 */
	public enum Group implements IDictGroup {
		user_category;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

