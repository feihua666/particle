package com.particle.crm.infrastructure.relation.gateway.impl;

import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.CrmCustomerRelationId;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationGateway;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationService;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.crm.infrastructure.relation.structmapping.CrmCustomerRelationInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户与客户关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Component
public class CrmCustomerRelationGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerRelationId,CrmCustomerRelation> implements CrmCustomerRelationGateway {

	private ICrmCustomerRelationService iCrmCustomerRelationService;

	@Override
	public CrmCustomerRelation getById(CrmCustomerRelationId crmCustomerRelationId) {
		CrmCustomerRelationDO byId = iCrmCustomerRelationService.getById(crmCustomerRelationId.getId());
		CrmCustomerRelation crmCustomerRelation = DomainFactory.create(CrmCustomerRelation.class);
		crmCustomerRelation = CrmCustomerRelationInfrastructureStructMapping.instance. crmCustomerRelationDOToCrmCustomerRelation(crmCustomerRelation,byId);
		return crmCustomerRelation;
	}

	@Override
	public boolean doSave(CrmCustomerRelation crmCustomerRelation) {
		CrmCustomerRelationDO crmCustomerRelationDO = CrmCustomerRelationInfrastructureStructMapping.instance.crmCustomerRelationToCrmCustomerRelationDO(crmCustomerRelation);
		if (crmCustomerRelationDO.getId() == null) {
			crmCustomerRelationDO.setAddControl(crmCustomerRelation.getAddControl());
			CrmCustomerRelationDO add = iCrmCustomerRelationService.add(crmCustomerRelationDO);
			crmCustomerRelation.setId(CrmCustomerRelationId.of(add.getId()));
			return add != null;
		}
		crmCustomerRelationDO.setUpdateControl(crmCustomerRelation.getUpdateControl());
		CrmCustomerRelationDO update = iCrmCustomerRelationService.update(crmCustomerRelationDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerRelationId crmCustomerRelationId) {
		return iCrmCustomerRelationService.deleteById(crmCustomerRelationId.getId());
	}


	@Autowired
	public void setICrmCustomerRelationService(ICrmCustomerRelationService iCrmCustomerRelationService) {
		this.iCrmCustomerRelationService = iCrmCustomerRelationService;
	}
}
