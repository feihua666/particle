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
public enum SegmentGenType implements IDictItem {
	/**
	 * 后端模型服务
	 * 根据模型和代码片段生成 CRUD 代码
	 */
	backend_model_service,
	/**
	 * 前端模型服务
	 */
	frontend_model_service,
	/**
	 * 后端+前端模型服务
	 */
	backend_and_frontend_model_service,
	/**
	 * 后端模块
	 */
	backend_module,
	/**
	 * 前端模块
	 */
	frontend_module,
	/**
	 * 后端+前端模块
	 */
	backend_and_frontend_module;


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
