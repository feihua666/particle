package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummaryId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台供应商接口日汇总 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping {
	public static OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping.class );

	protected OpenplatformProviderRecordPrdApiDaySummaryId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderRecordPrdApiDaySummaryId.of(id);
	}
	protected Long map(OpenplatformProviderRecordPrdApiDaySummaryId openplatformProviderRecordPrdApiDaySummaryId){
		if (openplatformProviderRecordPrdApiDaySummaryId == null) {
			return null;
		}
		return openplatformProviderRecordPrdApiDaySummaryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderRecordPrdApiDaySummaryDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummaryDOToOpenplatformProviderRecordPrdApiDaySummary(@MappingTarget OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary,OpenplatformProviderRecordPrdApiDaySummaryDO openplatformProviderRecordPrdApiDaySummaryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdApiDaySummaryInfrastructureStructMapping#map(OpenplatformProviderRecordPrdApiDaySummaryId)}
	 * @param openplatformProviderRecordPrdApiDaySummary
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdApiDaySummaryDO openplatformProviderRecordPrdApiDaySummaryToOpenplatformProviderRecordPrdApiDaySummaryDO(OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary);

}
