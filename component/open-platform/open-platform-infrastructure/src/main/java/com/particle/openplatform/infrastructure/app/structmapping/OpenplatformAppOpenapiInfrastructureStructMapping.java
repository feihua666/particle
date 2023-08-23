package com.particle.openplatform.infrastructure.app.structmapping;

import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapi;
import com.particle.openplatform.domain.app.OpenplatformAppOpenapiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用与开放接口配置 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Mapper
public abstract class OpenplatformAppOpenapiInfrastructureStructMapping {
	public static OpenplatformAppOpenapiInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformAppOpenapiInfrastructureStructMapping.class );

	protected OpenplatformAppOpenapiId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformAppOpenapiId.of(id);
	}
	protected Long map(OpenplatformAppOpenapiId openplatformAppOpenapiId){
		if (openplatformAppOpenapiId == null) {
			return null;
		}
		return openplatformAppOpenapiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppOpenapiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformAppOpenapiDO
	 * @return
	 */
	public abstract OpenplatformAppOpenapi openplatformAppOpenapiDOToOpenplatformAppOpenapi(@MappingTarget OpenplatformAppOpenapi openplatformAppOpenapi,OpenplatformAppOpenapiDO openplatformAppOpenapiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppOpenapiInfrastructureStructMapping#map(OpenplatformAppOpenapiId)}
	 * @param openplatformAppOpenapi
	 * @return
	 */
	public abstract OpenplatformAppOpenapiDO openplatformAppOpenapiToOpenplatformAppOpenapiDO(OpenplatformAppOpenapi openplatformAppOpenapi);

}
