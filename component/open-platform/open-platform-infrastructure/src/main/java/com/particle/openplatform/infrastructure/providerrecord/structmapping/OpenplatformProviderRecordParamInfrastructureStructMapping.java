package com.particle.openplatform.infrastructure.providerrecord.structmapping;

import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParam;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParamId;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordParamInfrastructureStructMapping {
	public static OpenplatformProviderRecordParamInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordParamInfrastructureStructMapping.class );

	protected OpenplatformProviderRecordParamId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderRecordParamId.of(id);
	}
	protected Long map(OpenplatformProviderRecordParamId openplatformProviderRecordParamId){
		if (openplatformProviderRecordParamId == null) {
			return null;
		}
		return openplatformProviderRecordParamId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordParamInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderRecordParamDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordParam openplatformProviderRecordParamDOToOpenplatformProviderRecordParam(@MappingTarget OpenplatformProviderRecordParam openplatformProviderRecordParam,OpenplatformProviderRecordParamDO openplatformProviderRecordParamDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordParamInfrastructureStructMapping#map(OpenplatformProviderRecordParamId)}
	 * @param openplatformProviderRecordParam
	 * @return
	 */
	public abstract OpenplatformProviderRecordParamDO openplatformProviderRecordParamToOpenplatformProviderRecordParamDO(OpenplatformProviderRecordParam openplatformProviderRecordParam);

}
