package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 用户分组具体项
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 17:58
 */
public enum UserGroupFlag implements IDictItem {
	backend, // 后台用户
	fronted, // 前台用户
	h5, // 用户
	wx_mp, // 微信公众号用户
	wx_mini; // 微信小程序用户

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.user_group_flag.groupCode();
	}

	/**
	 * 用户分组标识，这里写为公共的
	 */
	public static enum Group implements IDictGroup {
		user_group_flag;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
