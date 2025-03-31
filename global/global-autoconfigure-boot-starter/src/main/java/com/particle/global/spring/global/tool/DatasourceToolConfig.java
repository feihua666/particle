package com.particle.global.spring.global.tool;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.particle.global.tool.datasource.DatasourceService;
import com.particle.global.tool.datasource.DatasourceTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2025/1/2 11:24
 */
@ConditionalOnClass({DatasourceTool.class, DynamicDataSourceContextHolder.class})
@Configuration(proxyBeanMethods = false)
public class DatasourceToolConfig {

    @Bean
    public DatasourceService datasourceService(){
        return new DynamicDatasourceServiceImpl();
    }
}
