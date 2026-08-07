package com.particle.agi.infrastructure.model.structmapping;

import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.agi.domain.model.AgiModelProvider;
import com.particle.agi.domain.model.AgiModelProviderId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * AI模型提供商 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiModelProviderInfrastructureStructMapping {
	public static AgiModelProviderInfrastructureStructMapping instance = Mappers.getMapper( AgiModelProviderInfrastructureStructMapping.class );

	protected AgiModelProviderId map(Long id){
		if (id == null) {
			return null;
		}
		return AgiModelProviderId.of(id);
	}
	protected Long map(AgiModelProviderId agiModelProviderId){
		if (agiModelProviderId == null) {
			return null;
		}
		return agiModelProviderId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiModelProviderInfrastructureStructMapping#map(java.lang.Long)}
	 * @param agiModelProviderDO
	 * @return
	 */
	public abstract AgiModelProvider agiModelProviderDOToAgiModelProvider(@MappingTarget AgiModelProvider agiModelProvider,AgiModelProviderDO agiModelProviderDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiModelProviderInfrastructureStructMapping#map(AgiModelProviderId)}
	 * @param agiModelProvider
	 * @return
	 */
	public abstract AgiModelProviderDO agiModelProviderToAgiModelProviderDO(AgiModelProvider agiModelProvider);

}
