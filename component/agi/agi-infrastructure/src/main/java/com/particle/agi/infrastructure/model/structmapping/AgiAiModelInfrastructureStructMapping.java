package com.particle.agi.infrastructure.model.structmapping;

import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.AgiAiModelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * AI模型 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAiModelInfrastructureStructMapping {
	public static AgiAiModelInfrastructureStructMapping instance = Mappers.getMapper( AgiAiModelInfrastructureStructMapping.class );

	protected AgiAiModelId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiAiModelId.of(id);
	}
	protected Long map(AgiAiModelId agiAiModelId){
		if (agiAiModelId == null) {
			return null;
		}
		return agiAiModelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAiModelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiAiModelDO
	 * @return
	 */
	public abstract AgiAiModel agiAiModelDOToAgiAiModel(@MappingTarget AgiAiModel agiAiModel,AgiAiModelDO agiAiModelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAiModelInfrastructureStructMapping#map(AgiAiModelId)}
	 * @param agiAiModel
	 * @return
	 */
	public abstract AgiAiModelDO agiAiModelToAgiAiModelDO(AgiAiModel agiAiModel);

}
