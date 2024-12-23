package com.particle.crm.infrastructure.customer.structmapping;

import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.CrmCustomerContactId;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户联系方式 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerContactInfrastructureStructMapping {
	public static CrmCustomerContactInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerContactInfrastructureStructMapping.class );

	protected CrmCustomerContactId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerContactId.of(id);
	}
	protected Long map(CrmCustomerContactId crmCustomerContactId){
		if (crmCustomerContactId == null) {
			return null;
		}
		return crmCustomerContactId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerContactInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerContactDO
	 * @return
	 */
	public abstract CrmCustomerContact crmCustomerContactDOToCrmCustomerContact(@MappingTarget CrmCustomerContact crmCustomerContact,CrmCustomerContactDO crmCustomerContactDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerContactInfrastructureStructMapping#map(CrmCustomerContactId)}
	 * @param crmCustomerContact
	 * @return
	 */
	public abstract CrmCustomerContactDO crmCustomerContactToCrmCustomerContactDO(CrmCustomerContact crmCustomerContact);

}
