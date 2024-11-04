package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import com.particle.navigation.domain.NavigationSubmit;
import com.particle.navigation.domain.NavigationSubmitId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航提交 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Mapper
public abstract class NavigationSubmitInfrastructureStructMapping {
	public static NavigationSubmitInfrastructureStructMapping instance = Mappers.getMapper( NavigationSubmitInfrastructureStructMapping.class );

	protected NavigationSubmitId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationSubmitId.of(id);
	}
	protected Long map(NavigationSubmitId navigationSubmitId){
		if (navigationSubmitId == null) {
			return null;
		}
		return navigationSubmitId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSubmitInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationSubmitDO
	 * @return
	 */
	public abstract NavigationSubmit navigationSubmitDOToNavigationSubmit(@MappingTarget NavigationSubmit navigationSubmit,NavigationSubmitDO navigationSubmitDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationSubmitInfrastructureStructMapping#map(NavigationSubmitId)}
	 * @param navigationSubmit
	 * @return
	 */
	public abstract NavigationSubmitDO navigationSubmitToNavigationSubmitDO(NavigationSubmit navigationSubmit);

}
