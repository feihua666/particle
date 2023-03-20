package com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums;

/**
 * <p>
 * jdbc 大数据源接口 sql 模板类型
 * </p>
 *
 * @author yangwei
 * @since 2023-03-18 11:57
 */
public enum JdbcBigDatasourceApiConfigSqlTemplateType {

	/**
	 * enjoy模板
	 */
	enjoy_template,
	/**
	 * mybatisScript模板
	 */
	mybatis_script_template,
	/**
	 * 无需处理，原生保留
	 */
	raw
}
