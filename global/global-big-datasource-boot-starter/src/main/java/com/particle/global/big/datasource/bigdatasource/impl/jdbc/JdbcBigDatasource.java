package com.particle.global.big.datasource.bigdatasource.impl.jdbc;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.event.DataSourceInitEvent;
import com.baomidou.dynamic.datasource.event.EncDataSourceInitEvent;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.particle.global.big.datasource.bigdatasource.AbstractBigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceErrorCode;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.executor.DefaultBigDatasourceExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.creator.CustomDefaultDataSourceCreator;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.executor.JdbcBigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.impl.DefaultJdbcServiceImpl;
import com.particle.global.mybatis.plus.crud.MetricsAndSlowSqlMybatisInterceptor;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p>
 * 基于jdbc的数据源实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:01
 */
@Data
public class JdbcBigDatasource extends AbstractBigDatasource {

	/**
	 * 大数据源api执行器，主要用来执行sql
	 */
	private JdbcBigDatasourceApiExecutor jdbcBigDatasourceApiExecutor;

	/**
	 * 使用动态多数据源，一个jdbc大数据源可以支持多个数据源
	 */
	protected DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

	protected SqlSessionFactory sqlSessionFactory;

	/**
	 * 在创建数据源时使用
	 */
	protected DynamicDataSourceProperties dynamicDataSourceProperties = new DynamicDataSourceProperties();

	protected DataSourceInitEvent dataSourceInitEvent = new EncDataSourceInitEvent();
	/**
	 * 数据源
	 */
	protected CustomDefaultDataSourceCreator defaultDataSourceCreator = new CustomDefaultDataSourceCreator(dynamicDataSourceProperties,dataSourceInitEvent);

	@Override
	public BigDatasourceApiExecutor getApiExecutor() throws BigDatasourceException {
		return jdbcBigDatasourceApiExecutor;
	}

	@Override
	public BigDatasourceExecutor getExecutor(BigDatasourceApi bigDatasourceApi) throws BigDatasourceException {

		return new DefaultBigDatasourceExecutor(bigDatasourceApi, jdbcBigDatasourceApiExecutor);
	}

	private static JdbcBigDatasource createBySimpleInstance(JdbcBigDatasource jdbcBigDatasource){
		jdbcBigDatasource.sqlSessionFactory = initSqlSessionFactory(jdbcBigDatasource.getDynamicRoutingDataSource());

		JdbcBigDatasourceApiExecutor jdbcBigDatasourceApiExecutor = JdbcBigDatasourceApiExecutor.create(
				DefaultJdbcServiceImpl.create(jdbcBigDatasource.getSqlSessionFactory())
		);
		jdbcBigDatasource.jdbcBigDatasourceApiExecutor = jdbcBigDatasourceApiExecutor;

		jdbcBigDatasource.init();

		return jdbcBigDatasource;
	}

	public static JdbcBigDatasource create(String name, BigDatasourceType type) {
		return create(name,type,null);
	}


	public static JdbcBigDatasource create(String name, BigDatasourceType type,DynamicDataSourceProperties dynamicDataSourceProperties) {
		JdbcBigDatasource jdbcBigDatasource = new JdbcBigDatasource();
		jdbcBigDatasource.setName(name);
		jdbcBigDatasource.setType(type);
		if (dynamicDataSourceProperties != null) {
			jdbcBigDatasource.dynamicDataSourceProperties = (dynamicDataSourceProperties);
			jdbcBigDatasource.defaultDataSourceCreator = new CustomDefaultDataSourceCreator(dynamicDataSourceProperties,jdbcBigDatasource.getDataSourceInitEvent());
		}
		return createBySimpleInstance(jdbcBigDatasource);
	}

	/**
	 * 初始化其它信息
	 */
	public void init(){
		dynamicRoutingDataSource.setPrimary(dynamicDataSourceProperties.getPrimary());
		dynamicRoutingDataSource.setStrict(dynamicDataSourceProperties.getStrict());
		dynamicRoutingDataSource.setStrategy(dynamicDataSourceProperties.getStrategy());
		dynamicRoutingDataSource.setP6spy(dynamicDataSourceProperties.getP6spy());
		dynamicRoutingDataSource.setSeata(dynamicDataSourceProperties.getSeata());
	}

	/**
	 * 本jdbc数据源，使用动态多数据源
	 * @param dataSourceName 名称用来路由
	 * @param dataSource
	 */
	public void addDataSource(String dataSourceName,DataSource dataSource) {
		dynamicRoutingDataSource.addDataSource(dataSourceName,dataSource);
	}

	/**
	 * 根据自定义配置添加数据源
	 * @param dataSourceName
	 * @param jdbcBigDatasourceConfig
	 */
	public void addDataSourceByJdbcBigDatasourceConfig(String dataSourceName, JdbcBigDatasourceConfig jdbcBigDatasourceConfig) {

		DataSourceProperty dataSourceProperty = new DataSourceProperty();
		dataSourceProperty.setDriverClassName(jdbcBigDatasourceConfig.getDriverClassName());
		dataSourceProperty.setUrl(jdbcBigDatasourceConfig.getUrl());
		dataSourceProperty.setUsername(jdbcBigDatasourceConfig.getUsername());
		dataSourceProperty.setPassword(jdbcBigDatasourceConfig.getPassword());
		addDataSourceByDataSourceProperty(dataSourceName, dataSourceProperty);
	}

	/**
	 * 根据原生动态数据原配置添加数据源
	 * @param dataSourceName
	 * @param dataSourceProperty
	 */
	public void addDataSourceByDataSourceProperty(String dataSourceName, DataSourceProperty dataSourceProperty) {
		if (defaultDataSourceCreator == null) {
			throw new IllegalStateException("defaultDataSourceCreator must be set first");
		}

		DataSource dataSource = defaultDataSourceCreator.createDataSource(dataSourceProperty);
		// 添加数据源
		addDataSource(dataSourceName,dataSource);
	}


	/**
	 * 使用 mybatis SqlSessionFactory
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	public static SqlSessionFactory initSqlSessionFactory(DataSource dataSource){

		MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		mybatisSqlSessionFactoryBean.setDataSource(dataSource);

		// configuration
		MybatisConfiguration configuration = mybatisSqlSessionFactoryBean.getConfiguration();
		if(configuration == null){
			configuration = new MybatisConfiguration();
		}
		/**
		 * 使用 {@link NativeSqlMapper}
		 */
		configuration.addMappers(NativeSqlMapper.class.getPackage().getName());
		mybatisSqlSessionFactoryBean.setConfiguration(configuration);

		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		// 分布插件
		mybatisPlusInterceptor.addInnerInterceptor( new PaginationInnerInterceptor());
		mybatisSqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor,new MetricsAndSlowSqlMybatisInterceptor());


		// global config
		GlobalConfig globalConfig = new GlobalConfig();
		GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
		globalConfig.setDbConfig(dbConfig);
		dbConfig.setWhereStrategy(FieldStrategy.NOT_EMPTY);
		mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);


		try {
			return mybatisSqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			throw new BigDatasourceException(BigDatasourceErrorCode.BIG_DATASOURCE_ERROR, e);
		}
	}

	public DynamicDataSourceProperties getDynamicDataSourceProperties() {
		return dynamicDataSourceProperties;
	}

	public CustomDefaultDataSourceCreator getDefaultDataSourceCreator() {
		return defaultDataSourceCreator;
	}

	@SneakyThrows
	@Override
	public void close() throws IOException {
		dynamicRoutingDataSource.destroy();
	}
}
