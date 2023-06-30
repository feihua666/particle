package com.particle.global.notification.notify;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.bootstrap.boot.OnApplicationShutdownListener;

/**
 * <p>
 * 应用启动关闭通知监听
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:07
 */
public class ApplicationShutdownNotifyListener implements OnApplicationShutdownListener {

	@Override
	public void shutdown() {
		NotifyParam notifyParam = NotifyParam.system()
				.setTitle("应用已停止")
				.setContentType("application.boot.shutdown")
				.setContent(StrUtil.format("hostname={},host={}", NetUtil.getLocalHostName(),NetUtil.getLocalhostStr()));

		NotifyTool.notify(notifyParam);
	}
}
