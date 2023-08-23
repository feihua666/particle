package com.particle.openplatform.infrastructure.openapi.structmapping;

import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapi;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Mapper
public abstract class OpenplatformOpenapiInfrastructureStructMapping {
	public static OpenplatformOpenapiInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiInfrastructureStructMapping.class );

	protected OpenplatformOpenapiId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiId.of(id);
	}
	protected Long map(OpenplatformOpenapiId openplatformOpenapiId){
		if (openplatformOpenapiId == null) {
			return null;
		}
		return openplatformOpenapiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiDO
	 * @return
	 */
	public abstract OpenplatformOpenapi openplatformOpenapiDOToOpenplatformOpenapi(@MappingTarget OpenplatformOpenapi openplatformOpenapi,OpenplatformOpenapiDO openplatformOpenapiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiInfrastructureStructMapping#map(OpenplatformOpenapiId)}
	 * @param openplatformOpenapi
	 * @return
	 */
	public abstract OpenplatformOpenapiDO openplatformOpenapiToOpenplatformOpenapiDO(OpenplatformOpenapi openplatformOpenapi);

}
