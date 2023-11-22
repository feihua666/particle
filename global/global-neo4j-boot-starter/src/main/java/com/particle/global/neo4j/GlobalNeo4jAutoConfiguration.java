package com.particle.global.neo4j;

import com.particle.global.neo4j.dto.basic.INeo4jCurrentUserResolver;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局neo4j自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023/11/14 17:51
 */
@Configuration
public class GlobalNeo4jAutoConfiguration {



    /**
     * 配置当前登录用户解析id以在添加节点时填充使用
     */
    @Configuration
    @ConditionalOnClass({LoginUserTool.class, TenantTool.class})
    class Neo4jCurrentUserResolverConfig {

        @Bean
        INeo4jCurrentUserResolver neo4jCurrentUserResolver() {
            return new INeo4jCurrentUserResolver() {

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
}
