package com.particle.openplatform.infrastructure.app.structmapping;

import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.domain.app.OpenplatformApp;
import com.particle.openplatform.domain.app.OpenplatformAppId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Mapper
public abstract class OpenplatformAppInfrastructureStructMapping {
	public static OpenplatformAppInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformAppInfrastructureStructMapping.class );

	protected OpenplatformAppId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformAppId.of(id);
	}
	protected Long map(OpenplatformAppId openplatformAppId){
		if (openplatformAppId == null) {
			return null;
		}
		return openplatformAppId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformAppDO
	 * @return
	 */
	public abstract OpenplatformApp openplatformAppDOToOpenplatformApp(@MappingTarget OpenplatformApp openplatformApp,OpenplatformAppDO openplatformAppDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppInfrastructureStructMapping#map(OpenplatformAppId)}
	 * @param openplatformApp
	 * @return
	 */
	public abstract OpenplatformAppDO openplatformAppToOpenplatformAppDO(OpenplatformApp openplatformApp);

}
