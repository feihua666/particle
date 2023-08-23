package com.particle.openplatform.infrastructure.openapirecord.structmapping;

import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParam;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParamId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口调用记录参数 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Mapper
public abstract class OpenplatformOpenapiRecordParamInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordParamInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordParamInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordParamId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordParamId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordParamId openplatformOpenapiRecordParamId){
		if (openplatformOpenapiRecordParamId == null) {
			return null;
		}
		return openplatformOpenapiRecordParamId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordParamInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordParamDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordParam openplatformOpenapiRecordParamDOToOpenplatformOpenapiRecordParam(@MappingTarget OpenplatformOpenapiRecordParam openplatformOpenapiRecordParam,OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordParamInfrastructureStructMapping#map(OpenplatformOpenapiRecordParamId)}
	 * @param openplatformOpenapiRecordParam
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamToOpenplatformOpenapiRecordParamDO(OpenplatformOpenapiRecordParam openplatformOpenapiRecordParam);

}
