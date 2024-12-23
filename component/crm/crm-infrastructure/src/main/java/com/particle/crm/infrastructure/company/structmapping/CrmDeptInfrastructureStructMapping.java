package com.particle.crm.infrastructure.company.structmapping;

import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.CrmDeptId;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户公司部门 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmDeptInfrastructureStructMapping {
	public static CrmDeptInfrastructureStructMapping instance = Mappers.getMapper( CrmDeptInfrastructureStructMapping.class );

	protected CrmDeptId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmDeptId.of(id);
	}
	protected Long map(CrmDeptId crmDeptId){
		if (crmDeptId == null) {
			return null;
		}
		return crmDeptId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmDeptInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmDeptDO
	 * @return
	 */
	public abstract CrmDept crmDeptDOToCrmDept(@MappingTarget CrmDept crmDept,CrmDeptDO crmDeptDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmDeptInfrastructureStructMapping#map(CrmDeptId)}
	 * @param crmDept
	 * @return
	 */
	public abstract CrmDeptDO crmDeptToCrmDeptDO(CrmDept crmDept);

}
