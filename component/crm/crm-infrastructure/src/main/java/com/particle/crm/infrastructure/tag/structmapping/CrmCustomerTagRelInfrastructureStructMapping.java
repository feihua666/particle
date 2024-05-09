package com.particle.crm.infrastructure.tag.structmapping;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.CrmCustomerTagRelId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户标签关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Mapper
public abstract class CrmCustomerTagRelInfrastructureStructMapping {
	public static CrmCustomerTagRelInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerTagRelInfrastructureStructMapping.class );

	protected CrmCustomerTagRelId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerTagRelId.of(id);
	}
	protected Long map(CrmCustomerTagRelId crmCustomerTagRelId){
		if (crmCustomerTagRelId == null) {
			return null;
		}
		return crmCustomerTagRelId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagRelInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerTagRelDO
	 * @return
	 */
	public abstract CrmCustomerTagRel crmCustomerTagRelDOToCrmCustomerTagRel(@MappingTarget CrmCustomerTagRel crmCustomerTagRel,CrmCustomerTagRelDO crmCustomerTagRelDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagRelInfrastructureStructMapping#map(CrmCustomerTagRelId)}
	 * @param crmCustomerTagRel
	 * @return
	 */
	public abstract CrmCustomerTagRelDO crmCustomerTagRelToCrmCustomerTagRelDO(CrmCustomerTagRel crmCustomerTagRel);

}
