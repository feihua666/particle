package com.particle.component.light.share.dict.oplog;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 操作日志模块 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-09 10:38:10
 */
public enum OpLogModule implements IDictItem {

	/**
	 * 用户模块
	 */
	user
	,
	/**
	 * 区域模块
	 */
	area
	,
	/**
	 * 部门模块
	 */
	dept
	,
	/**
	 * 字典模块
	 */
	dict
	,
	/**
	 * 功能权限模块
	 */
	func
	,
	/**
	 * 低代码模块
	 */
	lowCode
	,
	/**
	 * 角色模块
	 */
	role
	,
	/**
	 * 租户模块
	 */
	tenant
	,
	/**
	 * 未知模块
	 */
	unknown
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.op_log_module.groupCode();
	}

	/**
	 * 操作日志模块 字典组
	 */
	public enum Group implements IDictGroup {
		op_log_module;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

