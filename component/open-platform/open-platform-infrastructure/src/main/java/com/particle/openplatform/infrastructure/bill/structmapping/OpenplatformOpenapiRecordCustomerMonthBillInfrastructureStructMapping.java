package com.particle.openplatform.infrastructure.bill.structmapping;

import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBillId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 开放平台客户月账单 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Mapper
public abstract class OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping {
	public static OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping.class );

	protected OpenplatformOpenapiRecordCustomerMonthBillId map(Long id){
		if (id == null) {
			return null;
		}
		return OpenplatformOpenapiRecordCustomerMonthBillId.of(id);
	}
	protected Long map(OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId){
		if (openplatformOpenapiRecordCustomerMonthBillId == null) {
			return null;
		}
		return openplatformOpenapiRecordCustomerMonthBillId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping#map(java.lang.Long)}
	 * @param openplatformOpenapiRecordCustomerMonthBillDO
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBillDOToOpenplatformOpenapiRecordCustomerMonthBill(@MappingTarget OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill,OpenplatformOpenapiRecordCustomerMonthBillDO openplatformOpenapiRecordCustomerMonthBillDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiRecordCustomerMonthBillInfrastructureStructMapping#map(OpenplatformOpenapiRecordCustomerMonthBillId)}
	 * @param openplatformOpenapiRecordCustomerMonthBill
	 * @return
	 */
	public abstract OpenplatformOpenapiRecordCustomerMonthBillDO openplatformOpenapiRecordCustomerMonthBillToOpenplatformOpenapiRecordCustomerMonthBillDO(OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill);

}
