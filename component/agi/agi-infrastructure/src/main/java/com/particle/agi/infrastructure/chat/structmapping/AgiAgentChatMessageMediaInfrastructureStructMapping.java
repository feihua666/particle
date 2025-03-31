package com.particle.agi.infrastructure.chat.structmapping;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.agi.domain.chat.AgiAgentChatMessageMedia;
import com.particle.agi.domain.chat.AgiAgentChatMessageMediaId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 智能体对话消息媒体 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentChatMessageMediaInfrastructureStructMapping {
	public static AgiAgentChatMessageMediaInfrastructureStructMapping instance = Mappers.getMapper( AgiAgentChatMessageMediaInfrastructureStructMapping.class );

	protected AgiAgentChatMessageMediaId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAgentChatMessageMediaId.of(id);
	}
	protected Long map(AgiAgentChatMessageMediaId agiAgentChatMessageMediaId){
		if (agiAgentChatMessageMediaId == null) {
			return null;
		}
		return agiAgentChatMessageMediaId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageMediaInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAgentChatMessageMediaDO
	 * @return
	 */
	public abstract AgiAgentChatMessageMedia agiAgentChatMessageMediaDOToAgiAgentChatMessageMedia(@MappingTarget AgiAgentChatMessageMedia agiAgentChatMessageMedia,AgiAgentChatMessageMediaDO agiAgentChatMessageMediaDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentChatMessageMediaInfrastructureStructMapping#map(AgiAgentChatMessageMediaId)}
	 * @param agiAgentChatMessageMedia
	 * @return
	 */
	public abstract AgiAgentChatMessageMediaDO agiAgentChatMessageMediaToAgiAgentChatMessageMediaDO(AgiAgentChatMessageMedia agiAgentChatMessageMedia);

}
