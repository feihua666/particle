package com.particle.global.big.datasource;

import com.particle.global.big.datasource.bigdatasource.creator.BigDatasourceCreator;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingFallback;
import com.particle.global.big.datasource.bigdatasource.dynamic.impl.DefaultDynamicBigDatasourceRouterImpl;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.DynamicBigDatasourceProperties;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.DynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.YmlDynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.executor.DefaultBigDatasourceApiExecutorExeCacheImpl;
import com.particle.global.big.datasource.bigdatasource.executor.IBigDatasourceApiExecutorExeCache;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.feign.BigDatasourceFeignClientBuilder;
import com.particle.global.big.datasource.bigdatasource.trans.DefaultBigDatasourceApiTransSupportServiceImpl;
import com.particle.global.big.datasource.bigdatasource.trans.IBigDatasourceApiTransSupportService;
import com.particle.global.cache.CacheHelper;
import com.particle.global.cache.GlobalCacheAutoConfiguration;
import com.particle.global.tool.spring.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

/**
 * <p>
 * 全局大数据源基础配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 18:58
 */
@DependsOn(SpringContextHolder.springContextHolderName)
@Configuration
// 确保在cache之后配置，有 cacheHelper 依赖关系
@AutoConfigureAfter(GlobalCacheAutoConfiguration.class)
@EnableConfigurationProperties(DynamicBigDatasourceProperties.class)
@ConditionalOnProperty(prefix = DynamicBigDatasourceProperties.prefix, name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalBigDatasourceAutoConfiguration {

	@Autowired(required = false)
	private List<DynamicBigDatasourceRoutingFallback> routingFallbackList;

	@Bean
	@ConditionalOnMissingBean
	public IBigDatasourceApiTransSupportService bigDatasourceApiTransSupportService() {
		DefaultBigDatasourceApiTransSupportServiceImpl defaultBigDatasourceApiTransSupportService = new DefaultBigDatasourceApiTransSupportServiceImpl();
		return defaultBigDatasourceApiTransSupportService;
	}

	/**
	 * 默认的动态多大数据源
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DynamicBigDatasource dynamicBigDatasource(List<DynamicBigDatasourceProvider> providers){
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.setProviders(providers);
		DefaultDynamicBigDatasourceRouterImpl defaultDynamicBigDatasourceRouter = new DefaultDynamicBigDatasourceRouterImpl(dynamicBigDatasource.getBigDatasourceMap(), routingFallbackList);
		dynamicBigDatasource.setDynamicBigDatasourceRouter(defaultDynamicBigDatasourceRouter);
		return dynamicBigDatasource;
	}

	@Bean
	@ConditionalOnMissingBean
	public YmlDynamicBigDatasourceProvider ymlDynamicBigDatasourceProvider(DynamicBigDatasourceProperties dynamicBigDatasourceProperties,List<BigDatasourceCreator> bigDatasourceCreatorList){
		YmlDynamicBigDatasourceProvider ymlDynamicBigDatasourceProvider = new YmlDynamicBigDatasourceProvider();
		ymlDynamicBigDatasourceProvider.setBigDatasourcePropertyMap(dynamicBigDatasourceProperties.getBigDatasource());
		ymlDynamicBigDatasourceProvider.setBigDatasourceCreatorList(bigDatasourceCreatorList);
		return ymlDynamicBigDatasourceProvider;
	}
	@Bean
	public BigDatasourceFeignClientBuilder bigDatasourceFeignClientBuilder(){
		return new BigDatasourceFeignClientBuilder();
	}

	/**
	 * 缓存配置
	 */
	@Configuration
	@ConditionalOnClass(CacheHelper.class)
	static class BigDatasourceApiExecutorExeCacheConfig{
		@Bean
		@ConditionalOnBean(CacheHelper.class)
		public IBigDatasourceApiExecutorExeCache bigDatasourceApiExecutorExeCache(){
			return new DefaultBigDatasourceApiExecutorExeCacheImpl();
		}
	}

}
