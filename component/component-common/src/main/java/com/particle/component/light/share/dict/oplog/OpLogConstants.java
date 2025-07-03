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
		public static final String dataQuery = "dataQuery";
		public static final String oauth2authorization = "oauth2authorization";
		public static final String openPlatform = "openPlatform";
		public static final String report = "report";
		public static final String usageCount = "usageCount";

		// 客户
		public static final String crm = "crm";
		// 梦想之源
		public static final String dream = "dream";
		// 参数配置
		public static final String config = "config";
		public static final String dataconstraint = "dataconstraint";

		public static final String scheduler = "scheduler";
		public static final String opLog = "opLog";

		public static final String data = "data";
		public static final String feedback = "feedback";

		public static final String navigation = "navigation";
		public static final String agi = "agi";
		public static final String cms = "cms";

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
		public static final String other = "other";
	}
}
