package com.particle.agi.infrastructure.chat.structmapping;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.agi.domain.chat.AgiAgentChatMessageTool;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体对话消息工具 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageToolInfrastructureStructMapping {
	public static AgiAgentChatMessageToolInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentChatMessageToolInfrastructureStructMapping.class );

	protected AgiAgentChatMessageToolId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentChatMessageToolId.of(id);
	}
	protected Long map(AgiAgentChatMessageToolId agiAgentChatMessageToolId){
		if (agiAgentChatMessageToolId == null) {
			return null;
		}
		return agiAgentChatMessageToolId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentChatMessageToolDO
	 * @return
	 */
	public abstract AgiAgentChatMessageTool agiAgentChatMessageToolDOToAgiAgentChatMessageTool(@MappingTarget AgiAgentChatMessageTool agiAgentChatMessageTool,AgiAgentChatMessageToolDO agiAgentChatMessageToolDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolInfrastructureStructMapping#map(AgiAgentChatMessageToolId)}
	 * @param agiAgentChatMessageTool
	 * @return
	 */
	public abstract AgiAgentChatMessageToolDO agiAgentChatMessageToolToAgiAgentChatMessageToolDO(AgiAgentChatMessageTool agiAgentChatMessageTool);

}
