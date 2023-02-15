package com.particle.lowcode.domain.generator.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 模型表类型
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 15:17
 */
public enum TableType implements IDictItem {
	/**
	 * 树表
	 * 包括parentId{x}的表
	 */
	TREE,
	/**
	 * 关系表
	 * 如：用户角色关系表
	 */
	REL,
	/**
	 * 标准表
	 * 不是树表也不是关系表
	 */
	NORMAL;


	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.lowcode_model_table_type.groupCode();
	}

	/**
	 * 模型类型字典组
	 */
	public enum Group implements IDictGroup {
		/**
		 * 字典组编码
		 */
		lowcode_model_table_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
