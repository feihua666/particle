package com.particle.crm.infrastructure.relation.structmapping;

import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.CrmCustomerRelationDefineId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户关系定义 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Mapper
public abstract class CrmCustomerRelationDefineInfrastructureStructMapping {
	public static CrmCustomerRelationDefineInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerRelationDefineInfrastructureStructMapping.class );

	protected CrmCustomerRelationDefineId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerRelationDefineId.of(id);
	}
	protected Long map(CrmCustomerRelationDefineId crmCustomerRelationDefineId){
		if (crmCustomerRelationDefineId == null) {
			return null;
		}
		return crmCustomerRelationDefineId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationDefineInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerRelationDefineDO
	 * @return
	 */
	public abstract CrmCustomerRelationDefine crmCustomerRelationDefineDOToCrmCustomerRelationDefine(@MappingTarget CrmCustomerRelationDefine crmCustomerRelationDefine,CrmCustomerRelationDefineDO crmCustomerRelationDefineDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationDefineInfrastructureStructMapping#map(CrmCustomerRelationDefineId)}
	 * @param crmCustomerRelationDefine
	 * @return
	 */
	public abstract CrmCustomerRelationDefineDO crmCustomerRelationDefineToCrmCustomerRelationDefineDO(CrmCustomerRelationDefine crmCustomerRelationDefine);

}
