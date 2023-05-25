package com.particle.global.dto.messaging.event;

import cn.hutool.core.lang.UUID;
import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 消息基类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-16 11:30
 */
@Data
public class AbstractMessageEvent<T> extends DTO {

	/**
	 * 状态
	 */
	public enum Status{
		// 发送失败
		send_failed,
		// 后台发送，定时发送
		send_back,
		// 发送失败后，后台发送
		send_failed_back,
	}


	/**
	 * 标识发往哪个队列，在创建对象时指定
	 * 正常情况一条消息事件只能发往一个消息中间件
	 * 在 cloud-stream中应该是bindingName如：xxxx-out-0
	 * 在rabbit中应该是一个交换机名称
	 * 在kafka或rocketMq中应该是一个topic
	 */
	private String mq;

	/**
	 * 消费业务标识字符串，用来标识是什么消息
	 */
	private String identifier;

	/**
	 * 消息状态，消息机制自动维护，创建对象是无需设置该字段
	 * 应该和{@link Status} 枚举名称保持一致
	 */
	private String status;


	public AbstractMessageEvent(String identifier,T data, String mq) {
		this.identifier = identifier;
		this.data = data;
		this.mq = mq;
	}

	/**
	 * 消息id
	 */
	private String messageId = UUID.fastUUID().toString(true);
	/**
	 * 消息创建时间
	 */
	private LocalDateTime messageCreatedAt = LocalDateTime.now();

	/**
	 * 数据
	 */
	private T data;

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" + messageId + "]";
	}


	/**
	 * 填充默认值，主要用于新建一个新的事件对象
	 * @param newEvent 新 new 的对象
	 * @param originEvent 原来的对象
	 */
	public void fillDefault(AbstractMessageEvent newEvent,AbstractMessageEvent originEvent) {
		newEvent.setMessageId(originEvent.getMessageId());
		newEvent.setMessageCreatedAt(originEvent.getMessageCreatedAt());
	}

	/**
	 * 全部字典填充
	 * @param newEvent
	 * @param originEvent
	 */
	public void fillAll(AbstractMessageEvent newEvent,AbstractMessageEvent originEvent) {
		fillDefault(newEvent, originEvent);
		newEvent.setData(originEvent.getData());
		newEvent.setIdentifier(originEvent.getIdentifier());
		newEvent.setMq(originEvent.getMq());
	}


	/**
	 * 填充默认值，主要用于将另一个对象的默认值填充到本对象
	 * @param originEvent 原来的对象
	 */
	public void fillDefault(AbstractMessageEvent originEvent) {
		fillDefault(this,originEvent);
	}

	/**
	 * 全部字段填充
	 * @param originEvent
	 */
	public void fillAll(AbstractMessageEvent originEvent) {
		fillDefault(originEvent);
		fillAll(this,originEvent);
	}
}
