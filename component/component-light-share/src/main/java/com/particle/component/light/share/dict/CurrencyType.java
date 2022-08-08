package com.particle.component.light.share.dict;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 币种字典项
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 17:57
 */
public enum CurrencyType implements IDictItem {
	CNY; // 人民币

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.currency_type.groupCode();
	}

	/**
	 * 币种字典组
	 */
	public enum Group implements IDictGroup {
		currency_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
