package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 匹配方式字典
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 17:19
 */
public enum MatchType implements IDictItem {
	match_eq, // 等于
	match_like, // 模糊匹配
	match_like_left, // 左模糊匹配
	match_like_right, // 右模糊匹配
	match_regex, // 正则匹配
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.match_type.groupCode();
	}

	/**
	 * 字典组
	 */
	public static enum Group implements IDictGroup {
		match_type //匹配方式
		;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
