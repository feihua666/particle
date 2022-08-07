package com.particle.global.concurrency.threadpool;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
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

		if (ClassLoaderUtil.isPresent("com.particle.global.notification.notify.NotifyTool")) {
			NotifyParam notifyParam = NotifyParam.system();
			notifyParam.setContentType("thread.pool.uncaught.exception");
			notifyParam.setTitle("线程池线程异常");
			notifyParam.setSuggest("您可能需要自行捕获该异常，来完善业务逻辑");
			notifyParam.setContent(ExceptionUtil.stacktraceToString(e));
			NotifyTool.notify(notifyParam);
		}

	}
}
