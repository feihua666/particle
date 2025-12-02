package com.particle.data.infrastructure.dynamictable.structmapping;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据表格上传记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicTableUploadRecordInfrastructureStructMapping {
	public static DynamicTableUploadRecordInfrastructureStructMapping instance = Mappers.getMapper( DynamicTableUploadRecordInfrastructureStructMapping.class );

	protected DynamicTableUploadRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicTableUploadRecordId.of(id);
	}
	protected Long map(DynamicTableUploadRecordId dynamicTableUploadRecordId){
		if (dynamicTableUploadRecordId == null) {
			return null;
		}
		return dynamicTableUploadRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableUploadRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicTableUploadRecordDO
	 * @return
	 */
	public abstract DynamicTableUploadRecord dynamicTableUploadRecordDOToDynamicTableUploadRecord(@MappingTarget DynamicTableUploadRecord dynamicTableUploadRecord,DynamicTableUploadRecordDO dynamicTableUploadRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicTableUploadRecordInfrastructureStructMapping#map(DynamicTableUploadRecordId)}
	 * @param dynamicTableUploadRecord
	 * @return
	 */
	public abstract DynamicTableUploadRecordDO dynamicTableUploadRecordToDynamicTableUploadRecordDO(DynamicTableUploadRecord dynamicTableUploadRecord);

}
