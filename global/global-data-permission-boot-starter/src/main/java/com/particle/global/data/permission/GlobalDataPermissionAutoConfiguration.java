package com.particle.global.data.permission;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 18:56
 */
@Configuration(proxyBeanMethods = false)
public class GlobalDataPermissionAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public DataPermissionService dataPermissionEmptyService(){
        return new DataPermissionEmptyService();
    }
}
