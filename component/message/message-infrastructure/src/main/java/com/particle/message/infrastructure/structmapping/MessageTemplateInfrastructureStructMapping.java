package com.particle.message.infrastructure.structmapping;

import com.particle.message.domain.MessageTemplate;
import com.particle.message.domain.MessageTemplateId;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 消息模板 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MessageTemplateInfrastructureStructMapping {
	public static MessageTemplateInfrastructureStructMapping instance = Mappers.getMapper( MessageTemplateInfrastructureStructMapping.class );

	protected MessageTemplateId map(Long id){
		if (id == null) {
			return null;
		}
		return MessageTemplateId.of(id);
	}
	protected Long map(MessageTemplateId messageTemplateId){
		if (messageTemplateId == null) {
			return null;
		}
		return messageTemplateId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageTemplateInfrastructureStructMapping#map(java.lang.Long)}
	 * @param messageTemplateDO
	 * @return
	 */
	public abstract MessageTemplate messageTemplateDOToMessageTemplate(@MappingTarget MessageTemplate messageTemplate,MessageTemplateDO messageTemplateDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link MessageTemplateInfrastructureStructMapping#map(MessageTemplateId)}
	 * @param messageTemplate
	 * @return
	 */
	public abstract MessageTemplateDO messageTemplateToMessageTemplateDO(MessageTemplate messageTemplate);

}
