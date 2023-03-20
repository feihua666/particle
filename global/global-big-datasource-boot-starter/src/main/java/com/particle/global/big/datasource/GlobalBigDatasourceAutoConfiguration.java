package com.particle.global.big.datasource;

import com.particle.global.big.datasource.bigdatasource.creator.BigDatasourceCreator;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.properties.DynamicBigDatasourceProperties;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.DynamicBigDatasourceProvider;
import com.particle.global.big.datasource.bigdatasource.dynamic.provider.YmlDynamicBigDatasourceProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 * 全局大数据源基础配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 18:58
 */
@Configuration
@EnableConfigurationProperties(DynamicBigDatasourceProperties.class)
@ConditionalOnProperty(prefix = DynamicBigDatasourceProperties.prefix, name = "enabled", havingValue = "true", matchIfMissing = true)
public class GlobalBigDatasourceAutoConfiguration {

	/**DynamicBigDatasourceProperties dynamicBigDatasourceProperties
	 * 默认的动态多大数据源
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DynamicBigDatasource dynamicBigDatasource(List<DynamicBigDatasourceProvider> providers){
		DynamicBigDatasource dynamicBigDatasource = new DynamicBigDatasource();
		dynamicBigDatasource.setProviders(providers);
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
}
