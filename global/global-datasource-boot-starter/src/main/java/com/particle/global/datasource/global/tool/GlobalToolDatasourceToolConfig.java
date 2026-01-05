package com.particle.global.datasource.global.tool;

import com.particle.global.tool.datasource.DatasourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局数据源切换工具配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/26 09:10
 */
@Configuration(proxyBeanMethods = false)
public class GlobalToolDatasourceToolConfig {

    @Bean
    public DatasourceService datasourceService(){
        return new DynamicDatasourceServiceImpl();
    }
}
