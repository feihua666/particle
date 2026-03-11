package com.particle.component.autoconfigure.global.exceptionhandler.oplog;

import com.particle.global.exception.handle.GlobalMvcExceptionListener;
import com.particle.oplog.adapter.feign.client.error.rpc.OpLogErrorRpcFeignClient;
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
public class GlobalExceptionhandlerOpLogConfig {

    /**
     * 依赖操作异常日志组件配置
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({OpLogErrorRpcFeignClient.class, GlobalMvcExceptionListener.class})
    public static class OpLogErrorDependConfig {

        /**
         * 操作日志持久化实现
         * @return
         */
        @Bean
        @ConditionalOnBean(OpLogErrorRpcFeignClient.class)
        public GlobalMvcExceptionListenerImpl globalMvcExceptionListenerImpl(){
            return new GlobalMvcExceptionListenerImpl();
        }
    }
}
