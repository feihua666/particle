package com.particle.global.scheduler;

import com.particle.global.concurrency.lock.LockExecuteConfig;
import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.exception.biz.LockAlreadyOccupiedException;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

/**
 * <p>
 * 提供方便带锁的执行
 * </p>
 *
 * @author yangwei
 * @since 2023-07-20 18:06
 */
@Slf4j
public abstract class AbstractGlobalScheduler implements IGlobalScheduler{

	protected LockExecutor lockExecutor;


	/**
	 * 执行，使用默认配置
	 * @param supplier
	 * @param lockKey
	 * @param <T>
	 * @return
	 */
	public <T> T execute(Supplier<T> supplier, String lockKey){
		try {
			return lockExecutor.execute(supplier, lockKey);
		} catch (LockAlreadyOccupiedException e) {
			log.debug("LockAlreadyOccupied by lockKey for key={}. ignored!",lockKey);
			return null;
		}

	}


	/**
	 * 执行，使用自定义配置
	 * @param supplier
	 * @param lockExecuteConfig
	 * @param <T>
	 * @return
	 */
	public <T> T execute(Supplier<T> supplier, LockExecuteConfig lockExecuteConfig) {
		try {
			return lockExecutor.execute(supplier, lockExecuteConfig);
		} catch (LockAlreadyOccupiedException e) {
			log.debug("LockAlreadyOccupied by lockExecuteConfig for config={}. ignored!", JsonTool.toJsonStr(lockExecuteConfig));
			return null;
		}
	}

	@Autowired
	public void setLockExecutor(LockExecutor lockExecutor) {
		this.lockExecutor = lockExecutor;
	}
}
