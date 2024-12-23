package com.particle.component.light.share.dict.oplog;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 操作日志审计数据值改变类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-05-09 10:39:40
 */
public enum OpLogAuditDataChangeType implements IDictItem {

	/**
	 * 添加
	 */
	PROPERTY_ADDED
	,
	/**
	 * 删除
	 */
	PROPERTY_REMOVED
	,
	/**
	 * 修改
	 */
	PROPERTY_VALUE_CHANGED
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.op_log_audit_data_change_type.groupCode();
	}

	/**
	 * 操作日志审计数据值改变类型 字典组
	 */
	public enum Group implements IDictGroup {
		op_log_audit_data_change_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

