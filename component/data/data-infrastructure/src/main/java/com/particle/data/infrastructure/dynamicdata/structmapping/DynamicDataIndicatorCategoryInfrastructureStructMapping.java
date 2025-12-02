package com.particle.data.infrastructure.dynamicdata.structmapping;

import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategory;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 动态数据指标分类 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DynamicDataIndicatorCategoryInfrastructureStructMapping {
	public static DynamicDataIndicatorCategoryInfrastructureStructMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryInfrastructureStructMapping.class );

	protected DynamicDataIndicatorCategoryId map(Long id){
		if (id == null) {
			return null;
		}
		return DynamicDataIndicatorCategoryId.of(id);
	}
	protected Long map(DynamicDataIndicatorCategoryId dynamicDataIndicatorCategoryId){
		if (dynamicDataIndicatorCategoryId == null) {
			return null;
		}
		return dynamicDataIndicatorCategoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param dynamicDataIndicatorCategoryDO
	 * @return
	 */
	public abstract DynamicDataIndicatorCategory dynamicDataIndicatorCategoryDOToDynamicDataIndicatorCategory(@MappingTarget DynamicDataIndicatorCategory dynamicDataIndicatorCategory,DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DynamicDataIndicatorCategoryInfrastructureStructMapping#map(DynamicDataIndicatorCategoryId)}
	 * @param dynamicDataIndicatorCategory
	 * @return
	 */
	public abstract DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryToDynamicDataIndicatorCategoryDO(DynamicDataIndicatorCategory dynamicDataIndicatorCategory);

}
