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
	 * 默认不走消息中间件，走本地消息监听，适用于本地单机模式
	 */
	public static String defaultLocalListenerSyncVirtualMq = "defaultLocalListenerSyncVirtualMq";

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
	private String mq = defaultLocalListenerSyncVirtualMq;

	/**
	 * 消费业务标识字符串，用来标识是什么消息
	 */
	private String identifier;

	/**
	 * 消息状态，消息机制自动维护，创建对象是无需设置该字段
	 * 应该和{@link Status} 枚举名称保持一致
	 */
	private String status;



	public AbstractMessageEvent(String identifier,T data) {
		this.identifier = identifier;
		this.data = data;
	}

	public AbstractMessageEvent(String identifier,T data, String mq) {
		this(identifier, data);
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

}
