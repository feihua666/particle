package com.particle.openplatform.infrastructure.provider.structmapping;

import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.OpenplatformProviderApiId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台供应商接口 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Mapper
public abstract class OpenplatformProviderApiInfrastructureStructMapping {
	public static OpenplatformProviderApiInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderApiInfrastructureStructMapping.class );

	protected OpenplatformProviderApiId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderApiId.of(id);
	}
	protected Long map(OpenplatformProviderApiId openplatformProviderApiId){
		if (openplatformProviderApiId == null) {
			return null;
		}
		return openplatformProviderApiId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderApiInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderApiDO
	 * @return
	 */
	public abstract OpenplatformProviderApi openplatformProviderApiDOToOpenplatformProviderApi(@MappingTarget OpenplatformProviderApi openplatformProviderApi,OpenplatformProviderApiDO openplatformProviderApiDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderApiInfrastructureStructMapping#map(OpenplatformProviderApiId)}
	 * @param openplatformProviderApi
	 * @return
	 */
	public abstract OpenplatformProviderApiDO openplatformProviderApiToOpenplatformProviderApiDO(OpenplatformProviderApi openplatformProviderApi);

}
