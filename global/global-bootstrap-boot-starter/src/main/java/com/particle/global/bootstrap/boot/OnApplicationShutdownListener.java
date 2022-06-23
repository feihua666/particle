package com.particle.global.bootstrap.boot;

/**
 * <p>
 * 自定义应用关闭调用监听
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:00
 */
public interface OnApplicationShutdownListener {
	/**
	 * 应用关闭调用
	 */
	public void shutdown();
}
