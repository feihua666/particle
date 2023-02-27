package com.particle.lowcode.domain.generator.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 低代码模型项设计数据范围
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 15:17
 */
public enum LowcodeModelItemDesignJsonScope implements IDictItem {
	/**
	 * 添加
	 */
	CREATE,
	/**
	 * 列表查询
	 */
	QUERY_LIST,
	/**
	 * 分布查询
	 */
	QUERY_PAGE,
	/**
	 * 更新
	 */
	UPDATE,
	/**
	 * 数据持久层
	 */
	DO,
	/**
	 * 领域模型
	 */
	DOMAIN,
	/**
	 * 响应对象
	 */
	VO;


	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.model_item_design_json_scope.groupCode();
	}

	/**
	 * 低代码模型项设计数据范围字典组
	 */
	public enum Group implements IDictGroup {
		/**
		 * 字典组编码
		 */
		model_item_design_json_scope;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
