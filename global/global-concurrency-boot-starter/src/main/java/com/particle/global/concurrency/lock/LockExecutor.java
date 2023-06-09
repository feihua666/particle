package com.particle.global.concurrency.lock;

import java.util.function.Supplier;

/**
 * <p>
 * 带有锁的执行器，抽象一层可以实现不同的锁方式，而不改变使用逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 16:48
 */
public interface LockExecutor {

	/**
	 * 执行，使用默认配置
	 * @param supplier
	 * @param lockKey
	 * @param <T>
	 * @return
	 */
	public <T> T execute(Supplier<T> supplier, String lockKey);

	/**
	 * 执行，使用自定义配置
	 * @param supplier
	 * @param lockExecuteConfig
	 * @param <T>
	 * @return
	 */
	public <T> T execute(Supplier<T> supplier, LockExecuteConfig lockExecuteConfig);
}
