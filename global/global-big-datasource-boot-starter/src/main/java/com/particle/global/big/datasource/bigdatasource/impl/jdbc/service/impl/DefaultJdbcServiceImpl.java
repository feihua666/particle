package com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.PageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.IJdbcService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 默认jdbc查询服务实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 14:37
 */
public class DefaultJdbcServiceImpl implements IJdbcService<Map<String,Object>> {

	protected SqlSessionFactory sqlSessionFactory;


	/**
	 * 列表查询
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectList(BigDatasourceApi bigDatasourceApi, Object command) {
		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();
		List<Map<String, Object>> maps;
		try {
			maps = nativeSqlMapper.selectList(config.render(command),TemplateRenderDataWrap.create(command));
		} finally {
			sqlSession.close();
		}
		return maps;
	}

	/**
	 * 查询单条
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	@Override
	public Map<String, Object> selectOne(BigDatasourceApi bigDatasourceApi, Object command) {
		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();
		Map<String, Object> maps;
		try {
			maps = nativeSqlMapper.selectOne(config.render(command),TemplateRenderDataWrap.create(command));
		} finally {
			sqlSession.close();
		}
		return maps;
	}
	/**
	 * 分页查询
	 * @param bigDatasourceApi
	 * @param command
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> selectPage(BigDatasourceApi bigDatasourceApi, Object command) {
		// 默认从第一页开始
		Long pageNo = 1L;
		// 默认每页10条
		Long pageSize = 10L;
		boolean hasPageableAdapterConfig = false;
		PageableAdapterConfig pageableAdapterConfig = bigDatasourceApi.pageableAdapterConfig();
		if (pageableAdapterConfig != null) {
			PageQueryCommand pageQueryCommand = pageableAdapterConfig.obtainCommandPageInfo(command);
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

		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);
		Page pageQuery = new Page(pageNo, pageSize);
		JdbcBigDatasourceApiConfig config = (JdbcBigDatasourceApiConfig) bigDatasourceApi.config();

		Page<Map<String, Object>> maps;
		try {
			maps = nativeSqlMapper.selectPage(pageQuery,config.render(command),TemplateRenderDataWrap.create(command));
		} finally {
			sqlSession.close();
		}
		return maps;
	}


	/**
	 * 开启 SqlSession
	 * SqlSession 一般是方法或线程级别的，是线程不安全的
	 * @return
	 */
	protected SqlSession openSession(){
		return sqlSessionFactory.openSession();
	}

	public static DefaultJdbcServiceImpl create(SqlSessionFactory sqlSessionFactory) {
		DefaultJdbcServiceImpl defaultJdbcService = new DefaultJdbcServiceImpl();
		defaultJdbcService.setSqlSessionFactory(sqlSessionFactory);
		return defaultJdbcService;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}
