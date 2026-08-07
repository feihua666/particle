package com.particle.component.light.share.trans;

/**
 * <p>
 * 翻译，根据表名翻译常量
 * 表名翻译默认实现为 {@link com.particle.global.light.share.trans.TransConstants#defaultTransType} 支持的类型，可通过 particle.trans.table.{tableName}={newTableName} 配置以替换默认使用的表
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 18:20
 */
public class TransTableNameConstants {

	/**
	 * 功能组表
	 */
	public static final String component_func_group = "component_func_group";
	/**
	 * 菜单功能表
	 */
	public static final String component_func = "component_func";
	/**
	 * 功能应用表
	 */
	public static final String component_func_application = "component_func_application";
	/**
	 * 字典表
	 */
	public static final String component_dict = "component_dict";
	/**
	 * 区域表
	 */
	public static final String component_area = "component_area";
	/**
	 * 用户表
	 */
	public static final String component_user = "component_user";

	/**
	 * 用户登录标识表
	 */
	public static final String component_user_identifier = "component_user_identifier";
	/**
	 * 角色表
	 */
	public static final String component_role = "component_role";
	/**
	 * 低代码模型表
	 */
	public static final String component_lowcode_model = "component_lowcode_model";
	/**
	 * 低代码片段模板表
	 */
	public static final String component_lowcode_segment_template = "component_lowcode_segment_template";
	/**
	 * 低代码生成表
	 */
	public static final String component_lowcode_segment_gen = "component_lowcode_segment_gen";
	/**
	 * 数据查询数据源表
	 */
	public static final String component_data_query_datasource = "component_data_query_datasource";
	/**
	 * 数据查询供应商表
	 */
	public static final String component_data_query_provider = "component_data_query_provider";
	/**
	 * 数据查询数据接口表
	 */
	public static final String component_data_query_data_api = "component_data_query_data_api";
	/**
	 * 数据查询数据源接口表
	 */
	public static final String component_data_query_datasource_api = "component_data_query_datasource_api";
	/**
	 * 部门表
	 */
	public static final String component_dept = "component_dept";
	/**
	 * 部门用户关系表
	 */
	public static final String component_dept_user_rel = "component_dept_user_rel";
	/**
	 * 部门树表
	 */
	public static final String component_dept_tree = "component_dept_tree";
	/**
	 * 部门树名称表
	 */
	public static final String component_dept_tree_name = "component_dept_tree_name";
	/**
	 * 租户表
	 */
	public static final String component_tenant = "component_tenant";
	/**
	 * 租户功能应用表
	 */
	public static final String component_tenant_func_application = "component_tenant_func_application";

	/**
	 * 操作日志表
	 */
	public static final String component_op_log = "component_op_log";

	/**
	 * 埋点页面表
	 */
	public static final String component_tracking_page = "component_tracking_page";

	/**
	 * 开放平台应用表
	 */
	public static final String component_openplatform_app = "component_openplatform_app";
	/**
	 * 开放平台开放接口表
	 */
	public static final String component_openplatform_openapi = "component_openplatform_openapi";
	/**
	 * 开放平台开放接口费用表
	 */
	public static final String component_openplatform_openapi_fee = "component_openplatform_openapi_fee";
	/**
	 * 开放平台开放接口限制规则表
	 */
	public static final String component_openplatform_openapi_limit_rule = "component_openplatform_openapi_limit_rule";
	/**
	 * 开放平台供应商表
	 */
	public static final String component_openplatform_provider = "component_openplatform_provider";
	/**
	 * 开放平台文档目录名称表
	 */
	public static final String component_openplatform_doc_dir_name = "component_openplatform_doc_dir_name";
	/**
	 * 开放平台文档目录表
	 */
	public static final String component_openplatform_doc_dir = "component_openplatform_doc_dir";
	/**
	 * 开放平台文档接口表
	 */
	public static final String component_openplatform_doc_api = "component_openplatform_doc_api";
	/**
	 * 开放平台文档接口文档表
	 */
	public static final String component_openplatform_doc_api_doc = "component_openplatform_doc_api_doc";
	/**
	 * 开放平台文档接口文档参数字段表
	 */
	public static final String component_openplatform_doc_api_doc_param_field = "component_openplatform_doc_api_doc_param_field";
	/**
	 * 开放平台文档接口文档模板表
	 */
	public static final String component_openplatform_doc_api_doc_template = "component_openplatform_doc_api_doc_template";
	/**
	 * 开放平台文档接口文档模板参数字段表
	 */
	public static final String component_openplatform_doc_api_doc_template_param_field = "component_openplatform_doc_api_doc_template_param_field";

