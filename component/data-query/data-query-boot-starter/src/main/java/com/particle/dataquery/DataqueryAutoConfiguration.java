 package com.particle.dataquery;


import com.google.common.collect.Sets;
import com.particle.dataquery.adapter.dataapi.api.DataQueryDataApiController;
import com.particle.dataquery.infrastructure.dataapi.gateway.impl.DefaultDataApiForOpenapiRemoteQueryGatewayImpl;
import com.particle.dataquery.infrastructure.datasource.gateway.impl.DatasourceApiQueryGatewayHelper;
import com.particle.global.big.datasource.bigdatasource.api.BigDatasourceApi;
import com.particle.global.big.datasource.bigdatasource.executor.ExecutorInfrastructureListener;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.HttpClientInfrastructureListener;
import com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.BigDatasourceHttpJoddClientImpl;
import com.particle.global.openapi.api.GlobalOpenapiUrlPatternConfigure;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;
import com.particle.global.projectinfo.ProjectInfo;
import com.particle.global.swagger.ApplicationContexSwaggertHelper;
import com.particle.global.swagger.SwaggerInfo;
import com.particle.global.swagger.factory.SwaggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

 /**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author yw
 * @since 2023-03-01 16:56:13
 */
@ComponentScan
@Configuration
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
    @Configuration
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
}
