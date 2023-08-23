package com.particle.openplatform.infrastructure.provider.structmapping;

import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderDO;
import com.particle.openplatform.domain.provider.OpenplatformProvider;
import com.particle.openplatform.domain.provider.OpenplatformProviderId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口供应商 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Mapper
public abstract class OpenplatformProviderInfrastructureStructMapping {
	public static OpenplatformProviderInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderInfrastructureStructMapping.class );

	protected OpenplatformProviderId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderId.of(id);
	}
	protected Long map(OpenplatformProviderId openplatformProviderId){
		if (openplatformProviderId == null) {
			return null;
		}
		return openplatformProviderId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderDO
	 * @return
	 */
	public abstract OpenplatformProvider openplatformProviderDOToOpenplatformProvider(@MappingTarget OpenplatformProvider openplatformProvider,OpenplatformProviderDO openplatformProviderDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderInfrastructureStructMapping#map(OpenplatformProviderId)}
	 * @param openplatformProvider
	 * @return
	 */
	public abstract OpenplatformProviderDO openplatformProviderToOpenplatformProviderDO(OpenplatformProvider openplatformProvider);

}
