package com.particle.message.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 消息模板 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
public class MessageTemplateId extends Id {

	public MessageTemplateId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 消息模板 领域模型id
	 * @param id
	 * @return
	 */
	public static MessageTemplateId of(Long id){
		return new MessageTemplateId(id);
	}
}
