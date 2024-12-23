package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.domain.NavigationSite;
import com.particle.navigation.domain.NavigationSiteId;
import com.particle.navigation.infrastructure.dos.NavigationSiteDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航网站 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class NavigationSiteInfrastructureStructMapping {
	public static NavigationSiteInfrastructureStructMapping instance = Mappers.getMapper( NavigationSiteInfrastructureStructMapping.class );

	protected NavigationSiteId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationSiteId.of(id);
	}
	protected Long map(NavigationSiteId navigationSiteId){
		if (navigationSiteId == null) {
			return null;
		}
		return navigationSiteId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationSiteDO
	 * @return
	 */
	public abstract NavigationSite navigationSiteDOToNavigationSite(@MappingTarget NavigationSite navigationSite,NavigationSiteDO navigationSiteDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSiteInfrastructureStructMapping#map(NavigationSiteId)}
	 * @param navigationSite
	 * @return
	 */
	public abstract NavigationSiteDO navigationSiteToNavigationSiteDO(NavigationSite navigationSite);

}
