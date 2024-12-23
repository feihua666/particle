package com.particle.crm.infrastructure.customer.structmapping;

import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerInfrastructureStructMapping {
	public static CrmCustomerInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerInfrastructureStructMapping.class );

	protected CrmCustomerId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerId.of(id);
	}
	protected Long map(CrmCustomerId crmCustomerId){
		if (crmCustomerId == null) {
			return null;
		}
		return crmCustomerId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerDO
	 * @return
	 */
	public abstract CrmCustomer crmCustomerDOToCrmCustomer(@MappingTarget CrmCustomer crmCustomer,CrmCustomerDO crmCustomerDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerInfrastructureStructMapping#map(CrmCustomerId)}
	 * @param crmCustomer
	 * @return
	 */
	public abstract CrmCustomerDO crmCustomerToCrmCustomerDO(CrmCustomer crmCustomer);

}
