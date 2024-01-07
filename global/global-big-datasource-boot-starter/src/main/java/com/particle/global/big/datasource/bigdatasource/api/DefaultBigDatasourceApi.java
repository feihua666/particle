package com.particle.global.big.datasource.bigdatasource.api;

import com.particle.global.big.datasource.bigdatasource.api.config.*;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 默认的大数据源接口信息
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 19:15
 */
@Setter
public class DefaultBigDatasourceApi extends AbstractBigDatasourceApi{


	public static DefaultBigDatasourceApi create(String identifier,BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config){
		DefaultBigDatasourceApi defaultBigDatasourceApi = new DefaultBigDatasourceApi();
		defaultBigDatasourceApi.setIdentifier(identifier);
		defaultBigDatasourceApi.setResponseAssertType(responseAssertType);
		defaultBigDatasourceApi.setConfig(config);

		return defaultBigDatasourceApi;
	}

	public static DefaultBigDatasourceApi create(String identifier,BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config,
												 DynamicBigDatasourceRoutingKey routingKey){
		DefaultBigDatasourceApi defaultBigDatasourceApi = create(identifier,responseAssertType, config);
		defaultBigDatasourceApi.setRoutingKey(routingKey);

		return defaultBigDatasourceApi;
	}
	public static DefaultBigDatasourceApi create(String identifier,BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config,
												 DynamicBigDatasourceRoutingKey routingKey,
												 BigDatasourceApiPageableAdapterConfig pageableAdapterConfig){
		DefaultBigDatasourceApi defaultBigDatasourceApi = create(identifier,responseAssertType, config,routingKey);
		defaultBigDatasourceApi.setPageableAdapterConfig(pageableAdapterConfig);

		return defaultBigDatasourceApi;
	}

	public static DefaultBigDatasourceApi create(String identifier,BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config,
												 DynamicBigDatasourceRoutingKey routingKey,
												 BigDatasourceApiPageableAdapterConfig pageableAdapterConfig,
												 BigDatasourceApiCommandValidateConfig commandValidateConfig){
		DefaultBigDatasourceApi defaultBigDatasourceApi = create(identifier,responseAssertType, config,routingKey,pageableAdapterConfig);
		defaultBigDatasourceApi.setCommandValidateConfig(commandValidateConfig);

		return defaultBigDatasourceApi;
	}

	public static DefaultBigDatasourceApi create(String identifier,BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config,
												 DynamicBigDatasourceRoutingKey routingKey,
												 BigDatasourceApiPageableAdapterConfig pageableAdapterConfig,
												 BigDatasourceApiCommandValidateConfig commandValidateConfig,
												 BigDatasourceApiSuccessValidateConfig successValidateConfig){
		DefaultBigDatasourceApi defaultBigDatasourceApi = create(identifier,responseAssertType, config,routingKey,pageableAdapterConfig,commandValidateConfig);
		defaultBigDatasourceApi.setSuccessValidateConfig(successValidateConfig);

		return defaultBigDatasourceApi;
	}
	public static DefaultBigDatasourceApi create(String identifier, BigDatasourceApiResponseWrapType responseAssertType,
												 IBigDatasourceApiConfig config,
												 DynamicBigDatasourceRoutingKey routingKey,
												 BigDatasourceApiPageableAdapterConfig pageableAdapterConfig,
												 BigDatasourceApiCommandValidateConfig commandValidateConfig,
												 BigDatasourceApiSuccessValidateConfig successValidateConfig,
												 BigDatasourceApiTransConfig transConfig,
												 List<DictGroup> dictGroups){
		DefaultBigDatasourceApi defaultBigDatasourceApi = create(identifier,responseAssertType, config,routingKey,pageableAdapterConfig,commandValidateConfig,successValidateConfig);
		defaultBigDatasourceApi.setTransConfig(transConfig);
		defaultBigDatasourceApi.setDictGroups(dictGroups);

		return defaultBigDatasourceApi;
	}
}
