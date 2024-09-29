package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummaryId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用开放接口日汇总 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Mapper
public abstract class OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordAppOpenapiDaySummaryId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordAppOpenapiDaySummaryId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordAppOpenapiDaySummaryId openplatformOpenapiRecordAppOpenapiDaySummaryId){
		if (openplatformOpenapiRecordAppOpenapiDaySummaryId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppOpenapiDaySummaryId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummaryDOToOpenplatformOpenapiRecordAppOpenapiDaySummary(@MappingTarget OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary,OpenplatformOpenapiRecordAppOpenapiDaySummaryDO openplatformOpenapiRecordAppOpenapiDaySummaryDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppOpenapiDaySummaryInfrastructureStructMapping#map(OpenplatformOpenapiRecordAppOpenapiDaySummaryId)}
	 * @param openplatformOpenapiRecordAppOpenapiDaySummary
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppOpenapiDaySummaryDO openplatformOpenapiRecordAppOpenapiDaySummaryToOpenplatformOpenapiRecordAppOpenapiDaySummaryDO(OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary);

}
