package com.particle.data.infrastructure.dynamicdata.structmapping;

import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.domain.dynamicdata.DynamicDataCategory;
import com.particle.data.domain.dynamicdata.DynamicDataCategoryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据分类 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataCategoryInfrastructureStructMapping {
	public static DynamicDataCategoryInfrastructureStructMapping instance = Mappers.getMapper( DynamicDataCategoryInfrastructureStructMapping.class );

	protected DynamicDataCategoryId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicDataCategoryId.of(id);
	}
	protected Long map(DynamicDataCategoryId dynamicDataCategoryId){
		if (dynamicDataCategoryId == null) {
			return null;
		}
		return dynamicDataCategoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataCategoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicDataCategoryDO
	 * @return
	 */
	public abstract DynamicDataCategory dynamicDataCategoryDOToDynamicDataCategory(@MappingTarget DynamicDataCategory dynamicDataCategory,DynamicDataCategoryDO dynamicDataCategoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataCategoryInfrastructureStructMapping#map(DynamicDataCategoryId)}
	 * @param dynamicDataCategory
	 * @return
	 */
	public abstract DynamicDataCategoryDO dynamicDataCategoryToDynamicDataCategoryDO(DynamicDataCategory dynamicDataCategory);

}
