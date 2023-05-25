package com.particle.message.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 消息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
public class MessageId extends Id {

	public MessageId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 消息 领域模型id
	 * @param id
	 * @return
	 */
	public static MessageId of(Long id){
		return new MessageId(id);
	}
}
