package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.domain.NavigationSiteTagRel;
import com.particle.navigation.domain.NavigationSiteTagRelId;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航网站标签关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteTagRelInfrastructureStructMapping {
	public static NavigationSiteTagRelInfrastructureStructMapping instance = Mappers.getMapper( NavigationSiteTagRelInfrastructureStructMapping.class );

	protected NavigationSiteTagRelId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationSiteTagRelId.of(id);
	}
	protected Long map(NavigationSiteTagRelId navigationSiteTagRelId){
		if (navigationSiteTagRelId == null) {
			return null;
		}
		return navigationSiteTagRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationSiteTagRelDO
	 * @return
	 */
	public abstract NavigationSiteTagRel navigationSiteTagRelDOToNavigationSiteTagRel(@MappingTarget NavigationSiteTagRel navigationSiteTagRel,NavigationSiteTagRelDO navigationSiteTagRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagRelInfrastructureStructMapping#map(NavigationSiteTagRelId)}
	 * @param navigationSiteTagRel
	 * @return
	 */
	public abstract NavigationSiteTagRelDO navigationSiteTagRelToNavigationSiteTagRelDO(NavigationSiteTagRel navigationSiteTagRel);

}
