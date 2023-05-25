package com.particle.message.infrastructure.structmapping;

import com.particle.message.infrastructure.dos.MessageDO;
import com.particle.message.domain.Message;
import com.particle.message.domain.MessageId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 消息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Mapper
public abstract class MessageInfrastructureStructMapping {
	public static MessageInfrastructureStructMapping instance = Mappers.getMapper( MessageInfrastructureStructMapping.class );

	protected MessageId map(Long id){
		if (id == null) {
			return null;
		}
		return MessageId.of(id);
	}
	protected Long map(MessageId messageId){
		if (messageId == null) {
			return null;
		}
		return messageId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageInfrastructureStructMapping#map(java.lang.Long)}
	 * @param messageDO
	 * @return
	 */
	public abstract Message messageDOToMessage(@MappingTarget Message message,MessageDO messageDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageInfrastructureStructMapping#map(MessageId)}
	 * @param message
	 * @return
	 */
	public abstract MessageDO messageToMessageDO(Message message);

}
