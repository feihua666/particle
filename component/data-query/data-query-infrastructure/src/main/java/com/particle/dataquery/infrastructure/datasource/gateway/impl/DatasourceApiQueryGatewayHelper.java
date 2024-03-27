package com.particle.dataquery.infrastructure.datasource.gateway.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiNeo4jBasicConfigCqlTemplateType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceType;
import com.particle.dataquery.domain.datasource.value.*;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.dataquery.infrastructure.DataQueryInfrastructureConfiguration;
import com.particle.dataquery.infrastructure.dataapi.gateway.impl.DataApiQueryGatewayImpl;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApiContext;
import com.particle.global.big.datasource.bigdatasource.api.DefaultBigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.api.config.*;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiPageableAdapterType;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import com.particle.global.big.datasource.bigdatasource.enums.ParamValidateType;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config.ElasticsearchBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDslTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.http.api.config.HttpBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.config.HttpBigDatasourceConfig;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigContentType;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestMethod;
import com.particle.global.big.datasource.bigdatasource.impl.http.enums.HttpBigDatasourceApiConfigRequestUrlRenderType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config.JdbcBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config.Neo4jBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigCqlTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigDataType;
import com.particle.global.tool.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 23:44
 */
@Component
public class DatasourceApiQueryGatewayHelper {

	public static final String apiContext_dataQueryProviderId = "dataQueryProviderId";

	private static Map<String, Object> outExtConfigBindingsMap;
	private static Map<String, Object> outExtConfigBindingsMapTest;
	@Autowired
	private DataQueryDictGateway dataQueryDictGateway;

	@Qualifier(DataQueryInfrastructureConfiguration.dataQueryDataApiExecutor)
	@Autowired
	private ExecutorService dataQueryDataApiExecutor;


	private static DataApiQueryGatewayImpl.DatasourceApiInvoker datasourceApiInvoker;
	private static DataApiQueryGatewayImpl.DatasourceApiInvoker datasourceApiInvokerTest;
	/**
	 * 初始化出参扩展配置全局使用
	 */
	private void initOutExtConfigBindingsMap(boolean isTest) {
		if (!isTest && outExtConfigBindingsMap == null) {
			// 少 new 几个对象
			if (datasourceApiInvoker == null) {
				datasourceApiInvoker = new DataApiQueryGatewayImpl.DatasourceApiInvoker(SpringContextHolder.getBean(DataApiQueryGatewayImpl.class),false);
			}
			// 对出参扩展配置支持调用数据源接口
			if (outExtConfigBindingsMap == null) {
				Map<String, Object> newOutExtConfigBindingsMap = new HashMap<>();
				newOutExtConfigBindingsMap.put("datasourceApi", datasourceApiInvoker);
				newOutExtConfigBindingsMap.put("dataQueryDataApiExecutor", dataQueryDataApiExecutor);
				outExtConfigBindingsMap = newOutExtConfigBindingsMap;
			}
		}

		if (isTest && outExtConfigBindingsMapTest == null) {
			if (datasourceApiInvokerTest == null) {
				datasourceApiInvokerTest = new DataApiQueryGatewayImpl.DatasourceApiInvoker(SpringContextHolder.getBean(DataApiQueryGatewayImpl.class),true);
			}
			if (outExtConfigBindingsMapTest == null) {
				Map<String, Object> newOutExtConfigBindingsMap = new HashMap<>();
				newOutExtConfigBindingsMap.put("datasourceApi", datasourceApiInvokerTest);
				newOutExtConfigBindingsMap.put("dataQueryDataApiExecutor", dataQueryDataApiExecutor);
				outExtConfigBindingsMapTest = newOutExtConfigBindingsMap;
			}
		}
	}

