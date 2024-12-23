package com.particle.global.datasource;

import com.particle.global.datasource.sqlinit.CustomDataSourceInitializationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 全局数据源基础配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 18:58
 */
@Configuration(proxyBeanMethods = false)
@Import(CustomDataSourceInitializationConfiguration.class)
public class GlobalDatasourceAutoConfiguration {
}
