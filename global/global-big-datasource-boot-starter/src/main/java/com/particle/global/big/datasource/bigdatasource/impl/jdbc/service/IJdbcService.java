package com.particle.global.big.datasource.bigdatasource.impl.jdbc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * jdbc服务，该服务实现具体的数据查询功能
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 14:33
 */
public interface IJdbcService<T> {
	/**
	 * 列表查询
	 * @param sqlTemplate
	 * @param command
	 * @return
	 */
	List<T> selectList(String sqlTemplate, Object command);

	/**
	 * 查询单条
	 *
	 * @param sqlTemplate
	 * @param command
	 * @return
	 */
	T selectOne(String sqlTemplate, Object command);

	/**
	 * 分页查询
	 *
	 * @param sqlTemplate
	 * @param command
	 * @param pageQuery 该参数用来设置请求页码和每页大小
	 * @return
	 */
	Page<T> selectPage(String sqlTemplate, Object command, Page pageQuery);
}
