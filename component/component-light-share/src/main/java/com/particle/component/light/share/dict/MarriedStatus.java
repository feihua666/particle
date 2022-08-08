package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 婚姻状况字典项
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 17:55
 */
public enum MarriedStatus implements IDictItem {
	married_status_dissociaton, // 离异
	married_status_dissociaton_kid, // 女
	married_status_single, // 单身
	married_status_married, // 已婚
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.married_status_parent.groupCode();
	}

	/**
	 * 婚姻状况组编码
	 */
	public enum Group implements IDictGroup {
		married_status_parent
		;
		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
