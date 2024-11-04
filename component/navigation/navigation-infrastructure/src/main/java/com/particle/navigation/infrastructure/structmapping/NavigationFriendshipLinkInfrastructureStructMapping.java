package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;
import com.particle.navigation.domain.NavigationFriendshipLink;
import com.particle.navigation.domain.NavigationFriendshipLinkId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航友情链接 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Mapper
public abstract class NavigationFriendshipLinkInfrastructureStructMapping {
	public static NavigationFriendshipLinkInfrastructureStructMapping instance = Mappers.getMapper( NavigationFriendshipLinkInfrastructureStructMapping.class );

	protected NavigationFriendshipLinkId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationFriendshipLinkId.of(id);
	}
	protected Long map(NavigationFriendshipLinkId navigationFriendshipLinkId){
		if (navigationFriendshipLinkId == null) {
			return null;
		}
		return navigationFriendshipLinkId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationFriendshipLinkInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationFriendshipLinkDO
	 * @return
	 */
	public abstract NavigationFriendshipLink navigationFriendshipLinkDOToNavigationFriendshipLink(@MappingTarget NavigationFriendshipLink navigationFriendshipLink,NavigationFriendshipLinkDO navigationFriendshipLinkDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationFriendshipLinkInfrastructureStructMapping#map(NavigationFriendshipLinkId)}
	 * @param navigationFriendshipLink
	 * @return
	 */
	public abstract NavigationFriendshipLinkDO navigationFriendshipLinkToNavigationFriendshipLinkDO(NavigationFriendshipLink navigationFriendshipLink);

}
