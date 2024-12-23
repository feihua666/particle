package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用开放接口月汇总 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordAppOpenapiMonthSummaryId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordAppOpenapiMonthSummaryId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId openplatformOpenapiRecordAppOpenapiMonthSummaryId){
		if (openplatformOpenapiRecordAppOpenapiMonthSummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiMonthSummaryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummaryDOToOpenplatformOpenapiRecordAppOpenapiMonthSummary(@MappingTarget OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary,OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO openplatformOpenapiRecordAppOpenapiMonthSummaryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiMonthSummaryInfrastructureStructMapping#map(OpenplatformOpenapiRecordAppOpenapiMonthSummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO openplatformOpenapiRecordAppOpenapiMonthSummaryToOpenplatformOpenapiRecordAppOpenapiMonthSummaryDO(OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary);

}
