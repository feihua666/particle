package com.particle.agi.infrastructure.chat.structmapping;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.agi.domain.chat.AgiAgentChatMessage;
import com.particle.agi.domain.chat.AgiAgentChatMessageId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体对话消息 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageInfrastructureStructMapping {
	public static AgiAgentChatMessageInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentChatMessageInfrastructureStructMapping.class );

	protected AgiAgentChatMessageId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentChatMessageId.of(id);
	}
	protected Long map(AgiAgentChatMessageId agiAgentChatMessageId){
		if (agiAgentChatMessageId == null) {
			return null;
		}
		return agiAgentChatMessageId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentChatMessageDO
	 * @return
	 */
	public abstract AgiAgentChatMessage agiAgentChatMessageDOToAgiAgentChatMessage(@MappingTarget AgiAgentChatMessage agiAgentChatMessage,AgiAgentChatMessageDO agiAgentChatMessageDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageInfrastructureStructMapping#map(AgiAgentChatMessageId)}
	 * @param agiAgentChatMessage
	 * @return
	 */
	public abstract AgiAgentChatMessageDO agiAgentChatMessageToAgiAgentChatMessageDO(AgiAgentChatMessage agiAgentChatMessage);

}
