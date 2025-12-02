package com.particle.data.infrastructure.dynamicdata.structmapping;

import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据指标分类上传记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping {
	public static DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping.class );

	protected DynamicDataIndicatorCategoryUploadRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicDataIndicatorCategoryUploadRecordId.of(id);
	}
	protected Long map(DynamicDataIndicatorCategoryUploadRecordId dynamicDataIndicatorCategoryUploadRecordId){
		if (dynamicDataIndicatorCategoryUploadRecordId == null) {
			return null;
		}
		return dynamicDataIndicatorCategoryUploadRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicDataIndicatorCategoryUploadRecordDO
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecordDOToDynamicDataIndicatorCategoryUploadRecord(@MappingTarget DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord,DynamicDataIndicatorCategoryUploadRecordDO dynamicDataIndicatorCategoryUploadRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryUploadRecordInfrastructureStructMapping#map(DynamicDataIndicatorCategoryUploadRecordId)}
	 * @param dynamicDataIndicatorCategoryUploadRecord
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryUploadRecordDO dynamicDataIndicatorCategoryUploadRecordToDynamicDataIndicatorCategoryUploadRecordDO(DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord);

}
