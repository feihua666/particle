package com.particle.global.concurrency.asynslot;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.springframework.util.Assert;

/**
 * <p>
 * 快速异步返回 slot
 * </p>
 *
 * @author yangwei
 * @since 2021-08-17 18:18
 */
public class ImmediateValueAsynSlot<R> extends AsynSlot<R> {

	private R value;


	public ImmediateValueAsynSlot(R value){
		super();
		Assert.notNull(value,"value 不能为空");
		this.value = value;
		setImmediate(true);
		setName(value.getClass().getName());
	}

	@Override
	protected ListenableFuture<R> evaluate() {
		return Futures.immediateFuture(value);
	}

	/**
	 * build
	 * @param value
	 * @param <T>
	 * @return
	 */
	public static <T> ImmediateValueAsynSlot<T> create(T value){
		return new ImmediateValueAsynSlot<T>(value);
	}
}
