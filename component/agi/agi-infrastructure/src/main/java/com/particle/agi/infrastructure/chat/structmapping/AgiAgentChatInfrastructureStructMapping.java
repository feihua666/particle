package com.particle.agi.infrastructure.chat.structmapping;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.agi.domain.chat.AgiAgentChat;
import com.particle.agi.domain.chat.AgiAgentChatId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体对话 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatInfrastructureStructMapping {
	public static AgiAgentChatInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentChatInfrastructureStructMapping.class );

	protected AgiAgentChatId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentChatId.of(id);
	}
	protected Long map(AgiAgentChatId agiAgentChatId){
		if (agiAgentChatId == null) {
			return null;
		}
		return agiAgentChatId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentChatDO
	 * @return
	 */
	public abstract AgiAgentChat agiAgentChatDOToAgiAgentChat(@MappingTarget AgiAgentChat agiAgentChat,AgiAgentChatDO agiAgentChatDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatInfrastructureStructMapping#map(AgiAgentChatId)}
	 * @param agiAgentChat
	 * @return
	 */
	public abstract AgiAgentChatDO agiAgentChatToAgiAgentChatDO(AgiAgentChat agiAgentChat);

}
