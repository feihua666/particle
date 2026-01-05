package com.particle.component.autoconfigure.oplog;

import com.particle.oplog.adapter.feign.client.rpc.OpLogRpcFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 操作日志组件配置
 * </p>
 *
 * @author yangwei
 * @since 2025/12/27 16:17
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(OpLogRpcFeignClient.class)
public class OpLogConfig {

    /**
     * 操作日志持久化实现
     * @return
     */
    @Bean
    @ConditionalOnBean(OpLogRpcFeignClient.class)
    public OpLogRepositoryImpl opLogRepositoryImpl(){
        return new OpLogRepositoryImpl();
    }
}
