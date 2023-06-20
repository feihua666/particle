package com.particle.global.concurrency.test;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.particle.global.concurrency.threadpool.CustomDefaultUncaughtExceptionHandler;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.tool.thread.ThreadContextTool;

import java.util.concurrent.*;

/**
 * <p>
 * 测试，完善
 * </p>
 *
 * @author yangwei
 * @since 2023-06-20 13:04
 */
public class TtlExecutorTest {
	public static void main(String[] args) {
		testExecutorService();
		testSchedulerExecutorService();
	}

	public static void testExecutorService() {

		// 线程工厂
		ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("testExecutorService").setUncaughtExceptionHandler(new CustomDefaultUncaughtExceptionHandler()).build();

		ExecutorService executorService = CustomExecutors.newThreadPoolExecutor(threadFactory,

				1,
				1,
				1000 * 60,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false,false);

		executorService = TtlExecutors.getTtlExecutorService(executorService);

		String threadKey = "testExecutorServiceKey";
		ThreadContextTool.put(threadKey,threadKey);
		executorService.execute(()->{
			System.out.println(threadKey + " = " + ThreadContextTool.get(threadKey));
		});
		String threadKey1 = "testExecutorServiceKey1";
		ThreadContextTool.put(threadKey1 ,threadKey1);
		executorService.execute(()->{
			System.out.println(threadKey1 + " = " + ThreadContextTool.get(threadKey1));
		});
		executorService.shutdown();
	}



	public static void testSchedulerExecutorService() {

		// 线程工厂
		ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("testSchedulerExecutorService").setUncaughtExceptionHandler(new CustomDefaultUncaughtExceptionHandler()).build();

		ScheduledExecutorService executorService = (ScheduledExecutorService)CustomExecutors.newThreadPoolExecutor(threadFactory,

				1,
				1,
				1000 * 60,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				true,false);

		//executorService =  TtlExecutors.getTtlScheduledExecutorService(((ScheduledExecutorService) executorService));

		String threadKey = "testSchedulerExecutorServiceKey";
		ThreadContextTool.put(threadKey,threadKey);
		executorService.scheduleAtFixedRate(()->{
			System.out.println(threadKey + " = " + ThreadContextTool.get(threadKey));
		},4,2,TimeUnit.SECONDS);

		String threadKey1 = "testSchedulerExecutorServiceKey1";
		ThreadContextTool.remove(threadKey);
		ThreadContextTool.put(threadKey1 ,threadKey1);
		executorService.scheduleAtFixedRate(()->{
			System.out.println(threadKey1 + " = " + ThreadContextTool.get(threadKey1));
			System.out.println(threadKey + " = " + ThreadContextTool.get(threadKey));
		},2,2,TimeUnit.SECONDS);
		//executorService.shutdown();
	}
}
