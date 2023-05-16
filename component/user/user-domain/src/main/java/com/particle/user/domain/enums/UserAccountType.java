package com.particle.user.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 用户帐号类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-15 13:40:30
 */
public enum UserAccountType implements IDictItem {

	/**
	 * 帐号
	 */
	front_account
	,
	/**
	 * 手机号登录
	 */
	front_mobile
	,
	/**
	 * 邮箱登录
	 */
	front_email
	,
	/**
	 * 微信登录
	 */
	front_wx
	,
	/**
	 * qq登录
	 */
	front_qq
	,
	/**
	 * 抖音登录
	 */
	front_douyin
	,
	/**
	 * 新浪微博登录
	 */
	front_sina_weibo
	,
	/**
	 * 微信公众号登录
	 */
	wx_mp
	,
	/**
	 * 微信小程序登录
	 */
	wx_ma
	,
	/**
	 * 微信小程序手机号登录
	 */
	wx_ma_mobile
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.user_account_type.groupCode();
	}

	/**
	 * 用户帐号类型 字典组
	 */
	public enum Group implements IDictGroup {
		user_account_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

