package com.particle.global.big.datasource.bigdatasource.dynamic.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <p>
 * 大数据源自动配置参数
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 20:33
 */
@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicBigDatasourceProperties.prefix)
public class DynamicBigDatasourceProperties {

	public static final String prefix = "particle.bigdatasource.dynamic";
	/**
	 * 大数据源配置
	 */
	private Map<String,BigDatasourceProperty> bigDatasource;
}
