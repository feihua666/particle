package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.domain.NavigationCategory;
import com.particle.navigation.domain.NavigationCategoryId;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航分类 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationCategoryInfrastructureStructMapping {
	public static NavigationCategoryInfrastructureStructMapping instance = Mappers.getMapper( NavigationCategoryInfrastructureStructMapping.class );

	protected NavigationCategoryId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationCategoryId.of(id);
	}
	protected Long map(NavigationCategoryId navigationCategoryId){
		if (navigationCategoryId == null) {
			return null;
		}
		return navigationCategoryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationCategoryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationCategoryDO
	 * @return
	 */
	public abstract NavigationCategory navigationCategoryDOToNavigationCategory(@MappingTarget NavigationCategory navigationCategory,NavigationCategoryDO navigationCategoryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationCategoryInfrastructureStructMapping#map(NavigationCategoryId)}
	 * @param navigationCategory
	 * @return
	 */
	public abstract NavigationCategoryDO navigationCategoryToNavigationCategoryDO(NavigationCategory navigationCategory);

}
