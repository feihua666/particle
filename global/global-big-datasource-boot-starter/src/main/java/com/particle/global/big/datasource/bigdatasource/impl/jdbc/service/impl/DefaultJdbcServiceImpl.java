package com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.IJdbcService;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

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
	 * @param sqlTemplate
	 * @param command
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectList(String sqlTemplate, Object command) {
		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);
		List<Map<String, Object>> maps;
		try {
			maps = nativeSqlMapper.selectList(sqlTemplate,TemplateRenderDataWrap.create(command));
		} finally {
			sqlSession.close();
		}
		return maps;
	}

	/**
	 * 查询单条
	 * @param sqlTemplate
	 * @param command
	 * @return
	 */
	@Override
	public Map<String, Object> selectOne(String sqlTemplate, Object command) {
		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);
		Map<String, Object> maps;
		try {
			maps = nativeSqlMapper.selectOne(sqlTemplate,TemplateRenderDataWrap.create(command));
		} finally {
			sqlSession.close();
		}
		return maps;
	}
	/**
	 * 分页查询
	 * @param sqlTemplate
	 * @param command
	 * @param pageQuery 该对象用来设置请求页码和每页大小
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> selectPage(String sqlTemplate, Object command, Page pageQuery) {

		SqlSession sqlSession = openSession();
		NativeSqlMapper nativeSqlMapper = sqlSession.getMapper(NativeSqlMapper.class);

		Page<Map<String, Object>> maps;
		try {
			maps = nativeSqlMapper.selectPage(pageQuery,sqlTemplate,TemplateRenderDataWrap.create(command));
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
