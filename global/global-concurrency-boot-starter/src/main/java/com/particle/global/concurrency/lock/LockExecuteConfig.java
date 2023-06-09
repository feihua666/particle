package com.particle.global.concurrency.lock;

import lombok.Data;

import java.time.Duration;

/**
 * <p>
 * 带有锁的执行配置
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 16:50
 */
@Data
public class LockExecuteConfig {
	/**
	 * 最大锁定时间，默认5分钟
	 */
	public static final Duration MAX_RUN_TIME = Duration.ofMinutes(5);


	public LockExecuteConfig(String name) {
		this.name = name;
	}
	/**
	 * name 必须存在值
	 * 锁唯一值，可以认为量个 lock key
	 */
	private String name;

	/**
	 * 锁时长，单位 ms，默认为空，如果有看门狗的实现为空可以实现自动续期
	 */
	private Long duration;

	public static LockExecuteConfig create(String name, Long duration) {
		LockExecuteConfig lockExecuteConfig = new LockExecuteConfig(name);
		lockExecuteConfig.setDuration(duration);
		return lockExecuteConfig;
	}

	/**
	 * 获取持续时长
	 * @return
	 */
	public long durationMust() {
		if (duration != null) {
			return duration;
		}
		return MAX_RUN_TIME.toMillis();
	}
}
