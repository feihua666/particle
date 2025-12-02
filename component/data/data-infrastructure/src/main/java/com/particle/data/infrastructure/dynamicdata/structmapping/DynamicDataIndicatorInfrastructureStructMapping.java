package com.particle.data.infrastructure.dynamicdata.structmapping;

import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据指标 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorInfrastructureStructMapping {
	public static DynamicDataIndicatorInfrastructureStructMapping instance = Mappers.getMapper( DynamicDataIndicatorInfrastructureStructMapping.class );

	protected DynamicDataIndicatorId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicDataIndicatorId.of(id);
	}
	protected Long map(DynamicDataIndicatorId dynamicDataIndicatorId){
		if (dynamicDataIndicatorId == null) {
			return null;
		}
		return dynamicDataIndicatorId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicDataIndicatorDO
	 * @return
	 */
	public abstract DynamicDataIndicator dynamicDataIndicatorDOToDynamicDataIndicator(@MappingTarget DynamicDataIndicator dynamicDataIndicator,DynamicDataIndicatorDO dynamicDataIndicatorDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorInfrastructureStructMapping#map(DynamicDataIndicatorId)}
	 * @param dynamicDataIndicator
	 * @return
	 */
	public abstract DynamicDataIndicatorDO dynamicDataIndicatorToDynamicDataIndicatorDO(DynamicDataIndicator dynamicDataIndicator);

}
