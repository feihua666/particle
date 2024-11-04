package com.particle.navigation.infrastructure.structmapping;

import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;
import com.particle.navigation.domain.NavigationStaticDeploy;
import com.particle.navigation.domain.NavigationStaticDeployId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 导航网站静态部署 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Mapper
public abstract class NavigationStaticDeployInfrastructureStructMapping {
	public static NavigationStaticDeployInfrastructureStructMapping instance = Mappers.getMapper( NavigationStaticDeployInfrastructureStructMapping.class );

	protected NavigationStaticDeployId map(Long id){
		if (id == null) {
			return null;
		}
		return NavigationStaticDeployId.of(id);
	}
	protected Long map(NavigationStaticDeployId navigationStaticDeployId){
		if (navigationStaticDeployId == null) {
			return null;
		}
		return navigationStaticDeployId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationStaticDeployInfrastructureStructMapping#map(java.lang.Long)}
	 * @param navigationStaticDeployDO
	 * @return
	 */
	public abstract NavigationStaticDeploy navigationStaticDeployDOToNavigationStaticDeploy(@MappingTarget NavigationStaticDeploy navigationStaticDeploy,NavigationStaticDeployDO navigationStaticDeployDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link NavigationStaticDeployInfrastructureStructMapping#map(NavigationStaticDeployId)}
	 * @param navigationStaticDeploy
	 * @return
	 */
	public abstract NavigationStaticDeployDO navigationStaticDeployToNavigationStaticDeployDO(NavigationStaticDeploy navigationStaticDeploy);

}
