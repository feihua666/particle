package com.particle.crm.infrastructure.company.structmapping;

import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.CrmCompanyId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户公司 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Mapper
public abstract class CrmCompanyInfrastructureStructMapping {
	public static CrmCompanyInfrastructureStructMapping instance = Mappers.getMapper( CrmCompanyInfrastructureStructMapping.class );

	protected CrmCompanyId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCompanyId.of(id);
	}
	protected Long map(CrmCompanyId crmCompanyId){
		if (crmCompanyId == null) {
			return null;
		}
		return crmCompanyId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCompanyInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCompanyDO
	 * @return
	 */
	public abstract CrmCompany crmCompanyDOToCrmCompany(@MappingTarget CrmCompany crmCompany,CrmCompanyDO crmCompanyDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCompanyInfrastructureStructMapping#map(CrmCompanyId)}
	 * @param crmCompany
	 * @return
	 */
	public abstract CrmCompanyDO crmCompanyToCrmCompanyDO(CrmCompany crmCompany);

}
