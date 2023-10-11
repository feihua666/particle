package com.particle.global.big.datasource.bigdatasource.api;

import com.particle.global.big.datasource.bigdatasource.api.config.*;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 默认的大数据源接口信息
 * 注意：该类型实现不能是单例，否则导致数据错乱
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 19:15
 */
@Setter
public abstract class AbstractBigDatasourceApi implements BigDatasourceApi{

	protected String identifier;

	protected BigDatasourceApiContext apiContext;

	protected BigDatasourceApiResponseWrapType responseAssertType;
	protected IBigDatasourceApiConfig config;
	protected DynamicBigDatasourceRoutingKey routingKey;

	protected List<DictGroup> dictGroups;
	protected BigDatasourceApiCommandValidateConfig queryCommandConfig;
	protected BigDatasourceApiPageableAdapterConfig pageableAdapterConfig;
	protected BigDatasourceApiCommandValidateConfig commandValidateConfig;
	protected BigDatasourceApiSuccessValidateConfig successValidateConfig;

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public BigDatasourceApiContext apiContext() {
		if (apiContext == null) {
			apiContext = BigDatasourceApiContext.create();
		}
		return apiContext;
	}

	@Override
	public BigDatasourceApiResponseWrapType responseWrapType() {
		return responseAssertType;
	}

	@Override
	public IBigDatasourceApiConfig config() {
		return config;
	}
	@Override
	public DynamicBigDatasourceRoutingKey routingKey() {
		return routingKey;
	}

	@Override
	public List<DictGroup> dictGroups() {
		return dictGroups;
	}

	@Override
	public BigDatasourceApiCommandValidateConfig commandValidateConfig() {
		return queryCommandConfig;
	}

	@Override
	public PageableAdapterConfig pageableAdapterConfig() {
		return pageableAdapterConfig;
	}

	@Override
	public BigDatasourceApiSuccessValidateConfig successValidateConfig() {
		return successValidateConfig;
	}
}
