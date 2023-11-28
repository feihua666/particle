package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.baomidou.dynamic.datasource.exception.CannotFindDataSourceException;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.domain.datasource.value.*;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceProvider;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.INeo4jBigDatasourceLoader;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCommandValidateConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiPageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiSuccessValidateConfig;
import com.particle.global.big.datasource.bigdatasource.dynamic.*;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceType;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceNotFoundException;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.Neo4jBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.config.Neo4jBigDatasourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 大数据源api查询接口网关实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 23:44
 */
@Slf4j
@Component
public class DatasourceApiQueryGatewayImpl implements DatasourceApiQueryGateway {
	private static String jdbc = DataQueryDatasourceDynamicBigDatasourceProvider.dataQueryGlobalBigDatasourceRoutingKey;

	@Autowired
	private DynamicBigDatasource dynamicBigDatasource;
	@Autowired
	private DataQueryDictGateway dataQueryDictGateway;
	@Autowired
	private DatasourceApiQueryGatewayHelper datasourceApiQueryGatewayHelper;


	@Autowired(required = false)
	private INeo4jBigDatasourceLoader iNeo4jBigDatasourceLoader;

	@Override
	public Object queryRealtime(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString) {
		String datasourceTypeDictIdValue = dataQueryDictGateway.getDictValueById(datasource.getTypeDictId());
		DataQueryDatasourceType dataQueryDatasourceType = DataQueryDatasourceType.valueOf(datasourceTypeDictIdValue);
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_jdbc) {
			return doJdbcQuery(datasource, datasourceApi, param,queryString,true);
		}
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_http) {
			return doHttpQuery(datasource, datasourceApi, param,queryString);
		}
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_neo4j) {
			return doNeo4jQuery(datasource, datasourceApi, param,queryString,true);
		}
		throw  new RuntimeException("can not support datasource type of " + dataQueryDatasourceType.itemValue());
	}

	@Override
	public Object query(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString) {
		return queryRealtime(datasource,datasourceApi,param,queryString);
	}

	/**
	 * 执行http查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param queryString
	 * @return
	 */
	Object doHttpQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString){
		try {
			// 路由数据源
			DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString());

			// 手动设置，数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_http);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
		}finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}
	/**
	 * 执行jdbc查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param tryLoadJdbcInnerDataSource 是否尝试动态重新加载数据源
	 * @return
	 */
	Object doJdbcQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean tryLoadJdbcInnerDataSource) {
		// 路由数据源
		DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(jdbc, datasource.getId().getId().toString());

		try {

			// 手动设置，数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_jdbc);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
		} catch (PersistenceException | CannotFindDataSourceException e) {
			if(!tryLoadJdbcInnerDataSource){
				throw e;
			}
			if(e instanceof PersistenceException){
				boolean cannotFindDataSourceException = ((PersistenceException) e).getCause() instanceof CannotFindDataSourceException;
				if (!cannotFindDataSourceException) {
					throw e;
				}
			}
			synchronized (datasource.getId().getId()){
				DynamicBigDatasourceRoutingKey currentRoutingKey = DynamicBigDatasourceRoutingKeyHolder.get();
				String key = currentRoutingKey.toStringKey();
				if (currentRoutingKey instanceof JdbcBigDatasourceRoutingKey) {
					key += ">" + ((JdbcBigDatasourceRoutingKey<?>) currentRoutingKey).subKey();
				}

				log.warn("执行api {} 失败，未找到 {} 数据源，尝试动态加载jdbc数据源...",datasourceApi.getName(), key);
				// 如果数据源不存在，尝试添加数据源再试
				// 取出jdbc数据源，判断是否存在，如果不存在，可动态添加
				DynamicBigDatasourceRoutingKey jdbcRoutingKey = DynamicBigDatasourceRoutingKeyFactory.of(jdbc);
				Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap = dynamicBigDatasource.getBigDatasourceMap();
				boolean datasourceLoaded = false;
				// 定义一个变量是否存在，尝试再找一下，因为可能并发原因已经初始化
				boolean isExistRoutingKey = false;
				if (bigDatasourceMap != null) {
					for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
						if (routingKey.match(dynamicBigDatasourceRoutingKey)) {
							isExistRoutingKey = true;
							break;
						}
					}

				}

				if (isExistRoutingKey) {
					// 	如果已经存在，说明之前加载过，这可能是并发原因
					log.info("未动态加载 {} jdbc数据源,因为  routingKey={}已存在，将重试查询",datasourceApi.getName(), routingKey);
				}else {

					for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
						if (jdbcRoutingKey.match(dynamicBigDatasourceRoutingKey)) {
							BigDatasource bigDatasource = bigDatasourceMap.get(dynamicBigDatasourceRoutingKey);
							if (bigDatasource instanceof JdbcBigDatasource) {
								DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = datasource.jdbcConfig();

								((JdbcBigDatasource) bigDatasource).addDataSourceByJdbcBigDatasourceConfig(
										// String dataSourceName,注意这里使用id
										datasource.getId().getId().toString(),

										JdbcBigDatasourceConfig.create(dataQueryDatasourceJdbcConfig.getDriverClassName(),
												dataQueryDatasourceJdbcConfig.getUrl(),
												dataQueryDatasourceJdbcConfig.getUsername(),
												dataQueryDatasourceJdbcConfig.getPassword())
								);
								datasourceLoaded = true;
							}
							break;
						}
					}

					if (datasourceLoaded) {
						log.warn("尝试动态加载 {} jdbc数据源结束,成功加载完成 routingKey={}",datasourceApi.getName(), routingKey);
					}else {
						log.warn("尝试动态加载 {} jdbc数据源结束,未成功 routingKey={}",datasourceApi.getName(), routingKey);
					}

					// 如果没有加载直接抛出异常不再尝试
					if (!datasourceLoaded) {
						throw e;
					}
				}

			}

			return doJdbcQuery(datasource, datasourceApi, param,queryString,false);
		}
		finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}
	/**
	 * 执行neo4j查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param queryString
	 * @param tryLoadNeo4jDataSource 是否尝试动态加载neo4j数据源
	 * @return
	 */
	Object doNeo4jQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean tryLoadNeo4jDataSource){

		// 路由数据源
		DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString());

		try {
			// 手动设置，数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_neo4j);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
		}catch (BigDatasourceNotFoundException e){
			if(!tryLoadNeo4jDataSource){
				throw e;
			}
			synchronized (datasource.getId().getId()){
				log.warn("执行api {} 失败，未找到 {} 数据源，尝试动态加载neo4j数据源...",datasourceApi.getName(), routingKey);

				// 	找不到数据源时，动态添加
				Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap = dynamicBigDatasource.getBigDatasourceMap();
				boolean datasourceLoaded = false;
				// 定义一个变量是否存在，尝试再找一下，因为可能并发原因已经初始化
				boolean isExistRoutingKey = false;
				if (bigDatasourceMap != null) {
					for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
						if (routingKey.match(dynamicBigDatasourceRoutingKey)) {
							isExistRoutingKey = true;
							break;
						}
					}
				}

				if (isExistRoutingKey) {
					// 	如果已经存在，说明之前加载过，这可能是并发原因
					log.info("未动态加载 {} neo4j数据源,因为  routingKey={}已存在，将重试查询",datasourceApi.getName(), routingKey);
				}else {
					// 尝试加载数据源
					DataQueryDatasourceNeo4jConfig config = datasource.neo4jConfig();
					Neo4jBigDatasourceConfig neo4jBigDatasourceConfig = Neo4jBigDatasourceConfig.create(
							config.getUri(),
							config.getUsername(),
							config.getPassword(),
							null);
					Neo4jBigDatasource neo4jBigDatasource = null;
					if (iNeo4jBigDatasourceLoader != null) {
						log.debug("use neo4jBigDatasourceLoader load neo4jBigDatasource");
						neo4jBigDatasource = iNeo4jBigDatasourceLoader.load(datasource.getName(),
								BigDatasourceType.datasource_neo4j,
								neo4jBigDatasourceConfig);
					}else {
						log.debug("use default neo4jBigDatasource");
						neo4jBigDatasource = Neo4jBigDatasource.createByNeo4jBigDatasourceConfig(
								datasource.getName(),
								BigDatasourceType.datasource_neo4j,
								neo4jBigDatasourceConfig);
					}

					bigDatasourceMap.put(DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString()), neo4jBigDatasource);
					datasourceLoaded = true;

					if (datasourceLoaded) {
						log.warn("尝试动态加载 {} neo4j数据源结束,成功加载完成 routingKey={}",datasourceApi.getName(), routingKey);
					}else {
						log.warn("尝试动态加载 {} neo4j数据源结束,未成功 routingKey={}",datasourceApi.getName(), routingKey);
					}

					// 如果没有加载直接抛出异常不再尝试
					if (!datasourceLoaded) {
						throw e;
					}
				}

			}

			return doNeo4jQuery(datasource, datasourceApi, param,queryString,false);
		}
		finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}
}
