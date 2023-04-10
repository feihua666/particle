package com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.PageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.IJdbcService;
import com.particle.global.dto.basic.PageQueryCommand;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public Object doExecutePage(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		// 默认从第一页开始
		Long pageNo = 1L;
		// 默认每页10条
		Long pageSize = 10L;
		boolean hasPageableAdapterConfig = false;
		PageableAdapterConfig pageableAdapterConfig = bigDatasourceApi.pageableAdapterConfig();
		if (pageableAdapterConfig != null) {
			PageQueryCommand pageQueryCommand = pageableAdapterConfig.obtainCommandPageInfo(command,queryString);
			if (pageQueryCommand != null) {
				pageNo = pageQueryCommand.getPageNo();
				pageSize = pageQueryCommand.getPageSize();
				hasPageableAdapterConfig = true;
			}
		}
		if (!hasPageableAdapterConfig) {
			if(command instanceof PageQueryCommand){
				pageNo = ((PageQueryCommand) command).getPageNo();
				pageSize = ((PageQueryCommand) command).getPageSize();
			} else if(command instanceof Map){
				Object pageNoInMap = ((Map<?, ?>) command).get("pageNo");
				Object pageSizeInMap = ((Map<?, ?>) command).get("pageSize");
				pageNo = Optional.ofNullable(pageNoInMap).map(String::valueOf).map(Long::valueOf).orElse(pageNo);
				pageSize = Optional.ofNullable(pageSizeInMap).map(String::valueOf).map(Long::valueOf).orElse(pageSize);
			}
		}
		Page pageQuery = new Page(pageNo, pageSize);
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();
		JdbcBigDatasourceApiConfig.RenderResult renderResult = config.render(jdbcService, command,queryString);
		if (renderResult.getResult() != null) {
			boolean b = renderResult.getResult() instanceof Page;
			if (b) {
				return ((Page<?>) renderResult.getResult());
			}else {
				throw new BigDatasourceException(" jdbc render result must be instance of " + Page.class.getName());
			}
		}

		Page<?> page = jdbcService.selectPage(renderResult.getStrTemplateResult(),command,pageQuery);
		return page;
	}

	@Override
	public Object doExecuteMulti(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();
		JdbcBigDatasourceApiConfig.RenderResult renderResult = config.render(jdbcService, command,queryString);
		if (renderResult.getResult() != null) {
			boolean b = renderResult.getResult() instanceof Collection;
			if (b) {
				return ((Collection) renderResult.getResult());
			}else {
				throw new BigDatasourceException(" jdbc render result must be instance of " + Collection.class.getName());
			}
		}
		List<?> list = jdbcService.selectList(renderResult.getStrTemplateResult(), command);
		return list;
	}

	@Override
	public Object doExecuteSingle(BigDatasourceApi bigDatasourceApi, Object command,String queryString) {
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();
		JdbcBigDatasourceApiConfig.RenderResult renderResult = config.render(jdbcService, command,queryString);
		if (renderResult.getResult() != null) {
			return renderResult.getResult();
		}
		Object r = jdbcService.selectOne(renderResult.getStrTemplateResult(), command);
		return r;
	}

}
