package com.particle.openplatform.infrastructure.openapirecord.structmapping;

import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecord;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口调用记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Mapper
public abstract class OpenplatformOpenapiRecordInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordId openplatformOpenapiRecordId){
		if (openplatformOpenapiRecordId == null) {
			return null;
		}
		return openplatformOpenapiRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecord openplatformOpenapiRecordDOToOpenplatformOpenapiRecord(@MappingTarget OpenplatformOpenapiRecord openplatformOpenapiRecord,OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordInfrastructureStructMapping#map(OpenplatformOpenapiRecordId)}
	 * @param openplatformOpenapiRecord
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordDO openplatformOpenapiRecordToOpenplatformOpenapiRecordDO(OpenplatformOpenapiRecord openplatformOpenapiRecord);

}
