package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBillId;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台应用月账单 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordAppMonthBillId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordAppMonthBillId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId){
		if (openplatformOpenapiRecordAppMonthBillId == null) {
			return null;
		}
		return openplatformOpenapiRecordAppMonthBillId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordAppMonthBillDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBillDOToOpenplatformOpenapiRecordAppMonthBill(@MappingTarget OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill,OpenplatformOpenapiRecordAppMonthBillDO openplatformOpenapiRecordAppMonthBillDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordAppMonthBillInfrastructureStructMapping#map(OpenplatformOpenapiRecordAppMonthBillId)}
	 * @param openplatformOpenapiRecordAppMonthBill
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordAppMonthBillDO openplatformOpenapiRecordAppMonthBillToOpenplatformOpenapiRecordAppMonthBillDO(OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill);

}
