package com.particle.lowcode.infrastructure.generator.structmapping;

import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 低代码模型 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LowcodeModelInfrastructureStructMapping {
	public static LowcodeModelInfrastructureStructMapping instance = Mappers.getMapper( LowcodeModelInfrastructureStructMapping.class );

	protected LowcodeModelId map(Long id){
		if (id == null) {
			return null;
		}
		return LowcodeModelId.of(id);
	}
	protected Long map(LowcodeModelId lowcodeModelId){
		if (lowcodeModelId == null) {
			return null;
		}
		return lowcodeModelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param lowcodeModelDO
	 * @return
	 */
	public abstract LowcodeModel lowcodeModelDOToLowcodeModel(@MappingTarget LowcodeModel lowcodeModel,LowcodeModelDO lowcodeModelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelInfrastructureStructMapping#map(LowcodeModelId)}
	 * @param lowcodeModel
	 * @return
	 */
	public abstract LowcodeModelDO lowcodeModelToLowcodeModelDO(LowcodeModel lowcodeModel);

}
