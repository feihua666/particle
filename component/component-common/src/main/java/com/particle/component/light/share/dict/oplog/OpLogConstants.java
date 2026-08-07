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
     * 注意同步修改，因为这些常量是编译时使用，在注解中使用，不能直接使用枚举
	 */
	public static class Module{
		/** 用户模块 */
		public static final String user = "user";
		/** 区域模块 */
		public static final String area = "area";
		/** 部门模块 */
		public static final String dept = "dept";
		/** 字典模块 */
		public static final String dict = "dict";
		/** 功能权限模块 */
		public static final String func = "func";
		/** 低代码模块 */
		public static final String lowCode = "lowCode";
		/** 角色模块 */
		public static final String role = "role";
		/** 租户模块 */
		public static final String tenant = "tenant";
		/** 跟踪模块 */
		public static final String tracking = "tracking";
		/** 消息模块 */
		public static final String message = "message";
		/** 数据查询模块 */
		public static final String dataQuery = "dataQuery";
		/** oauth2授权模块 */
		public static final String oauth2authorization = "oauth2authorization";
		/** 开放平台模块 */
		public static final String openPlatform = "openPlatform";
		/** 报表模块 */
		public static final String report = "report";
		/** 使用计数模块 */
		public static final String usageCount = "usageCount";

		/** 客户模块 */
		public static final String crm = "crm";
		/** 梦想之源模块 */
		public static final String dream = "dream";
		/** 配置模块 */
		public static final String config = "config";
		/** 数据约束模块 */
		public static final String dataconstraint = "dataconstraint";

		/** 调度模块 */
		public static final String scheduler = "scheduler";
		/** 操作日志模块 */
		public static final String opLog = "opLog";

		/** 数据模块 */
		public static final String data = "data";
		/** 反馈模块 */
		public static final String feedback = "feedback";

		/** 导航模块 */
		public static final String navigation = "navigation";
		/** 人工智能模块 */
		public static final String agi = "agi";

		/** 内容管理模块 */
		public static final String cms = "cms";

		/** 审批模块 */
		public static final String audit = "audit";

		/** 工作流模块 */
		public static final String workflow = "workflow";

		/** 爬虫模块 */
		public static final String crawler = "crawler";

		/** 未知模块 */
		public static final String unknown = "unknown";

	}

	/**
	 * 意义同 {@link com.particle.global.dataaudit.op.OpLogType}
     * 注意同步修改，因为这些常量是编译时使用，在注解中使用，不能直接使用枚举
	 */
	public static class Type{
		/** 添加 */
		public static final String create = "create";
		/** 更新 */
		public static final String update = "update";
		/** 删除 */
		public static final String delete = "delete";
		/** 查询 */
		public static final String query = "query";
		/** 分配 */
		public static final String relAsign = "relAsign";
		/** 审核 */
		public static final String audit = "audit";
		/** 未知 */
		public static final String unknown = "unknown";
		/** 其他 */
		public static final String other = "other";
	}
}
