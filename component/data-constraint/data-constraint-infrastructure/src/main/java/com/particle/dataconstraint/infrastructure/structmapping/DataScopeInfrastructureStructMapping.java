package com.particle.dataconstraint.infrastructure.structmapping;

import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.DataScopeId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据范围 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Mapper
public abstract class DataScopeInfrastructureStructMapping {
	public static DataScopeInfrastructureStructMapping instance = Mappers.getMapper( DataScopeInfrastructureStructMapping.class );

	protected DataScopeId map(Long id){
		if (id == null) {
			return null;
		}
		return DataScopeId.of(id);
	}
	protected Long map(DataScopeId dataScopeId){
		if (dataScopeId == null) {
			return null;
		}
		return dataScopeId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataScopeDO
	 * @return
	 */
	public abstract DataScope dataScopeDOToDataScope(@MappingTarget DataScope dataScope,DataScopeDO dataScopeDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeInfrastructureStructMapping#map(DataScopeId)}
	 * @param dataScope
	 * @return
	 */
	public abstract DataScopeDO dataScopeToDataScopeDO(DataScope dataScope);

}