	/**
	 * 专门针对出参扩展配置全局使用
	 * @return
	 */
	public Map<String, Object> outExtConfigBindingsMap(boolean isTest){
		initOutExtConfigBindingsMap(isTest);
		return isTest ? outExtConfigBindingsMapTest : outExtConfigBindingsMap;
	}
	/**
	 * 根据数据查询api创建
	 * @param dataQueryDataApi
	 * @return
	 */
	public DefaultBigDatasourceApi createDefaultBigDatasourceApiByDataQueryDataApi(DataQueryDataApi dataQueryDataApi,boolean isTest) {
		String datasourceApiResponseTypeDictIdValue = dataQueryDictGateway.getDictValueById(dataQueryDataApi.getResponseTypeDictId());

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				dataQueryDataApi.getId().toString()+ "_dataQueryDataApi_" + dataQueryDataApi.getName(),
				// 响应包装
				datasourceApiResponseTypeDictIdValue == null ? BigDatasourceApiResponseWrapType.proxy : BigDatasourceApiResponseWrapType.valueOf(datasourceApiResponseTypeDictIdValue),
				null,
				null,
				pageableAdapterConfig(dataQueryDataApi),
				commandValidateConfig(dataQueryDataApi),
				successValidateConfig(dataQueryDataApi)
		);
		// 扩展配置
		DataQueryDatasourceApiInParamExtConfig dataQueryDatasourceApiInParamExtConfig = dataQueryDataApi.inParamExtConfig();
		if (dataQueryDatasourceApiInParamExtConfig != null) {
			defaultBigDatasourceApi.setCommandExtConfig(BigDatasourceApiCommandExtConfig.create(dataQueryDatasourceApiInParamExtConfig.getGroovyScript()));
		}
		DataQueryDatasourceApiOutParamExtConfig dataQueryDatasourceApiOutParamExtConfig = dataQueryDataApi.outParamExtConfig();
		if (dataQueryDatasourceApiOutParamExtConfig != null) {
			defaultBigDatasourceApi.setResultExtConfig(BigDatasourceApiResultExtConfig.create(dataQueryDatasourceApiOutParamExtConfig.getGroovyScript(),outExtConfigBindingsMap(isTest)));
		}
		// 翻译配置
		DataQueryDatasourceApiTransConfig dataQueryDatasourceApiTransConfig = dataQueryDataApi.outParamTransConfig();
		if (dataQueryDatasourceApiTransConfig != null) {
			defaultBigDatasourceApi.setTransConfig(transConfig(dataQueryDatasourceApiTransConfig));
		}
		// 字典配置
		DataQueryDatasourceApiDictConfig dataQueryDatasourceApiDictConfig = dataQueryDataApi.dictConfig();
		if (dataQueryDatasourceApiDictConfig != null) {
			defaultBigDatasourceApi.setDictGroups(dictGroups(dataQueryDatasourceApiDictConfig));
		}

