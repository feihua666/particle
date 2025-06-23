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

	protected String name;
	protected String identifier;

	protected BigDatasourceApiContext apiContext;

	protected BigDatasourceApiResponseWrapType responseAssertType;
	protected IBigDatasourceApiConfig config;
	protected DynamicBigDatasourceRoutingKey routingKey;

	protected BigDatasourceApiTransConfig transConfig;
	protected List<DictGroup> dictGroups;
	protected BigDatasourceApiPageableAdapterConfig pageableAdapterConfig;
	protected BigDatasourceApiCommandExtConfig commandExtConfig;
	protected BigDatasourceApiCommandValidateConfig commandValidateConfig;
	protected BigDatasourceApiResultExtConfig resultExtConfig;
	protected BigDatasourceApiSuccessValidateConfig successValidateConfig;
	protected BigDatasourceApiCacheConfig cacheConfig = BigDatasourceApiCacheConfig.memoryBigDatasourceApiCacheConfig;

	/**
	 * 使用缓存配置
	 */
	private Boolean isUseCache = false;

	/**
	 * 读取等待时间，单位ms，超过该时间将会放弃
	 */
	private Integer readTimeout = 3000;

	/**
	 * 连接等待时间，单位ms，超过该时间将会放弃
	 */
	private Integer connectTimeout = 1000;

	@Override
	public String name() {
		return name;
	}

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
	public BigDatasourceApiCommandExtConfig commandExtConfig() {
		return commandExtConfig;
	}

	@Override
	public BigDatasourceApiResultExtConfig resultExtConfig() {
		return resultExtConfig;
	}

	@Override
	public BigDatasourceApiTransConfig transConfig() {
		return transConfig;
	}
	@Override
	public List<DictGroup> dictGroups() {
		return dictGroups;
	}

	@Override
	public BigDatasourceApiCommandValidateConfig commandValidateConfig() {
		return commandValidateConfig;
	}

	@Override
	public PageableAdapterConfig pageableAdapterConfig() {
		return pageableAdapterConfig;
	}


	@Override
	public BigDatasourceApiSuccessValidateConfig successValidateConfig() {
		return successValidateConfig;
	}

	@Override
	public boolean useCache() {
		return isUseCache;
	}

	@Override
	public BigDatasourceApiCacheConfig cacheConfig() {
		return cacheConfig;
	}

	@Override
	public Integer connectTimeout() {
		return connectTimeout;
	}

	@Override
	public Integer readTimeout() {
		return readTimeout;
	}
}
