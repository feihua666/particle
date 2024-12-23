package com.particle.dataconstraint.infrastructure.structmapping;

import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.DataScopeCustomDataRelId;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据范围自定义数据关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataScopeCustomDataRelInfrastructureStructMapping {
	public static DataScopeCustomDataRelInfrastructureStructMapping instance = Mappers.getMapper( DataScopeCustomDataRelInfrastructureStructMapping.class );

	protected DataScopeCustomDataRelId map(Long id){
		if (id == null) {
			return null;
		}
		return DataScopeCustomDataRelId.of(id);
	}
	protected Long map(DataScopeCustomDataRelId dataScopeCustomDataRelId){
		if (dataScopeCustomDataRelId == null) {
			return null;
		}
		return dataScopeCustomDataRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeCustomDataRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataScopeCustomDataRelDO
	 * @return
	 */
	public abstract DataScopeCustomDataRel dataScopeCustomDataRelDOToDataScopeCustomDataRel(@MappingTarget DataScopeCustomDataRel dataScopeCustomDataRel,DataScopeCustomDataRelDO dataScopeCustomDataRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeCustomDataRelInfrastructureStructMapping#map(DataScopeCustomDataRelId)}
	 * @param dataScopeCustomDataRel
	 * @return
	 */
	public abstract DataScopeCustomDataRelDO dataScopeCustomDataRelToDataScopeCustomDataRelDO(DataScopeCustomDataRel dataScopeCustomDataRel);

}
