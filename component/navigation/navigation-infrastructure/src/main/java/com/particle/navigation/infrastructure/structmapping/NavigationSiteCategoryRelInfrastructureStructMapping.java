package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;
import com.particle.navigation.domain.NavigationSiteCategoryRel;
import com.particle.navigation.domain.NavigationSiteCategoryRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航网站分类关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Mapper
public abstract class NavigationSiteCategoryRelInfrastructureStructMapping {
	public static NavigationSiteCategoryRelInfrastructureStructMapping instance = Mappers.getMapper( NavigationSiteCategoryRelInfrastructureStructMapping.class );

	protected NavigationSiteCategoryRelId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationSiteCategoryRelId.of(id);
	}
	protected Long map(NavigationSiteCategoryRelId navigationSiteCategoryRelId){
		if (navigationSiteCategoryRelId == null) {
			return null;
		}
		return navigationSiteCategoryRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteCategoryRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationSiteCategoryRelDO
	 * @return
	 */
	public abstract NavigationSiteCategoryRel navigationSiteCategoryRelDOToNavigationSiteCategoryRel(@MappingTarget NavigationSiteCategoryRel navigationSiteCategoryRel,NavigationSiteCategoryRelDO navigationSiteCategoryRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteCategoryRelInfrastructureStructMapping#map(NavigationSiteCategoryRelId)}
	 * @param navigationSiteCategoryRel
	 * @return
	 */
	public abstract NavigationSiteCategoryRelDO navigationSiteCategoryRelToNavigationSiteCategoryRelDO(NavigationSiteCategoryRel navigationSiteCategoryRel);

}
