package com.particle.message.infrastructure.structmapping;

import com.particle.message.infrastructure.dos.MessageUserStateDO;
import com.particle.message.domain.MessageUserState;
import com.particle.message.domain.MessageUserStateId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 用户消息读取状态 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
@Mapper
public abstract class MessageUserStateInfrastructureStructMapping {
	public static MessageUserStateInfrastructureStructMapping instance = Mappers.getMapper( MessageUserStateInfrastructureStructMapping.class );

	protected MessageUserStateId map(Long id){
		if (id == null) {
			return null;
		}
		return MessageUserStateId.of(id);
	}
	protected Long map(MessageUserStateId messageUserStateId){
		if (messageUserStateId == null) {
			return null;
		}
		return messageUserStateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageUserStateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param messageUserStateDO
	 * @return
	 */
	public abstract MessageUserState messageUserStateDOToMessageUserState(@MappingTarget MessageUserState messageUserState,MessageUserStateDO messageUserStateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageUserStateInfrastructureStructMapping#map(MessageUserStateId)}
	 * @param messageUserState
	 * @return
	 */
	public abstract MessageUserStateDO messageUserStateToMessageUserStateDO(MessageUserState messageUserState);

}
