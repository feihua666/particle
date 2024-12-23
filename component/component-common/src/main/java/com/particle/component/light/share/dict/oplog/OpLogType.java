package com.particle.component.light.share.dict.oplog;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 操作日志类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-09 10:39:12
 */
public enum OpLogType implements IDictItem {

	/**
	 * 添加
	 */
	create
	,
	/**
	 * 更新
	 */
	update
	,
	/**
	 * 删除
	 */
	delete
	,
	/**
	 * 查询
	 */
	query
	,
	/**
	 * 分配
	 */
	relAsign
	,
	/**
	 * 审核
	 */
	audit
	,
	/**
	 * 未知
	 */
	unknown
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.op_log_type.groupCode();
	}

	/**
	 * 操作日志类型 字典组
	 */
	public enum Group implements IDictGroup {
		op_log_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

