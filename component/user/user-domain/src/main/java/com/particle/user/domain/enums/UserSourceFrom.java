package com.particle.user.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 用户来源 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-15 13:23:44
 */
public enum UserSourceFrom implements IDictItem {

	/**
	 * 微信公众号
	 */
	user_source_from_wx_mp
	,
	/**
	 * 微信小程序
	 */
	user_source_from_wx_mini
	,
	/**
	 * 今日头条
	 */
	user_source_from_jinritoutiao
	,
	/**
	 * 新浪微博
	 */
	user_source_from_sina_weibo
	,
	/**
	 * 后台添加
	 */
	backend_add
	,
	/**
	 * 系统初始化
	 */
	system_init
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.user_source_from.groupCode();
	}

	/**
	 * 用户来源 字典组
	 */
	public enum Group implements IDictGroup {
		user_source_from;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

