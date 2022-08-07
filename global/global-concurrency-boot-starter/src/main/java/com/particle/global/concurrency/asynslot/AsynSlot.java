package com.particle.global.concurrency.asynslot;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.particle.global.actuator.monitor.MonitorTool;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.global.tool.state.StateDepend;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.google.common.util.concurrent.Uninterruptibles.getUninterruptibly;

/**
 * <p>
 * 异步执行槽位，类似于 twitter storm中的bolt
 * </p>
 * Bolt的概念来源于Storm， 一个Bolt类似一个插槽，在整个执行过程中，代表一段业务处理逻辑。
 * slot 执行时通过递归来发现其依赖的slot，当其依赖的slot完成时，才会真正执行 {@link AsynSlot#evaluate()}
 * 业务代码需要实现{@link AsynSlot#evaluate()}并提供异步返回结果。
 * <p>
 * {@link AsynSlot#sinkAsynSlots} 表示当前Bolt执行完后，发送通知即可。不需要等待{@link AsynSlot#sinkAsynSlots}执行结果。
 * 比如用来发送非可靠的消息通知
 * <p>
 * {@link AsynSlot#getValue()} 方法的调用可能阻塞，并且直到{@link AsynSlot#evaluate()}返回的Future就绪或者失败，才会继续执行
 * <p>
 * 实现 {@link AsynSlot#evaluate()} 时，可以通过 {@link AsynSlot#getDependValue(String)} 来获取依赖值。这个方法会抛业务出异常，跟
 * 同步调用类似。需要自行处理。在{@link AsynSlot#evaluate()} 中调用{@link AsynSlot#getDependValue(String)} 不会阻塞。因为evaluate方法中是在其依赖执行完成后才执行的，
 * 所以早已经有值了
 * <p>
 * <p>
 * 如果 {@link AsynSlot#evaluate()} 是一个耗时的操作，可以单独设置 {@link AsynSlot#executor} 一个线程池
 * @author yangwei
 * @since 2021-08-16 19:39
 */
@Slf4j
public abstract class AsynSlot<R> {
	/**
	 * 标识 evaluate 方法是否已执行
	 */
	private AtomicBoolean evaluated = new AtomicBoolean();
	/**
	 * execute 的返回结果
	 */
	private final SettableFuture<R> future = SettableFuture.create();

	/**
	 * evaluate方法耗时通知阈值
	 */
	private long evaluateNotifyThreshold = 400;
	/**
	 * execute方法耗时通知阈值
	 */
	private long executeNotifyThreshold = 1000;


	/**
	 * 当前 slot 的执行开始时间戳
	 */
	private long startAt;
	/**
	 * evaluate 执行开始时间戳
	 */
	private long evaluateStartAt;
	/**
	 * evaluate 执行结束时间戳
	 */
	private long evaluateStopAt;
	/**
	 * 当前 slot 的执行结束时间戳
	 */
	private long stopAt;


	/**
	 * 通知的槽位，这严格上来说已经不是当前槽位图里的依赖东西了
	 */
	@Setter
	private List<AsynSlot<?>> sinkAsynSlots;

	/**
	 * 依赖的其它 slot
	 */
	@Setter
	@Getter
	private StateDepend<String, AsynSlot<?>> dependencies;
	/**
	 * 当前 slot 名称
	 */
	@Getter
	@Setter
	private String name;
	/**
	 * 是否是直接值，立即返回的不需要耗时处理的
	 */
	@Setter
	private boolean immediate;

	/**
	 * 异步执行线程池
	 */
	@Setter
	private Executor executor;


	public AsynSlot(){
		// 默认的名称取类名
		this.name = getClass().getName();

		// 线程池，默认
		this.executor = SpringContextHolder.getBean("asynSlotTaskExecutor");

	}


