package com.particle.global.concurrency.lock.distribute;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.inmemory.InMemoryLockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * <p>
 * 分布式 shedlock 执行器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-19 13:18
 */
@Import(DistributedShedLockConfiguration.JdbcLockProviderConfiguration.class)
@Slf4j
@ConditionalOnClass(LockConfiguration.class)
@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class DistributedShedLockConfiguration {

    /**
     * 默认内存级别，意义不大
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public LockProvider memoryLockProvider() {
        log.warn("init InMemoryLockProvider instance. if you use distributed shedlock config yourself please.");
        return new InMemoryLockProvider();
    }
    @Bean
    public DistributedShedLockExecutor distributedShedLockExecutor(LockProvider lockProvider) {
        return new DistributedShedLockExecutor(lockProvider);
    }

    @ConditionalOnClass(JdbcTemplateLockProvider.class)
    static class JdbcLockProviderConfiguration{

        /**
         * 如果需要数据库，需要放开这里
         * @param dataSource
         * @return
         */
        @Primary
        @Bean
        @ConditionalOnBean(DataSource.class)
        public LockProvider jdbcLockProvider(DataSource dataSource) {
            log.warn("init JdbcTemplateLockProvider instance.");
            return new JdbcTemplateLockProvider(
                    JdbcTemplateLockProvider.Configuration.builder()
                            .withJdbcTemplate(new JdbcTemplate(dataSource))
                            .withTableName("global_concurrency_shedlock")
                            .build()
            );
        }
    }

}
