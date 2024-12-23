package com.particle.dataconstraint.infrastructure.structmapping;

import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 数据对象 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataObjectInfrastructureStructMapping {
	public static DataObjectInfrastructureStructMapping instance = Mappers.getMapper( DataObjectInfrastructureStructMapping.class );

	protected DataObjectId map(Long id){
		if (id == null) {
			return null;
		}
		return DataObjectId.of(id);
	}
	protected Long map(DataObjectId dataObjectId){
		if (dataObjectId == null) {
			return null;
		}
		return dataObjectId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataObjectInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dataObjectDO
	 * @return
	 */
	public abstract DataObject dataObjectDOToDataObject(@MappingTarget DataObject dataObject,DataObjectDO dataObjectDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataObjectInfrastructureStructMapping#map(DataObjectId)}
	 * @param dataObject
	 * @return
	 */
	public abstract DataObjectDO dataObjectToDataObjectDO(DataObject dataObject);

}
