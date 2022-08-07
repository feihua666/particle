package com.particle.global.notification.notify;

/**
 * <p>
 * 通知监听，主要是于在common组件中发起的遇到的问题通知或报警，
 * 比如运行时异常（需要结合errorListener使用）或其它可能用到的地方
 * 可以有多个实例
 * </p>
 *
 * @author yangwei
 * @since 2021-08-13 09:37
 */
public interface INotifyListener {

	/**
	 * 发起通知
	 * @param notifyParam
	 */
	void notify(NotifyParam notifyParam);
}
