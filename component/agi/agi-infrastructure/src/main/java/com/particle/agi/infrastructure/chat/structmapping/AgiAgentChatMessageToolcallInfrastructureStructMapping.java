package com.particle.agi.infrastructure.chat.structmapping;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcall;
import com.particle.agi.domain.chat.AgiAgentChatMessageToolcallId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体对话消息工具调用 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageToolcallInfrastructureStructMapping {
	public static AgiAgentChatMessageToolcallInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentChatMessageToolcallInfrastructureStructMapping.class );

	protected AgiAgentChatMessageToolcallId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentChatMessageToolcallId.of(id);
	}
	protected Long map(AgiAgentChatMessageToolcallId agiAgentChatMessageToolcallId){
		if (agiAgentChatMessageToolcallId == null) {
			return null;
		}
		return agiAgentChatMessageToolcallId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolcallInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentChatMessageToolcallDO
	 * @return
	 */
	public abstract AgiAgentChatMessageToolcall agiAgentChatMessageToolcallDOToAgiAgentChatMessageToolcall(@MappingTarget AgiAgentChatMessageToolcall agiAgentChatMessageToolcall,AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageToolcallInfrastructureStructMapping#map(AgiAgentChatMessageToolcallId)}
	 * @param agiAgentChatMessageToolcall
	 * @return
	 */
	public abstract AgiAgentChatMessageToolcallDO agiAgentChatMessageToolcallToAgiAgentChatMessageToolcallDO(AgiAgentChatMessageToolcall agiAgentChatMessageToolcall);

}
