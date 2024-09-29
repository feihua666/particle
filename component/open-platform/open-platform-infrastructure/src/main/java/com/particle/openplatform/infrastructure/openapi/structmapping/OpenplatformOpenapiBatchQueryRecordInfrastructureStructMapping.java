package com.particle.openplatform.infrastructure.openapi.structmapping;

import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口批量查询记录 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Mapper
public abstract class OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping {
	public static OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping.class );

	protected OpenplatformOpenapiBatchQueryRecordId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiBatchQueryRecordId.of(id);
	}
	protected Long map(OpenplatformOpenapiBatchQueryRecordId openplatformOpenapiBatchQueryRecordId){
		if (openplatformOpenapiBatchQueryRecordId == null) {
			return null;
		}
		return openplatformOpenapiBatchQueryRecordId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiBatchQueryRecordDO
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecordDOToOpenplatformOpenapiBatchQueryRecord(@MappingTarget OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord,OpenplatformOpenapiBatchQueryRecordDO openplatformOpenapiBatchQueryRecordDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordInfrastructureStructMapping#map(OpenplatformOpenapiBatchQueryRecordId)}
	 * @param openplatformOpenapiBatchQueryRecord
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordDO openplatformOpenapiBatchQueryRecordToOpenplatformOpenapiBatchQueryRecordDO(OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord);

}
