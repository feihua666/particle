 package com.particle.dataquery;


 import com.particle.dataquery.infrastructure.dataapi.gateway.impl.DefaultDataApiForOpenapiRemoteQueryGatewayImpl;
 import com.particle.dataquery.infrastructure.gateway.impl.DataQueryDoNotifyServiceImpl;
 import com.particle.dataquery.infrastructure.gateway.impl.IDataQueryDoNotifyService;
 import com.particle.dataquery.warmup.ApplicationStartDataQueryDataApiWarmUpLightListener;
 import com.particle.dataquery.warmup.ApplicationStartDataQueryDataApiWarmUpListener;
 import com.particle.dataquery.warmup.ApplicationStartDataQueryDatasourceApiWarmUpLightListener;
 import com.particle.dataquery.warmup.ApplicationStartDataQueryDatasourceWarmUpLightListener;
 import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
 import com.particle.global.light.share.constant.ClassAdapterConstants;
 import com.particle.global.openapi.api.GlobalOpenapiUrlPatternConfigure;
 import com.particle.global.projectinfo.ProjectInfo;
 import com.particle.global.swagger.ApplicationContexSwaggertHelper;
 import com.particle.global.swagger.SwaggerInfo;
 import com.particle.global.swagger.factory.SwaggerFactory;
 import io.swagger.v3.oas.models.security.SecurityScheme;
 import org.mybatis.spring.annotation.MapperScan;
 import org.springdoc.core.models.GroupedOpenApi;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
 import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.ComponentScan;
 import org.springframework.context.annotation.Configuration;

 import java.util.ArrayList;
 import java.util.List;

 /**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author yw
 * @since 2023-03-01 16:56:13
 */
@ComponentScan
@Configuration(proxyBeanMethods = false)
@MapperScan({
        "com.particle.dataquery.infrastructure.provider.mapper",
        "com.particle.dataquery.infrastructure.datasource.mapper",
        "com.particle.dataquery.infrastructure.dataapi.mapper",
})
public class DataqueryAutoConfiguration {


    /**
     * 后端管理文档
     * @param projectInfo 参数不能去，依赖projectInfo
     * @return
     */
    @ConditionalOnBean({ApplicationContexSwaggertHelper.class})
    @Bean
    public GroupedOpenApi createDataqueryAdminRestApi(ProjectInfo projectInfo) {
        List<SecurityScheme> parameters = new ArrayList<>();

        return SwaggerFactory.createRestApi(SwaggerInfo.builder()
                .groupName("data-query接口")
                .basePackage("com.particle.dataquery.adapter")
                .securitySchemes(parameters)
                .version(ProjectInfo.VERSION)
                .title(ProjectInfo.NAME + " Swagger Apis")
                .description(ProjectInfo.NAME + " Swagger Apis Description")
                .build());
    }
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({GlobalOpenapiUrlPatternConfigure.class})
    public static class OpenapiConfiguration{
        /**
         * 数据查询支持开放接口收集供应商数据
         * @return
         */
        @Bean
        public DataqueryExecutorInfrastructureListener dataqueryExecutorInfrastructureListener(){
            return new DataqueryExecutorInfrastructureListener();
        }

        /**
         * 数据查询支持开放接口远程调用
         * @return
         */
        @Bean
        public DefaultDataApiForOpenapiRemoteQueryGatewayImpl defaultDataApiForOpenapiRemoteQueryGateway() {
            return new DefaultDataApiForOpenapiRemoteQueryGatewayImpl();
        }
    }

     /**
      * 数据查询接口调用预热，建议使用经量级预热，因为该预热会产生实际
      */
    @ConditionalOnProperty(prefix = "com.particle.dataquery.api",name = "warm-up",havingValue = "true",matchIfMissing = false)
    @Configuration(proxyBeanMethods = false)
    public static class DataQueryApiWarmUpConfiguration{
        @Bean
        public OnApplicationRunnerListener applicationStartDataQueryDataApiWarmUpListener(){
            return new ApplicationStartDataQueryDataApiWarmUpListener();
        }
    }

     /**
      * 数据查询接口经量级预热
      */
     @ConditionalOnProperty(prefix = "com.particle.dataquery.api",name = "warm-up-light",havingValue = "true",matchIfMissing = true)
     @Configuration(proxyBeanMethods = false)
     public static class DataQueryApiWarmUpForLightConfiguration{
         @Bean
         public OnApplicationRunnerListener applicationStartDataQueryDataApiWarmUpLightListener(){
             return new ApplicationStartDataQueryDataApiWarmUpLightListener();
         }
     }
     /**
      * 数据源接口经量级预热
      */
     @ConditionalOnProperty(prefix = "com.particle.dataqueryDatasource.api",name = "warm-up-light",havingValue = "true",matchIfMissing = true)
     @Configuration(proxyBeanMethods = false)
     public static class DataQueryDatasourceApiWarmUpForLightConfiguration{
         @Bean
         public OnApplicationRunnerListener applicationStartDataQueryDatasourceApiWarmUpLightListener(){
             return new ApplicationStartDataQueryDatasourceApiWarmUpLightListener();
         }
     }
     /**
      * 数据源接口经量级预热
      */
     @ConditionalOnProperty(prefix = "com.particle.dataqueryDatasource",name = "warm-up-light",havingValue = "true",matchIfMissing = true)
     @Configuration(proxyBeanMethods = false)
     public static class DataQueryDatasourceWarmUpForLightConfiguration{
         @Bean
         public OnApplicationRunnerListener applicationStartDataQueryDatasourceWarmUpLightListener(){
             return new ApplicationStartDataQueryDatasourceWarmUpLightListener();
         }
     }
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)
    public static class NotifyConfiguration{

        @Bean
        public IDataQueryDoNotifyService dataQueryDoNotifyService(){
            return new DataQueryDoNotifyServiceImpl();
        }
    }
}
