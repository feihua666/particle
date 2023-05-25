package com.particle.message.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户消息读取状态 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
public class MessageUserStateId extends Id {

	public MessageUserStateId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户消息读取状态 领域模型id
	 * @param id
	 * @return
	 */
	public static MessageUserStateId of(Long id){
		return new MessageUserStateId(id);
	}
}