	/**
	 * 外部调用的执行方法，发起执行
	 * @return
	 */
	public final ListenableFuture<R> execute() {
		if (!evaluated.compareAndSet(false, true)) {
			log.warn("当前 {} 槽位已执行，直接返回",name);
			return future;
		}

		startAt = System.currentTimeMillis();

		// 执行依赖槽位
		List<ListenableFuture<?>> listenableFutures = executeDepends();
		ListenableFuture<?> listenableFuture = null;

		if (CollectionUtils.isEmpty(listenableFutures)) {
			// 如果依赖为空就单独执行自己
			listenableFuture = Futures.immediateFuture("no dependences value");
		}else {
			// 依赖不为空，将所有的依赖打包成一个
			listenableFuture = Futures.allAsList(listenableFutures);
		}

		// 所有依执行完成回调开始执行本slot
		Futures.addCallback(listenableFuture, new FutureCallback() {
			@Override
			public void onSuccess(Object result) {

				evaluateStartAt = System.currentTimeMillis();
				// 前置方法
				preEvaluate();

				// 不要返回 null
				// 参考  Futures.immediateFuture(xxx);
				final ListenableFuture<R> val = evaluate();
				// 添加回调监听
				Futures.addCallback(val, new FutureCallback<R>() {
					@Override
					public void onSuccess(R result) {
						future.set(result);
						// 通知其它槽位
						applySinkAsynSlots();
					}

					@Override
					public void onFailure(Throwable t) {
						future.setException(t);
					}
				},executor);

				// 后置方法
				postEvaluate(val);

				// 记录执行结束时间
				evaluateStopAt = System.currentTimeMillis();

				long evaluateDuration = evaluateStopAt - evaluateStartAt;
				log.info("asynSlot {} evaluate complete cost {} ms", name, evaluateDuration);

				stopAt = System.currentTimeMillis();

				long executeDuration = stopAt - startAt;

				log.info("asynSlot {} execute complete cost {} ms", name, executeDuration);

				// 监控
				// 立即返回的不需要监控，因为其没有意义
				if (!immediate) {
					String evaluateKey = "asynSlot.evaluate";
					String executeKey = "asynSlot.execute";
					MonitorTool.timer(evaluateKey,evaluateDuration,evaluateKey + "耗时监控",name);
					MonitorTool.timer(executeKey,evaluateDuration,executeKey + "耗时监控",name);
				}
				if(evaluateDuration > evaluateNotifyThreshold){
					NotifyParam notifyParam = NotifyParam.system();
					notifyParam.setContentType("asynSlot.duration");
					notifyParam.setTitle("evaluate 执行时间超过阈值");
					notifyParam.setContent(StrUtil.format("evaluate 执行时间{}ms超过阈值{}ms,name={}",evaluateDuration,evaluateNotifyThreshold,getName()));
					NotifyTool.notify(notifyParam);
				}
				if (executeDuration > executeNotifyThreshold) {
					NotifyParam notifyParam = NotifyParam.system();
					notifyParam.setContentType("asynSlot.duration");
					notifyParam.setTitle("execute 执行时间超过阈值");
					notifyParam.setContent(StrUtil.format("execute 执行时间{}ms超过阈值{}ms,name={}",executeDuration,executeNotifyThreshold,getName()));
					NotifyTool.notify(notifyParam);
				}

			}

			@Override
			public void onFailure(Throwable t) {
				future.setException(t);
				stopAt = System.currentTimeMillis();
			}
		}, this.executor);


		return future;
	}

	/**
	 * 执行依赖项
	 * @return
	 */
	private List<ListenableFuture<?>> executeDepends(){

		if (dependencies == null) {
			log.info("依赖状态机为空，没有依赖 slot 可执行");
			return null;
		}
		List<AsynSlot<?>> allDepend = dependencies.getAllDepend();
		if (CollectionUtils.isEmpty(allDepend)) {
			log.info("依赖状态机获取所有依赖为空，没有依赖 slot 可执行");
			return null;
		}
		List<ListenableFuture<?>> result = new ArrayList<>(allDepend.size());
		for (AsynSlot<?> asynSlot : allDepend) {
			ListenableFuture<?> execute = asynSlot.execute();
			result.add(execute);
		}
		return result;

	}