	/**
	 * 报表片段模板表
	 */
	public static final String component_report_segment_template = "component_report_segment_template";
	/**
	 * 报表接口表
	 */
	public static final String component_report_report_api = "component_report_report_api";
	/**
	 * 使用次数定义表
	 */
	public static final String component_usage_count_define = "component_usage_count_define";
	/**
	 * 使用次数配置表
	 */
	public static final String component_usage_count_config = "component_usage_count_config";
	/**
	 * 使用次数记录表
	 */
	public static final String component_usage_count_record = "component_usage_count_record";


	/**
	 * 客户公司表
	 */
	public static final String component_crm_company = "component_crm_company";
	/**
	 * 客户公司部门表
	 */
	public static final String component_crm_dept = "component_crm_dept";
	/**
	 * 客户表
	 */
	public static final String component_crm_customer = "component_crm_customer";
	/**
	 * 客户关系定义表
	 */
	public static final String component_crm_customer_relation_define = "component_crm_customer_relation_define";
	/**
	 * 客户标签表
	 */
	public static final String component_crm_customer_tag = "component_crm_customer_tag";


	/**
	 * 双色球号码表
	 */
	public static final String component_dream_ssq_code = "component_dream_ssq_code";


	/**
	 * 数据对象表
	 */
	public static final String component_data_object = "component_data_object";

	/**
	 * 任务计划临时任务表
	 */
	public static final String component_scheduler_temp_task = "component_scheduler_temp_task";
	/**
	 * 导航分类表
	 */
	public static final String component_navigation_category = "component_navigation_category";
	/**
	 * 导航网站表
	 */
	public static final String component_navigation_site = "component_navigation_site";
	/**
	 * 导航网站标签表
	 */
	public static final String component_navigation_site_tag = "component_navigation_site_tag";


	/**
	 * 知识存储原始文档表
	 */
	public static final String component_agi_vector_store_raw_document = "component_agi_vector_store_raw_document";


	/**
	 * 站点表
	 */
	public static final String component_cms_site = "component_cms_site";
	/**
	 * 栏目表
	 */
	public static final String component_cms_channel = "component_cms_channel";
	/**
	 * 内容表
	 */
	public static final String component_cms_content = "component_cms_content";
	/**
	 * 内容分类表
	 */
	public static final String component_cms_content_category = "component_cms_content_category";

    /**
     * 动态数据表格表
     */
    public static final String component_data_dynamic_table = "component_data_dynamic_table";
    /**
     * 动态数据分类表
     */
    public static final String component_data_dynamic_data_category = "component_data_dynamic_data_category";
    /**
     * 动态数据指标分类表
     */
    public static final String component_data_dynamic_data_indicator_category = "component_data_dynamic_data_indicator_category";

    /**
     * 组件表
     */
    public static final String component_admin_component = "component_admin_component";


	/**
	 * 工作流项目表
	 */
	public static final String component_workflow_project = "component_workflow_project";
	/**
	 * 工作流定义表
	 */
	public static final String component_workflow_definition = "component_workflow_definition";

	/**
	 * 工作流定义历史表
	 */
	public static final String component_workflow_definition_history = "component_workflow_definition_history";



	/**
	 * 爬虫项目表
	 */
	public static final String component_crawler_project = "component_crawler_project";
	/**
	 * 爬虫定义表
	 */
	public static final String component_crawler_definition = "component_crawler_definition";

	/**
	 * 爬虫定义历史表
	 */
	public static final String component_crawler_definition_history = "component_crawler_definition_history";

}
