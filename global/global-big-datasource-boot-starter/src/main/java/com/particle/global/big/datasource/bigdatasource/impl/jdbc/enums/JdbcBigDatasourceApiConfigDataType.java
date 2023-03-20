package com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums;

/**
 * <p>
 * jdbc 大数据源接口 sql 模板类型
 * </p>
 *
 * @author yangwei
 * @since 2023-03-18 11:57
 */
public enum JdbcBigDatasourceApiConfigDataType {

	/**
	 * 单条数据
	 * 如果返回多条时会抛出异常
	 */
	single,
	/**
	 * 多条数据
	 */
	multiple,
	/**
	 * 分页数据
	 * 分页数据使用的是mybatis plus的 {@link com.baomidou.mybatisplus.core.metadata.IPage}
	 */
	page,

}
