package com.particle.crm.infrastructure.relation.structmapping;

import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.CrmCustomerRelationId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 客户与客户关系 基础设施层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Mapper
public abstract class CrmCustomerRelationInfrastructureStructMapping {
	public static CrmCustomerRelationInfrastructureStructMapping instance = Mappers.getMapper( CrmCustomerRelationInfrastructureStructMapping.class );

	protected CrmCustomerRelationId map(Long id){
		if (id == null) {
			return null;
		}
		return CrmCustomerRelationId.of(id);
	}
	protected Long map(CrmCustomerRelationId crmCustomerRelationId){
		if (crmCustomerRelationId == null) {
			return null;
		}
		return crmCustomerRelationId.getId();
	}

	/**
	 * 数据实体转领域模型
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationInfrastructureStructMapping#map(java.lang.Long)}
	 * @param crmCustomerRelationDO
	 * @return
	 */
	public abstract CrmCustomerRelation crmCustomerRelationDOToCrmCustomerRelation(@MappingTarget CrmCustomerRelation crmCustomerRelation,CrmCustomerRelationDO crmCustomerRelationDO);

	/**
	 * 领域模型转数据实体
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationInfrastructureStructMapping#map(CrmCustomerRelationId)}
	 * @param crmCustomerRelation
	 * @return
	 */
	public abstract CrmCustomerRelationDO crmCustomerRelationToCrmCustomerRelationDO(CrmCustomerRelation crmCustomerRelation);

}
