package com.particle.global.notification.notify;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.bootstrap.boot.OnApplicationRunnerListener;
import org.springframework.boot.ApplicationArguments;

/**
 * <p>
 * 应用启动关闭通知监听
 * </p>
 *
 * @author yangwei
 * @since 2021-08-31 10:07
 */
public class ApplicationStartNotifyListener implements OnApplicationRunnerListener {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		NotifyParam notifyParam = NotifyParam.system()
				.setTitle("应用已启动")
				.setContentType("application.boot.start")
				.setContent(StrUtil.format("hostname={},host={}", NetUtil.getLocalHostName(),NetUtil.getLocalhostStr()));
		NotifyTool.notify(notifyParam);
	}
}
