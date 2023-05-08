package com.particle.global.dataaudit.op;

/**
 * <p>
 * 操作日志类型，仅为示例
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 14:57
 */
public enum OpLogType {

	/**
	 * 添加
	 */
	create,
	/**
	 * 修改
	 */
	update,
	/**
	 * 删除
	 */
	delete,
	/**
	 * 查询，一般查询适用于敏感信息
	 */
	query,

	/**
	 * 审批，修改的一种
	 */
	audit,
	/**
	 * 未知
	 */
	unknown,
}
