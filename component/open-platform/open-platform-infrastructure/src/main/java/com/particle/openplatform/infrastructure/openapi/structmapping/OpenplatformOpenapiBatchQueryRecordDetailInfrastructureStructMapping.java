package com.particle.openplatform.infrastructure.openapi.structmapping;

import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetail;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordDetailId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放接口批量查询记录明细 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping {
	public static OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping.class );

	protected OpenplatformOpenapiBatchQueryRecordDetailId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiBatchQueryRecordDetailId.of(id);
	}
	protected Long map(OpenplatformOpenapiBatchQueryRecordDetailId openplatformOpenapiBatchQueryRecordDetailId){
		if (openplatformOpenapiBatchQueryRecordDetailId == null) {
			return null;
		}
		return openplatformOpenapiBatchQueryRecordDetailId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiBatchQueryRecordDetailDO
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetailDOToOpenplatformOpenapiBatchQueryRecordDetail(@MappingTarget OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail,OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiBatchQueryRecordDetailInfrastructureStructMapping#map(OpenplatformOpenapiBatchQueryRecordDetailId)}
	 * @param openplatformOpenapiBatchQueryRecordDetail
	 * @return
	 */
	public abstract OpenplatformOpenapiBatchQueryRecordDetailDO openplatformOpenapiBatchQueryRecordDetailToOpenplatformOpenapiBatchQueryRecordDetailDO(OpenplatformOpenapiBatchQueryRecordDetail openplatformOpenapiBatchQueryRecordDetail);

}
