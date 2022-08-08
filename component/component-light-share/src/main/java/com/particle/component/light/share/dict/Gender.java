package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 性别
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 17:53
 */
public enum Gender implements IDictItem {
	m, // 男
	f, // 女
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.gender.groupCode();
	}

	/**
	 * 性别字典组编码
	 */
	public static enum Group implements IDictGroup {
		gender
		;
		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
