package com.particle.component.light.share.dict.oplog;

/**
 * <p>
 * 操作日志常量
 * </p>
 *
 * @author yangwei
 * @since 2023-05-07 15:52
 */
public class OpLogConstants {

	/**
	 * 意义同 {@link com.particle.global.dataaudit.op.OpLogModule}
	 */
	public static class Module{
		public static final String user = "user";
		public static final String sys = "sys";
		public static final String unknown = "unknown";

	}

	/**
	 * 意义同 {@link com.particle.global.dataaudit.op.OpLogType}
	 */
	public static class Type{
		public static final String create = "create";
		public static final String update = "update";
		public static final String delete = "delete";
		public static final String query = "query";
		public static final String audit = "audit";
		public static final String unknown = "unknown";
	}
}
