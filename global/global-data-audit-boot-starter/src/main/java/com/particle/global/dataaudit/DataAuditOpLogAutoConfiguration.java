package com.particle.global.dataaudit;

import com.particle.global.dataaudit.op.LoggingOpLogHandler;
import com.particle.global.dataaudit.op.OpLogAspect;
import com.particle.global.dataaudit.op.PersistentOpLogHandler;
import com.particle.global.tool.id.SnowflakeIdTool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * 操作日志自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 14:43
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "particle.dataaudit.oplog", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableAspectJAutoProxy
public class DataAuditOpLogAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(OpLogAspect.class)
	public OpLogAspect opLogAspect(SnowflakeIdTool snowflakeIdTool) {
		return new OpLogAspect(snowflakeIdTool);
	}

	/**
	 * 保证顺序在 {@link DataAuditOpLogAutoConfiguration#persistentOpLogHandler()} 之前，这样可以避免在持久化有异常时有日志打印输出
	 *
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE + 100)
	public LoggingOpLogHandler loggingOpLogHandler() {
		return new LoggingOpLogHandler();
	}

	/**
	 * 保证顺序在 {@link DataAuditOpLogAutoConfiguration#loggingOpLogHandler()} 之后，这样可以避免在持久化有异常时有日志打印输出
	 *
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE + 1000)
	public PersistentOpLogHandler persistentOpLogHandler() {
		return new PersistentOpLogHandler();
	}
}
