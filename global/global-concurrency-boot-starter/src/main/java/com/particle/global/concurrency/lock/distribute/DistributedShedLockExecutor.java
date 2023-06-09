package com.particle.global.concurrency.lock.distribute;

import com.particle.global.concurrency.lock.LockExecuteConfig;
import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.exception.biz.LockAlreadyOccupiedException;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SimpleLock;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * 分布式 shedlock 执行器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-19 13:18
 */
@Slf4j
public class DistributedShedLockExecutor implements LockExecutor {


	/**
	 * 锁提供商，可以自定义
	 */
	private final LockProvider lockProvider;

	public DistributedShedLockExecutor(LockProvider lockProvider) {
		this.lockProvider = lockProvider;
	}

	public <T> T execute(Supplier<T> supplier, LockConfiguration configuration) {
		Optional<SimpleLock> lock = lockProvider.lock(configuration);
		if (!lock.isPresent()) {
			log.warn("Failed to obtain lock {}.", configuration.getName());
			throw new LockAlreadyOccupiedException(configuration.getName());
		}

		log.debug("Obtained lock {}.", configuration.getName());

		try {
			log.debug("Run task inside lock {}.", configuration.getName());
			return supplier.get();
		} finally {
			lock.get().unlock();
			log.debug("Released lock {}.", configuration.getName());
		}
	}

	@Override
	public <T> T execute(Supplier<T> supplier, String lockKey) {
		LockConfiguration lockConfiguration = lockExecuteConfigConvert(LockExecuteConfig.create(lockKey, LockExecuteConfig.MAX_RUN_TIME.toMillis()));

		return this.execute(supplier, lockConfiguration);

	}

	@Override
	public <T> T execute(Supplier<T> supplier, LockExecuteConfig lockExecuteConfig) {

		LockConfiguration lockConfiguration = lockExecuteConfigConvert(lockExecuteConfig);

		return this.execute(supplier, lockConfiguration);
	}


	/**
	 * 对象转换
	 * @param lockExecuteConfig
	 * @return
	 */
	private LockConfiguration lockExecuteConfigConvert(LockExecuteConfig lockExecuteConfig) {
		Instant now = Instant.now();
		// 最小持有时间，这里默认为0
		LockConfiguration lockConfiguration = new LockConfiguration(now,
				lockExecuteConfig.getName(),
				Duration.between(now, now.plus(Duration.ofMillis(lockExecuteConfig.durationMust()))), Duration.between(now, now));;

		return lockConfiguration;
	}
}
