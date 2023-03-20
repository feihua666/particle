package com.particle.global.big.datasource.bigdatasource.impl.jdbc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

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
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	List<T> selectList(BigDatasourceApi bigDatasourceApi, Object command);

	/**
	 * 查询单条
	 *
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	T selectOne(BigDatasourceApi bigDatasourceApi, Object command);

	/**
	 * 分页查询
	 *
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	Page<T> selectPage(BigDatasourceApi bigDatasourceApi, Object command);
}
