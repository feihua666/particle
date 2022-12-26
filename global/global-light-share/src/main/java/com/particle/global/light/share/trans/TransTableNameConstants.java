package com.particle.global.light.share.trans;

/**
 * <p>
 * 翻译，根据表名翻译常量
 * 表名翻译默认实现为 {@link TransConstants#defaultTransType} 支持的类型，可通过 particle.trans.table.{tableName}={newTableName} 配置以替换默认使用的表
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 18:20
 */
public class TransTableNameConstants {

	public static final String component_func_group = "component_func_group";
	public static final String component_func = "component_func";
	public static final String component_dict = "component_dict";
	public static final String component_area = "component_area";
	public static final String component_user = "component_user";
	public static final String component_user_identifier = "component_user_identifier";
}
