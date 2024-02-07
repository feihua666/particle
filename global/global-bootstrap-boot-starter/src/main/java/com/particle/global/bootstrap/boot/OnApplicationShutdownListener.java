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
	 * 注意：不能使用 shutdown，因为使用shutdown可能会导致容器在关闭时自动调用 shutdown 方法，导致问题
	 * 参考：https://blog.csdn.net/Zong_0915/article/details/126440729
	 */
	public void appShutdown();
}
