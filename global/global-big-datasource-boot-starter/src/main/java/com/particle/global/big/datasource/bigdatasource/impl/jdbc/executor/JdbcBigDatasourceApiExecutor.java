package com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.IJdbcService;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * jdbc大数据源接口执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 14:00
 */
@Data
public class JdbcBigDatasourceApiExecutor extends AbstractJdbcBigDatasourceApiExecutor {

	protected IJdbcService jdbcService;

	public static JdbcBigDatasourceApiExecutor create( IJdbcService IJdbcService) {
		JdbcBigDatasourceApiExecutor jdbcBigDatasourceApiExecutor = new JdbcBigDatasourceApiExecutor();
		jdbcBigDatasourceApiExecutor.setJdbcService(IJdbcService);
		return jdbcBigDatasourceApiExecutor;
	}

	@Override
	public Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command) {
		Page<?> page = jdbcService.selectPage(bigDatasourceApi,command);
		return page;
	}

	@Override
	public Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command) {
		List<?> list = jdbcService.selectList(bigDatasourceApi, command);
		return list;
	}

	@Override
	public Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command) {
		Object r = jdbcService.selectOne(bigDatasourceApi, command);
		return r;
	}

}
