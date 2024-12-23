package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台供应商接口月汇总 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping {
	public static OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping.class );

	protected OpenplatformProviderRecordPrdApiMonthSummaryId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderRecordPrdApiMonthSummaryId.of(id);
	}
	protected Long map(OpenplatformProviderRecordPrdApiMonthSummaryId openplatformProviderRecordPrdApiMonthSummaryId){
		if (openplatformProviderRecordPrdApiMonthSummaryId == null) {
			return null;
		}
		return openplatformProviderRecordPrdApiMonthSummaryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderRecordPrdApiMonthSummaryDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummaryDOToOpenplatformProviderRecordPrdApiMonthSummary(@MappingTarget OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary,OpenplatformProviderRecordPrdApiMonthSummaryDO openplatformProviderRecordPrdApiMonthSummaryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiMonthSummaryInfrastructureStructMapping#map(OpenplatformProviderRecordPrdApiMonthSummaryId)}
	 * @param openplatformProviderRecordPrdApiMonthSummary
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiMonthSummaryDO openplatformProviderRecordPrdApiMonthSummaryToOpenplatformProviderRecordPrdApiMonthSummaryDO(OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary);

}
