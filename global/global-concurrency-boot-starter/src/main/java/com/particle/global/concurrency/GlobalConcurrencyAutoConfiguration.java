package com.particle.global.concurrency;

import com.particle.global.concurrency.config.GlobalConcurrencyExecutorsConfig;
import com.particle.global.concurrency.lock.distribute.DistributedShedLockConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 全局并发相关自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 11:29
 */
@Configuration
@Import({GlobalConcurrencyExecutorsConfig.class, DistributedShedLockConfiguration.class})
public class GlobalConcurrencyAutoConfiguration {
}
