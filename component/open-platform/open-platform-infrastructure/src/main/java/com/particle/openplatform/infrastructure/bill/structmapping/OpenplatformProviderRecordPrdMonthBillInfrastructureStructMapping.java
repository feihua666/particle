package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBillId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台供应商月账单 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Mapper
public abstract class OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping {
	public static OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping.class );

	protected OpenplatformProviderRecordPrdMonthBillId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformProviderRecordPrdMonthBillId.of(id);
	}
	protected Long map(OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId){
		if (openplatformProviderRecordPrdMonthBillId == null) {
			return null;
		}
		return openplatformProviderRecordPrdMonthBillId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformProviderRecordPrdMonthBillDO
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBillDOToOpenplatformProviderRecordPrdMonthBill(@MappingTarget OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill,OpenplatformProviderRecordPrdMonthBillDO openplatformProviderRecordPrdMonthBillDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformProviderRecordPrdMonthBillInfrastructureStructMapping#map(OpenplatformProviderRecordPrdMonthBillId)}
	 * @param openplatformProviderRecordPrdMonthBill
	 * @return
	 */
	public abstract OpenplatformProviderRecordPrdMonthBillDO openplatformProviderRecordPrdMonthBillToOpenplatformProviderRecordPrdMonthBillDO(OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill);

}
