package com.particle.global.notification.notify;

import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 日志打印通知
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 16:14
 */
@Slf4j
public class LogNotifyListener extends AbstractNotifyListener{

	@Override
	protected boolean support(NotifyParam param) {
		return true;
	}

	@Override
	protected String supportType() {
		throw new RuntimeException("日志通知支持类型，该方法不会执行");
	}

	@Override
	public void doNotify(NotifyParam notifyParam) {

		log.info("log-notify:notifyParam = {}", JsonTool.toJsonStr(notifyParam));

	}
}
