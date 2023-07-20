package com.particle.global.scheduler;

import com.particle.global.concurrency.lock.LockExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

/**
 * <p>
 * 提供方便带锁的执行
 * </p>
 *
 * @author yangwei
 * @since 2023-07-20 18:06
 */
public abstract class AbstractGlobalScheduler implements IGlobalScheduler{

	protected LockExecutor lockExecutor;

	@Autowired
	public void setLockExecutor(LockExecutor lockExecutor) {
		this.lockExecutor = lockExecutor;
	}
}
