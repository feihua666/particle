package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 用户生效触发 字典项
 * </p>
 *
 * @author yw
 * @since 2023-07-21 15:36:32
 */
public enum UserEffectiveAtTrigger implements IDictItem {

	/**
	 * 成功登录
	 */
	login_success
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.user_effective_at_trigger.groupCode();
	}

	/**
	 * 用户生效触发 字典组
	 */
	public enum Group implements IDictGroup {
		user_effective_at_trigger;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

