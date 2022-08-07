package com.particle.global.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 自定义拒绝策略基类，所有自定义的业务逻辑拒绝策略应该继承此类，方便扩展
 * </p>
 *
 * @author yangwei
 * @since 2021-06-13 19:45
 */
@Slf4j
public class CustomBaseRejectedExecutionHandler implements RejectedExecutionHandler {
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

		log.error("拒绝策略被触发,{}",executor.toString());
	}
}
