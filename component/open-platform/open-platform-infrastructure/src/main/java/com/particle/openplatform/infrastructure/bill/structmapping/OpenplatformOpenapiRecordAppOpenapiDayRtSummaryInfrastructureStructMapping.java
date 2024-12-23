package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId openplatformOpenapiRecordAppOpenapiDayRtSummaryId){
		if (openplatformOpenapiRecordAppOpenapiDayRtSummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiDayRtSummaryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummaryDOToOpenplatformOpenapiRecordAppOpenapiDayRtSummary(@MappingTarget OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary,OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO openplatformOpenapiRecordAppOpenapiDayRtSummaryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDayRtSummaryInfrastructureStructMapping#map(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO openplatformOpenapiRecordAppOpenapiDayRtSummaryToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO(OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary);

}