	/**
	 * 获取依赖的值
	 * @param key
	 * @param <T>
	 * @return 查个依赖的执行结果
	 */
	public <T> T getDependValue(String key) throws Exception {
		if (dependencies == null) {
			return null;
		}
		AsynSlot<?> depend = dependencies.getDepend(key);
		if (depend == null) {
			log.warn("获取依赖值为空，给定的key={}",key);
			return null;
		}

		return (T) depend.getValue();
	}

	/**
	 * 获取依赖值
	 * @param key
	 * @param timeout
	 * @param unit
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public <T> T getDependValue(String key,long timeout, TimeUnit unit) throws Exception {
		if (dependencies == null) {
			return null;
		}
		AsynSlot<?> depend = dependencies.getDepend(key);
		if (depend == null) {
			log.warn("获取依赖值为空，给定的key={}",key);
			return null;
		}

		return (T) depend.getValue(timeout, unit);
	}
	/**
	 * 获取执行的结果
	 * 可能会被阻塞，直到有值
	 * @return
	 * @throws Exception
	 */
	public final R getValue() throws Exception {
		try {
			return getUninterruptibly(future);
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof Error) {
				throw new RuntimeException(cause);
			}
			throw (Exception) e.getCause();
		}
	}


	/**
	 * 获取执行结果
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	public final R getValue(long timeout, TimeUnit unit) throws Exception {
		try {
			return getUninterruptibly(future, timeout, unit);
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof Error) {
				throw new RuntimeException(cause);
			}
			throw (Exception) e.getCause();
		}
	}

	/**
	 * 添加依赖
	 * @param key
	 * @param slot
	 */
	public final void addDepend(String key,AsynSlot<?> slot){

		if (evaluated.get()) {
			throw new IllegalStateException("asynSlot已经执行，不能再添加");
		}

		if(dependencies == null){
			dependencies = new StateDepend<>();
		}
		dependencies.addDepend(key,slot);
	}

	/**
	 * 通知其它依赖，自己执行完成
	 */
	private void applySinkAsynSlots() {
		if (sinkAsynSlots == null) {
			log.info("sinkAsynSlots 为空直接返回");
			return;
		}
		for (AsynSlot node : sinkAsynSlots) {
			Futures.addCallback(node.execute(), new FutureCallback() {
				@Override
				public void onSuccess(Object result) {
					log.error("sinkAsynSlot={} evaluated success.",node.name);
				}

				@Override
				public void onFailure(Throwable t) {
					log.error("sinkAsynSlot={} evaluated failed.",node.name, t);
				}
			},node.executor);
		}
	}

	/**
	 * 添加待通知的槽位
	 * @param slot
	 */
	public void addSinkAsynSlot(AsynSlot<?> slot){
		if (sinkAsynSlots == null) {
			sinkAsynSlots = new ArrayList<>();
		}
		sinkAsynSlots.add(slot);
	}


	private String dependString(){
		String lineSeparator = FileUtil.getLineSeparator();
		if (dependencies == null) {
			return null;
		}
		List<String> result = new ArrayList<>();
		for (AsynSlot<?> asynSlot : dependencies.getAllDepend()) {
			result.add(getName() + "->" + asynSlot.getName());
			String sub = asynSlot.dependString();
			if (!StrUtil.isEmpty(sub)) {
				result.add(sub);
			}
		}
		return result.stream().collect(Collectors.joining(lineSeparator));
	}
	/**
	 * 依赖有向图
	 * 获取的字符串可以在这里查看图 https://plantuml.com/zh/
	 * @return
	 */
	public String dependDigraphString(){
		String lineSeparator = FileUtil.getLineSeparator();

		String digraph = StrUtil.format("digraph G{{}{}{}}",lineSeparator,dependString(),lineSeparator);
		return digraph;
	}


	/**
	 * evaluate 执行前调用
	 */
	protected void preEvaluate(){}

	/**
	 * evaluate
	 */
	protected abstract ListenableFuture<R> evaluate();


	/**
	 * evaluate 执行后调用
	 */
	protected void postEvaluate(ListenableFuture<R> result){}

}
