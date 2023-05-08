package com.particle.global.dataaudit;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.dataaudit.audit.*;
import com.particle.global.dataaudit.op.OpLogAspect;
import com.particle.global.tool.diff.DataAuditTool;
import com.particle.global.tool.id.SnowflakeIdTool;
import org.javers.core.Javers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 操作日志与数据审计自动配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 14:43
 */
@Configuration
@ConditionalOnProperty(prefix = "particle.dataaudit.audit", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableAspectJAutoProxy
public class DataAuditAuditAutoConfiguration {

	public static final String dataAuditTaskExecutor = "dataAuditTaskExecutor";

	public DataAuditAuditAutoConfiguration() {
		DataAuditCollectTool.setIsEnabled(true);
	}


	@Bean
	@ConditionalOnMissingBean(OpLogAspect.class)
	public OpLogAspect opLogAspect(SnowflakeIdTool snowflakeIdTool) {
		return new OpLogAspect(snowflakeIdTool);
	}

	/**
	 * 数据审计线程池，主要用于数据比对
	 *
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = dataAuditTaskExecutor, destroyMethod = "shutdown")
	public ExecutorService dataAuditTaskExecutor(BeanFactory beanFactory) {
		return CustomExecutors.newExecutorService(beanFactory,
				dataAuditTaskExecutor,
				5,
				100,
				1000,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				true);

	}
	/**
	 * 数据审计比较器
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public Javers javers() {
		return DataAuditTool.getJavers();
	}

	/**
	 * 数据审计器，主要用来做数据比对
	 *
	 * @param javers
	 * @return
	 */
	@Bean
	public DataAuditor dataAuditor(Javers javers) {
		return new DataAuditor(javers);
	}

	/**
	 * 对比对的结果处理器
	 *
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public IDataAuditResultHandler dataAuditResultHandler() {
		return new DefaultDataAuditResultHandler();
	}

	/**
	 * 数据审核事务事件监听，保证在记录审计日志时数据已提交，如果事务回滚或异常不记录审计日志
	 *
	 * @param dataAuditor
	 * @param dataAuditResultHandler
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataAuditTransactionalEventListener dataAuditTransactionalEventListener(DataAuditor dataAuditor,
																				   IDataAuditResultHandler dataAuditResultHandler) {
		return new DataAuditTransactionalEventListener(dataAuditor, dataAuditResultHandler);
	}

	@Bean
	public DataAuditApplicationEventPublisherAware dataAuditApplicationEventPublisherAware() {
		return new DataAuditApplicationEventPublisherAware();
	}
}
