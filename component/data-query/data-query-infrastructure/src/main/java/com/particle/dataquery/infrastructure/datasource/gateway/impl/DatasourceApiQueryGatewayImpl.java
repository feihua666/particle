package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyFactory;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKeyHolder;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	@Override
	public Object queryRealtime(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean isTest) {
		String datasourceTypeDictIdValue = dataQueryDictGateway.getDictValueById(datasource.getTypeDictId());
		DataQueryDatasourceType dataQueryDatasourceType = DataQueryDatasourceType.valueOf(datasourceTypeDictIdValue);
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_jdbc) {
			return doJdbcQuery(datasource, datasourceApi, param,queryString,isTest);
		}
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_http) {
			return doHttpQuery(datasource, datasourceApi, param,queryString,isTest);
		}
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_neo4j) {
			return doNeo4jQuery(datasource, datasourceApi, param,queryString,isTest);
		}
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_es) {
			return doEsQuery(datasource, datasourceApi, param,queryString,isTest);
		}
		throw  new RuntimeException("can not support datasource type of " + dataQueryDatasourceType.itemValue());
	}

	@Override
	public Object query(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString) {
		return queryRealtime(datasource,datasourceApi,param,queryString,false);
	}

	/**
	 * 执行http查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param queryString
	 * @return
	 */
	Object doHttpQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean isTest){
		try {
			// 路由数据源
			DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString());

			// 手动设置，路由数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_http,isTest);

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
	 * @return
	 */
	Object doJdbcQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean isTest) {
		// 路由数据源
		DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(jdbc, datasource.getId().getId().toString());

		try {

			// 手动设置，路由数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_jdbc,isTest);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
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
	 * @return
	 */
	Object doNeo4jQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean isTest){

		// 路由数据源
		DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString());

		try {
			// 手动设置，路由数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_neo4j,isTest);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
		}
		finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}

	/**
	 * 执行es查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param queryString
	 * @return
	 */
	Object doEsQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,String queryString,boolean isTest){

		// 路由数据源
		DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(datasource.getId().getId().toString());

		try {
			// 手动设置，数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi =
					datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi,DataQueryDatasourceType.datasource_es,isTest);

			return apiExecutor.execute(defaultBigDatasourceApi, param,queryString);
		}
		finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}
}
