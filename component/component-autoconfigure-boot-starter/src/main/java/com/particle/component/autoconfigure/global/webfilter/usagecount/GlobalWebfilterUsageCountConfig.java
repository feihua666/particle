package com.particle.component.autoconfigure.global.webfilter.usagecount;

import com.particle.global.web.filter.UsageCountFilter;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountRecordRpcFeignClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * global web filter 依赖使用次数 相关配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 20:02
 */
@Configuration(proxyBeanMethods = false)
public class GlobalWebfilterUsageCountConfig {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({UsageCountRecordRpcFeignClient.class,UsageCountFilter.UsageCountMarker.class})
    public static class UsageCountDependConfig{

        @Bean
        @ConditionalOnBean(UsageCountRecordRpcFeignClient.class)
        public UsageCountMarkerImpl usageCountMarker(){
            return new UsageCountMarkerImpl();
        }
    }
}
