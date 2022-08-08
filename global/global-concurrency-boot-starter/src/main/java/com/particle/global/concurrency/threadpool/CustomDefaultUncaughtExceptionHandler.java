package com.particle.global.concurrency.threadpool;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2021-10-21 15:50
 */
@Slf4j
public class CustomDefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {

		log.error("异步线程执行异常，theadName={}",t.getName(),e);

		if (ClassLoaderUtil.isPresent(ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)) {
			com.particle.global.notification.notify.NotifyParam notifyParam = com.particle.global.notification.notify.NotifyParam.system();
			notifyParam.setContentType("thread.pool.uncaught.exception");
			notifyParam.setTitle("线程池线程异常");
			notifyParam.setSuggest("您可能需要自行捕获该异常，来完善业务逻辑");
			notifyParam.setContent(ExceptionUtil.stacktraceToString(e));
			com.particle.global.notification.notify.NotifyTool.notify(notifyParam);
		}

	}
}
