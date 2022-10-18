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
	 * 标识发往哪个队列
	 * 在 cloud-stream中应该是bindingName如：xxxx-out-0
	 * 在rabbit中应该是一个交换机名称
	 * 在kafka或rocketMq中应该是一个topic
	 */
	private String mq;

	/**
	 * 消息状态
	 */
	private String status;



	public AbstractMessageEvent() {
	}

	public AbstractMessageEvent(String mq) {
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
