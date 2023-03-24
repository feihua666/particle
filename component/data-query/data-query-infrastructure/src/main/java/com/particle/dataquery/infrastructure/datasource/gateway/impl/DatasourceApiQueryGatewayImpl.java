package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import com.baomidou.dynamic.datasource.exception.CannotFindDataSourceException;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.gateway.DatasourceApiQueryGateway;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInParamValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiInSuccessValidateConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceApiJdbcBasicConfig;
import com.particle.dataquery.domain.datasource.value.DataQueryDatasourceJdbcConfig;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.datasource.bigdatasource.DataQueryDatasourceDynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.BigDatasource;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiCommandValidateConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiPageableAdapterConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiSuccessValidateConfig;
import com.particle.global.big.datasource.bigdatasource.dynamic.*;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.executor.BigDatasourceApiExecutor;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.JdbcBigDatasource;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.config.JdbcBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
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

	@Override
	public Object queryRealtime(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param) {
		String datasourceTypeDictIdValue = dataQueryDictGateway.getDictValueById(datasource.getTypeDictId());
		DataQueryDatasourceType dataQueryDatasourceType = DataQueryDatasourceType.valueOf(datasourceTypeDictIdValue);
		if (dataQueryDatasourceType == DataQueryDatasourceType.datasource_jdbc) {

				return doJdbcQuery(datasource, datasourceApi, param,true);

		}

		return new RuntimeException("can not support datasource type of " + dataQueryDatasourceType.itemValue());
	}

	@Override
	public Object query(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param) {
		// todo 缓存性能
		return queryRealtime(datasource,datasourceApi,param);
	}

	/**
	 * 执行jdbc查询
	 * @param datasource
	 * @param datasourceApi
	 * @param param
	 * @param tryLoadJdbcInnerDataSource 是否尝试动态重新加载数据源
	 * @return
	 */
	Object doJdbcQuery(DataQueryDatasource datasource, DataQueryDatasourceApi datasourceApi, Object param,boolean tryLoadJdbcInnerDataSource) {
		try {
			// 路由数据源
			DynamicBigDatasourceRoutingKey routingKey = DynamicBigDatasourceRoutingKeyFactory.of(jdbc, datasource.getId().getId().toString());

			// 手动设置，数据源
			DynamicBigDatasourceRoutingKeyHolder.set(routingKey);
			BigDatasourceApiExecutor apiExecutor = dynamicBigDatasource.getApiExecutor();
			DefaultBigDatasourceApi defaultBigDatasourceApi = datasourceApiQueryGatewayHelper.createDefaultBigDatasourceApiByDataQueryDatasourceApi(datasourceApi);

			return apiExecutor.execute(defaultBigDatasourceApi, param);
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
			DynamicBigDatasourceRoutingKey currentRoutingKey = DynamicBigDatasourceRoutingKeyHolder.get();
			String key = currentRoutingKey.toStringKey();
			if (currentRoutingKey instanceof JdbcBigDatasourceRoutingKey) {
				key += ">" + ((JdbcBigDatasourceRoutingKey<?>) currentRoutingKey).subKey();
			}

			log.warn("执行api {} 失败，未找到 {} 数据源，尝试动态加载数据源...",datasourceApi.getName(), key);
			// 如果数据源不存在，尝试添加数据源再试
			// 取出jdbc数据源，判断是否存在，如果不存在，可动态添加
			DynamicBigDatasourceRoutingKey jdbcRoutingKey = DynamicBigDatasourceRoutingKeyFactory.of(jdbc);
			Map<DynamicBigDatasourceRoutingKey, BigDatasource> bigDatasourceMap = dynamicBigDatasource.getBigDatasourceMap();
			boolean datasourceLoaded = false;
			if (bigDatasourceMap != null) {
				for (DynamicBigDatasourceRoutingKey dynamicBigDatasourceRoutingKey : bigDatasourceMap.keySet()) {
					if (jdbcRoutingKey.match(dynamicBigDatasourceRoutingKey)) {
						BigDatasource bigDatasource = bigDatasourceMap.get(dynamicBigDatasourceRoutingKey);
						if (bigDatasource instanceof JdbcBigDatasource) {
							DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = datasource.jdbcConfig();

							((JdbcBigDatasource) bigDatasource).addDataSourceByJdbcBigDatasourceConfig(
									// String dataSourceName
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
			}

			if (datasourceLoaded) {
				log.warn("尝试动态加载数据源结束,成功加载完成 {}",datasourceApi.getName(), key);
			}else {
				log.warn("尝试动态加载数据源结束,未成功 {}",datasourceApi.getName(), key);
			}

			// 如果没有加载直接抛出异常不再尝试
			if (!datasourceLoaded) {
				throw e;
			}
			return doJdbcQuery(datasource, datasourceApi, param,false);
		}
		finally {
			DynamicBigDatasourceRoutingKeyHolder.clear();
		}
	}

}
