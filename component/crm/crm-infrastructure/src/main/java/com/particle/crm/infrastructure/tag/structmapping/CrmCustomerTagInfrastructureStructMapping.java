package com.particle.crm.infrastructure.tag.structmapping;

import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.CrmCustomerTagId;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户标签 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerTagInfrastructureStructMapping {
	public static CrmCustomerTagInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerTagInfrastructureStructMapping.class );

	protected CrmCustomerTagId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerTagId.of(id);
	}
	protected Long map(CrmCustomerTagId crmCustomerTagId){
		if (crmCustomerTagId == null) {
			return null;
		}
		return crmCustomerTagId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerTagDO
	 * @return
	 */
	public abstract CrmCustomerTag crmCustomerTagDOToCrmCustomerTag(@MappingTarget CrmCustomerTag crmCustomerTag,CrmCustomerTagDO crmCustomerTagDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagInfrastructureStructMapping#map(CrmCustomerTagId)}
	 * @param crmCustomerTag
	 * @return
	 */
	public abstract CrmCustomerTagDO crmCustomerTagToCrmCustomerTagDO(CrmCustomerTag crmCustomerTag);

}
