package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 注册类型字典项
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 18:00
 */
public enum RegistType implements IDictItem {
	account_regist,     // 帐号注册
	mobile_regist,     // 手机号注册
	wx_mp_regist,     // 微信公众号注册
	wx_mini_regist,     // 微信小程序注册
	none,     // 无

	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.regist_type.groupCode();
	}

	/**
	 * 注册类型
	 */
	public static enum Group implements IDictGroup {
		regist_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
