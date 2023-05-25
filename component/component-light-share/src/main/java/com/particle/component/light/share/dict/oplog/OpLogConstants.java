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
		public static final String area = "area";
		public static final String dept = "dept";
		public static final String dict = "dict";
		public static final String func = "func";
		public static final String lowCode = "lowCode";
		public static final String role = "role";
		public static final String tenant = "tenant";
		public static final String tracking = "tracking";
		public static final String message = "message";

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
		public static final String relAsign = "relAsign";
		public static final String audit = "audit";
		public static final String unknown = "unknown";
	}
}
