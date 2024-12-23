package com.particle.global.cache;

import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 全局缓存配置,开启缓存
 * JCache一般不使用，在不使用JCache情况下，默认缓存配置是 {@link ProxyCachingConfiguration}，确保在其后创建
 * </p>
 *
 * @author yangwei
 * @since 2023/10/8 16:54
 */
@Configuration(proxyBeanMethods = false)
// 经尝试，该注解必须添加到启动类上，否则启动报错
// @EnableCaching
@ComponentScan
public class GlobalCacheAutoConfiguration {
    /**
     * 缓存接口文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createGlobalCacheRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("cache接口")
                .basePackage("com.particle.global.cache.endpoint")
                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
    /**
     * 缓存帮助类，可用来清除简单的缓存
     * @return
     */
    @Bean
    public CacheHelper cacheHelper(){
        return new CacheHelper();
    }
}