		return defaultBigDatasourceApi;
	}


	/**
	 * 数据源接口创建
	 * @param datasourceApi
	 * @param dataQueryDatasourceType
	 * @return
	 */
	public DefaultBigDatasourceApi createDefaultBigDatasourceApiByDataQueryDatasourceApi(DataQueryDatasourceApi datasourceApi,
																						 DataQueryDatasourceType dataQueryDatasourceType,
																						 boolean isTest){
		String datasourceApiResponseTypeDictIdValue = dataQueryDictGateway.getDictValueById(datasourceApi.getResponseTypeDictId());
		IBigDatasourceApiConfig  iBigDatasourceApiConfig = null;
		if (DataQueryDatasourceType.datasource_jdbc == dataQueryDatasourceType) {
			iBigDatasourceApiConfig = jdbcBigDatasourceApiConfig(datasourceApi,isTest);
		}else if (DataQueryDatasourceType.datasource_http == dataQueryDatasourceType) {
			iBigDatasourceApiConfig = httpBigDatasourceApiConfig(datasourceApi,isTest);
		}else if (DataQueryDatasourceType.datasource_neo4j == dataQueryDatasourceType) {
			iBigDatasourceApiConfig = neo4jBigDatasourceApiConfig(datasourceApi,isTest);
		}else if (DataQueryDatasourceType.datasource_es == dataQueryDatasourceType) {
			iBigDatasourceApiConfig = esBigDatasourceApiConfig(datasourceApi,isTest);
		}

		DefaultBigDatasourceApi defaultBigDatasourceApi = DefaultBigDatasourceApi.create(
				datasourceApi.getId().toString()+ "_" + datasourceApi.getName(),
				// 响应包装
				BigDatasourceApiResponseWrapType.valueOf(datasourceApiResponseTypeDictIdValue),
				iBigDatasourceApiConfig,
				null,
				pageableAdapterConfig(datasourceApi),
				commandValidateConfig(datasourceApi),
				successValidateConfig(datasourceApi)
		);
		// 是否使用缓存
		defaultBigDatasourceApi.setIsUseCache(datasourceApi.getIsUseCache());
		// 扩展配置
		DataQueryDatasourceApiInParamExtConfig dataQueryDatasourceApiInParamExtConfig = datasourceApi.inParamExtConfig();
		if (dataQueryDatasourceApiInParamExtConfig != null) {
			defaultBigDatasourceApi.setCommandExtConfig(BigDatasourceApiCommandExtConfig.create(dataQueryDatasourceApiInParamExtConfig.getGroovyScript()));
		}
		// 出参扩展配置
		DataQueryDatasourceApiOutParamExtConfig dataQueryDatasourceApiOutParamExtConfig = datasourceApi.outParamExtConfig();
		if (dataQueryDatasourceApiOutParamExtConfig != null) {
			defaultBigDatasourceApi.setResultExtConfig(BigDatasourceApiResultExtConfig.create(dataQueryDatasourceApiOutParamExtConfig.getGroovyScript(),outExtConfigBindingsMap(isTest)));
		}

		// 翻译配置
		DataQueryDatasourceApiTransConfig dataQueryDatasourceApiTransConfig = datasourceApi.outParamTransConfig();
		if (dataQueryDatasourceApiTransConfig != null) {
			BigDatasourceApiTransConfig bigDatasourceApiTransConfig = transConfig(dataQueryDatasourceApiTransConfig);
			defaultBigDatasourceApi.setTransConfig(bigDatasourceApiTransConfig);
			// 	数据源接口暂不支持设置类型，以名引起相互调用麻烦
			// 本来不想支持，但感觉不太灵活，这里先放开，尽量建立翻译数据不做任何处理，以免引起相互调用死循环
			// Optional.ofNullable(bigDatasourceApiTransConfig).map(BigDatasourceApiTransConfig::getTransItems)
			// 		.ifPresent(items -> items.forEach(transItem -> transItem.setType(null)));
		}
		// 字典配置
		DataQueryDatasourceApiDictConfig dataQueryDatasourceApiDictConfig = datasourceApi.dictConfig();
		if (dataQueryDatasourceApiDictConfig != null) {
			defaultBigDatasourceApi.setDictGroups(dictGroups(dataQueryDatasourceApiDictConfig));
		}

		// 时间配置
		Integer connectTimeout = datasourceApi.getConnectTimeout();
		if (connectTimeout != null) {
			defaultBigDatasourceApi.setConnectTimeout(connectTimeout);
		}
		Integer readTimeout = datasourceApi.getReadTimeout();
		if (readTimeout != null) {
			defaultBigDatasourceApi.setReadTimeout(readTimeout);
		}

		// 传递一些数据，目前添加主要是收集调用过程中的关键数据，以支持开放接口收集供应商调用数据
		BigDatasourceApiContext bigDatasourceApiContext = defaultBigDatasourceApi.apiContext();
		bigDatasourceApiContext.putData("dataQueryProviderId",datasourceApi.getDataQueryProviderId());
		return defaultBigDatasourceApi;
	}


	/**
	 * jdbc基础配置
	 * @param datasourceApi
	 * @return
	 */
	private JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig(DataQueryDatasourceApi datasourceApi,boolean isTest) {
		DataQueryDatasourceApiJdbcBasicConfig dataQueryDatasourceApiJdbcBasicConfig = datasourceApi.jdbcBasicConfig();

		JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig = JdbcBigDatasourceApiConfig.create(
				JdbcBigDatasourceApiConfigSqlTemplateType.valueOf(dataQueryDatasourceApiJdbcBasicConfig.getSqlTemplateType().itemValue()),
				JdbcBigDatasourceApiConfigDataType.valueOf(dataQueryDatasourceApiJdbcBasicConfig.getDataType().itemValue()),
				dataQueryDatasourceApiJdbcBasicConfig.getSqlTemplate(),
				dataQueryDatasourceApiJdbcBasicConfig.getIsSearchCount());
		jdbcBigDatasourceApiConfig.setExtBindings(outExtConfigBindingsMap(isTest));
		return jdbcBigDatasourceApiConfig;
	}

	/**
	 * http基础配置
	 * @param datasourceApi
	 * @return
	 */
	private HttpBigDatasourceApiConfig httpBigDatasourceApiConfig(DataQueryDatasourceApi datasourceApi,boolean isTest) {
		DataQueryDatasourceApiHttpBasicConfig config = datasourceApi.httpBasicConfig();
		HttpBigDatasourceApiConfig httpBigDatasourceApiConfig = HttpBigDatasourceApiConfig.create(
				HttpBigDatasourceApiConfigRequestMethod.valueOf(config.getRequestMethod().itemValue()),
				HttpBigDatasourceApiConfigContentType.valueOf(config.getRequestContentType().itemValue()),
				HttpBigDatasourceApiConfigContentType.valueOf(config.getResponseContentType().itemValue()),
				HttpBigDatasourceApiConfigRequestUrlRenderType.valueOf(config.getRequestUrlRenderType().itemValue()),
				config.getRequestUrlTemplate()
		);
		httpBigDatasourceApiConfig.setExtBindings(outExtConfigBindingsMap(isTest));
		return httpBigDatasourceApiConfig;
	}

	/**
	 * neo4j基础配置
	 * @param datasourceApi
	 * @return
	 */
	private Neo4jBigDatasourceApiConfig neo4jBigDatasourceApiConfig(DataQueryDatasourceApi datasourceApi,boolean isTest) {
		DataQueryDatasourceApiNeo4jBasicConfig config = datasourceApi.neo4jBasicConfig();
		Neo4jBigDatasourceApiConfig neo4jBigDatasourceApiConfig = Neo4jBigDatasourceApiConfig.create(
				Neo4jBigDatasourceApiConfigCqlTemplateType.valueOf(config.getCqlTemplateType().itemValue()),
				Neo4jBigDatasourceApiConfigDataType.valueOf(config.getDataType().itemValue()),
				config.getCqlTemplate(),
				config.getCqlCountTemplate()
		);
		neo4jBigDatasourceApiConfig.setExtBindings(outExtConfigBindingsMap(isTest));
		return neo4jBigDatasourceApiConfig;
	}
	/**
	 * es基础配置
	 * @param datasourceApi
	 * @return
	 */
	private ElasticsearchBigDatasourceApiConfig esBigDatasourceApiConfig(DataQueryDatasourceApi datasourceApi,boolean isTest) {
		DataQueryDatasourceApiEsBasicConfig config = datasourceApi.esBasicConfig();
		ElasticsearchBigDatasourceApiConfig elasticsearchBigDatasourceApiConfig = ElasticsearchBigDatasourceApiConfig.create(
				ElasticsearchBigDatasourceApiConfigDslTemplateType.valueOf(config.getDslTemplateType().itemValue()),
				ElasticsearchBigDatasourceApiConfigDataType.valueOf(config.getDataType().itemValue()),
				config.getIndexNames(),
				config.getDslTemplate(),
				config.getDslCountTemplate()
		);
		elasticsearchBigDatasourceApiConfig.setExtBindings(outExtConfigBindingsMap(isTest));
		return elasticsearchBigDatasourceApiConfig;
	}

	/**
	 * 分页解析信息配置
	 * @param datasourceApi
	 * @return
	 */
	private BigDatasourceApiPageableAdapterConfig pageableAdapterConfig(DataQueryDatasourceApi datasourceApi) {
		return pageableAdapterConfig(datasourceApi.pageableAdapterConfig());
	}

	private BigDatasourceApiCommandValidateConfig commandValidateConfig(DataQueryDatasourceApi datasourceApi) {
		DataQueryDatasourceApiInParamValidateConfig dataQueryDatasourceApiInParamValidateConfig = datasourceApi.inParamValidateConfig();
		return commandValidateConfig(dataQueryDatasourceApiInParamValidateConfig);
	}

	private BigDatasourceApiSuccessValidateConfig successValidateConfig(DataQueryDatasourceApi datasourceApi) {
		DataQueryDatasourceApiInSuccessValidateConfig dataQueryDatasourceApiInSuccessValidateConfig = datasourceApi.outParamSuccessConfigJson();
		return successValidateConfig(dataQueryDatasourceApiInSuccessValidateConfig);
	}


	/**
	 * 分页解析信息配置
	 * @param dataQueryDataApi
	 * @return
	 */
	private BigDatasourceApiPageableAdapterConfig pageableAdapterConfig(DataQueryDataApi dataQueryDataApi) {
		return pageableAdapterConfig(dataQueryDataApi.pageableAdapterConfig());
	}

	private BigDatasourceApiCommandValidateConfig commandValidateConfig(DataQueryDataApi dataQueryDataApi) {
		DataQueryDatasourceApiInParamValidateConfig dataQueryDatasourceApiInParamValidateConfig = dataQueryDataApi.inParamValidateConfig();
		return commandValidateConfig(dataQueryDatasourceApiInParamValidateConfig);
	}

	private BigDatasourceApiSuccessValidateConfig successValidateConfig(DataQueryDataApi dataQueryDataApi) {
		DataQueryDatasourceApiInSuccessValidateConfig dataQueryDatasourceApiInSuccessValidateConfig = dataQueryDataApi.outParamSuccessConfigJson();
		return successValidateConfig(dataQueryDatasourceApiInSuccessValidateConfig);
	}




	/**
	 * 分页解析信息配置
	 * @param pageableAdapterConfigObj
	 * @return
	 */
	private BigDatasourceApiPageableAdapterConfig pageableAdapterConfig(DataQueryDatasourceApiPageableAdapterConfig pageableAdapterConfigObj) {
		return Optional.ofNullable(pageableAdapterConfigObj).map(
				pageableAdapterConfig -> BigDatasourceApiPageableAdapterConfig.create(
						BigDatasourceApiPageableAdapterType.valueOf(pageableAdapterConfig.getInParamTemplateType().itemValue()),
						pageableAdapterConfig.getInParamTemplate(),
						BigDatasourceApiPageableAdapterType.valueOf(pageableAdapterConfig.getOutParamTemplateType().itemValue()),
						pageableAdapterConfig.getOutParamTemplate()
				)
		).orElse(null);
	}

	private BigDatasourceApiCommandValidateConfig commandValidateConfig(DataQueryDatasourceApiInParamValidateConfig dataQueryDatasourceApiInParamValidateConfig) {
		if (dataQueryDatasourceApiInParamValidateConfig != null) {
			BigDatasourceApiCommandValidateConfig bigDatasourceApiCommandValidateConfig = BigDatasourceApiCommandValidateConfig.create();

			for (DataQueryDatasourceApiInParamValidateConfig.ApiValidateItem inParamValidateItem : dataQueryDatasourceApiInParamValidateConfig.getInParamValidateItems()) {
				BigDatasourceApiCommandValidateConfig.ValidateItem validateItem = BigDatasourceApiCommandValidateConfig.ValidateItem.create(
						inParamValidateItem.getName(),
						ParamValidateType.valueOf(inParamValidateItem.getType().itemValue()),
						inParamValidateItem.getContentTemplate(),
						inParamValidateItem.getErrorMessage()
				);
				bigDatasourceApiCommandValidateConfig.addValidateItem(validateItem);
			}
			return bigDatasourceApiCommandValidateConfig;
		}
		return null;
	}

	private BigDatasourceApiSuccessValidateConfig successValidateConfig(DataQueryDatasourceApiInSuccessValidateConfig dataQueryDatasourceApiInSuccessValidateConfig) {
		if (dataQueryDatasourceApiInSuccessValidateConfig != null) {
			BigDatasourceApiSuccessValidateConfig bigDatasourceApiSuccessValidateConfig = BigDatasourceApiSuccessValidateConfig.create();

			for (DataQueryDatasourceApiInSuccessValidateConfig.ApiValidateItem outParamValidateItem : dataQueryDatasourceApiInSuccessValidateConfig.getOutParamValidateItems()) {
				BigDatasourceApiSuccessValidateConfig.ValidateItem validateItem = BigDatasourceApiSuccessValidateConfig.ValidateItem.create(
						outParamValidateItem.getName(),
						ParamValidateType.valueOf(outParamValidateItem.getType().itemValue()),
						outParamValidateItem.getContentTemplate(),
						outParamValidateItem.getErrorMessage()
				);
				bigDatasourceApiSuccessValidateConfig.addValidateItem(validateItem);
			}
			return bigDatasourceApiSuccessValidateConfig;
		}
		return null;
	}


	/**
	 * 翻译配置
	 * @param dataQueryDatasourceApiTransConfig
	 * @return
	 */
	private BigDatasourceApiTransConfig transConfig(DataQueryDatasourceApiTransConfig dataQueryDatasourceApiTransConfig) {
		if (dataQueryDatasourceApiTransConfig == null) {
			return null;
		}
		List<DataQueryDatasourceApiTransConfig.TransItem> configTransItems = dataQueryDatasourceApiTransConfig.getTransItems();
		if (CollectionUtil.isEmpty(configTransItems)) {
			return null;
		}
		List<BigDatasourceApiTransConfig.TransItem> transItemList = configTransItems.stream().map(item -> BigDatasourceApiTransConfig.TransItem.create(
				item.getType(),
				item.getByFieldName(),
				item.getForFieldName(),
				item.getMapValueField(),
				item.getIsMapValueCollectionJoin(),
				item.getMapValueCollectionJoinSeparator(),
				item.getIsByFieldValueGroup(),
				item.getByFieldValueGroupSeparator(),
				item.getMapFieldValueGroupSeparator(),
				item.getIsBatchOnly(),
				item.getIsNotTransWhenExist(),
				item.getMapKeyField(),
				item.getDicGroupCode()
		)).collect(Collectors.toList());

		return BigDatasourceApiTransConfig.create(transItemList);

	}

	/**
	 * 字典配置
	 * @param dataQueryDatasourceApiDictConfig
	 * @return
	 */
	private List<DictGroup> dictGroups(DataQueryDatasourceApiDictConfig dataQueryDatasourceApiDictConfig) {
		if (dataQueryDatasourceApiDictConfig == null) {
			return null;
		}
		List<DataQueryDatasourceApiDictConfig.DictItem> configDictItems = dataQueryDatasourceApiDictConfig.getDictItems();
		if (CollectionUtil.isEmpty(configDictItems)) {
			return null;
		}
		return configDictItems.stream().map(item -> DictGroup.create(
				item.getId(),
				item.getName(),
				item.getValue(),
				CollectionUtil.isEmpty(item.getChildren()) ? null : item.getChildren().stream().map(
								item1 ->
										DictItem.create(item1.getId(), item1.getName(), item1.getValue(), null)
						)
						.collect(Collectors.toList())
		)).collect(Collectors.toList());
	}
}
