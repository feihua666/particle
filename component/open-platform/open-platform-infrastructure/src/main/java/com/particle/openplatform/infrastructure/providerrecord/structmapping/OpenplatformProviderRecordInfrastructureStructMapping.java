package com.particle.openplatform.infrastructure.providerrecord.structmapping;

import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecord;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Mapper
public abstract class OpenplatformProviderRecordInfrastructureStructMapping {
	public static OpenplatformProviderRecordInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordInfrastructureStructMapping.class );

	protected OpenplatformProviderRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderRecordId.of(id);
	}
	protected Long map(OpenplatformProviderRecordId openplatformProviderRecordId){
		if (openplatformProviderRecordId == null) {
			return null;
		}
		return openplatformProviderRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderRecordDO
	 * @return
	 */
	public abstract OpenplatformProviderRecord openplatformProviderRecordDOToOpenplatformProviderRecord(@MappingTarget OpenplatformProviderRecord openplatformProviderRecord,OpenplatformProviderRecordDO openplatformProviderRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordInfrastructureStructMapping#map(OpenplatformProviderRecordId)}
	 * @param openplatformProviderRecord
	 * @return
	 */
	public abstract OpenplatformProviderRecordDO openplatformProviderRecordToOpenplatformProviderRecordDO(OpenplatformProviderRecord openplatformProviderRecord);

}
