package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.domain.NavigationSiteTag;
import com.particle.navigation.domain.NavigationSiteTagId;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航网站标签 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteTagInfrastructureStructMapping {
	public static NavigationSiteTagInfrastructureStructMapping instance = Mappers.getMapper( NavigationSiteTagInfrastructureStructMapping.class );

	protected NavigationSiteTagId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationSiteTagId.of(id);
	}
	protected Long map(NavigationSiteTagId navigationSiteTagId){
		if (navigationSiteTagId == null) {
			return null;
		}
		return navigationSiteTagId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationSiteTagDO
	 * @return
	 */
	public abstract NavigationSiteTag navigationSiteTagDOToNavigationSiteTag(@MappingTarget NavigationSiteTag navigationSiteTag,NavigationSiteTagDO navigationSiteTagDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteTagInfrastructureStructMapping#map(NavigationSiteTagId)}
	 * @param navigationSiteTag
	 * @return
	 */
	public abstract NavigationSiteTagDO navigationSiteTagToNavigationSiteTagDO(NavigationSiteTag navigationSiteTag);

}
