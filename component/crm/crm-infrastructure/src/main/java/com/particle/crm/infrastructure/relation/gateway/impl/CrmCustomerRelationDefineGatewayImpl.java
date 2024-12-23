package com.particle.crm.infrastructure.relation.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.CrmCustomerRelationDefineId;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationDefineGateway;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.crm.infrastructure.relation.service.ICrmCustomerRelationDefineService;
import com.particle.crm.infrastructure.relation.structmapping.CrmCustomerRelationDefineInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户关系定义 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Component
public class CrmCustomerRelationDefineGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerRelationDefineId,CrmCustomerRelationDefine> implements CrmCustomerRelationDefineGateway {

	private ICrmCustomerRelationDefineService iCrmCustomerRelationDefineService;

	@Override
	public CrmCustomerRelationDefine getById(CrmCustomerRelationDefineId crmCustomerRelationDefineId) {
		CrmCustomerRelationDefineDO byId = iCrmCustomerRelationDefineService.getById(crmCustomerRelationDefineId.getId());
		CrmCustomerRelationDefine crmCustomerRelationDefine = DomainFactory.create(CrmCustomerRelationDefine.class);
		crmCustomerRelationDefine = CrmCustomerRelationDefineInfrastructureStructMapping.instance. crmCustomerRelationDefineDOToCrmCustomerRelationDefine(crmCustomerRelationDefine,byId);
		return crmCustomerRelationDefine;
	}

	@Override
	public boolean doSave(CrmCustomerRelationDefine crmCustomerRelationDefine) {
		CrmCustomerRelationDefineDO crmCustomerRelationDefineDO = CrmCustomerRelationDefineInfrastructureStructMapping.instance.crmCustomerRelationDefineToCrmCustomerRelationDefineDO(crmCustomerRelationDefine);
		if (crmCustomerRelationDefineDO.getId() == null) {
			crmCustomerRelationDefineDO.setAddControl(crmCustomerRelationDefine.getAddControl());
			CrmCustomerRelationDefineDO add = iCrmCustomerRelationDefineService.add(crmCustomerRelationDefineDO);
			crmCustomerRelationDefine.setId(CrmCustomerRelationDefineId.of(add.getId()));
			return add != null;
		}
		crmCustomerRelationDefineDO.setUpdateControl(crmCustomerRelationDefine.getUpdateControl());
		CrmCustomerRelationDefineDO update = iCrmCustomerRelationDefineService.update(crmCustomerRelationDefineDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerRelationDefineId crmCustomerRelationDefineId) {
		return iCrmCustomerRelationDefineService.deleteById(crmCustomerRelationDefineId.getId());
	}

	@Override
	public boolean delete(CrmCustomerRelationDefineId id, IdCommand idCommand) {
		return iCrmCustomerRelationDefineService.deleteById(idCommand);
	}

	@Autowired
	public void setICrmCustomerRelationDefineService(ICrmCustomerRelationDefineService iCrmCustomerRelationDefineService) {
		this.iCrmCustomerRelationDefineService = iCrmCustomerRelationDefineService;
	}
}
