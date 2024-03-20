package com.particle.openplatform.infrastructure.doc.structmapping;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口文档接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Mapper
public abstract class OpenplatformDocApiInfrastructureStructMapping {
	public static OpenplatformDocApiInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformDocApiInfrastructureStructMapping.class );

	protected OpenplatformDocApiId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformDocApiId.of(id);
	}
	protected Long map(OpenplatformDocApiId openplatformDocApiId){
		if (openplatformDocApiId == null) {
			return null;
		}
		return openplatformDocApiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformDocApiDO
	 * @return
	 */
	public abstract OpenplatformDocApi openplatformDocApiDOToOpenplatformDocApi(@MappingTarget OpenplatformDocApi openplatformDocApi,OpenplatformDocApiDO openplatformDocApiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformDocApiInfrastructureStructMapping#map(OpenplatformDocApiId)}
	 * @param openplatformDocApi
	 * @return
	 */
	public abstract OpenplatformDocApiDO openplatformDocApiToOpenplatformDocApiDO(OpenplatformDocApi openplatformDocApi);

}
