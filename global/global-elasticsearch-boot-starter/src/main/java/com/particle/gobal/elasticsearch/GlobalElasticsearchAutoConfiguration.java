package com.particle.gobal.elasticsearch;

import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import com.particle.gobal.elasticsearch.dto.basic.IElasticsearchCurrentUserResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;

/**
 * <p>
 * 全局 elasticsearch 自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023/12/7 12:43
 */
@Configuration
@ComponentScan
public class GlobalElasticsearchAutoConfiguration {


    /**
     * 配置当前登录用户解析id以在添加节点时填充使用
     */
    @Configuration
    @ConditionalOnClass({LoginUserTool.class, TenantTool.class})
    class ElasticsearchCurrentUserResolverConfig {

        @Bean
        IElasticsearchCurrentUserResolver elasticsearchCurrentUserResolver() {
            return new IElasticsearchCurrentUserResolver() {

                @Override
                public Long currentUserId() {
                    return LoginUserTool.getLoginUserId();
                }

                @Override
                public Long currentTenantId() {
                    return TenantTool.getTenantId();
                }
            };
        }
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate(org.elasticsearch.client.RestHighLevelClient client,
                                                    ElasticsearchConverter converter) {
        return new ElasticsearchRestTemplate(client, converter);
    }
}
