package com.particle.crm.infrastructure.tag.gateway.impl;

import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.CrmCustomerTagRelId;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagRelGateway;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagRelService;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.crm.infrastructure.tag.structmapping.CrmCustomerTagRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户标签关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Component
public class CrmCustomerTagRelGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerTagRelId,CrmCustomerTagRel> implements CrmCustomerTagRelGateway {

	private ICrmCustomerTagRelService iCrmCustomerTagRelService;

	@Override
	public CrmCustomerTagRel getById(CrmCustomerTagRelId crmCustomerTagRelId) {
		CrmCustomerTagRelDO byId = iCrmCustomerTagRelService.getById(crmCustomerTagRelId.getId());
		CrmCustomerTagRel crmCustomerTagRel = DomainFactory.create(CrmCustomerTagRel.class);
		crmCustomerTagRel = CrmCustomerTagRelInfrastructureStructMapping.instance. crmCustomerTagRelDOToCrmCustomerTagRel(crmCustomerTagRel,byId);
		return crmCustomerTagRel;
	}

	@Override
	public boolean doSave(CrmCustomerTagRel crmCustomerTagRel) {
		CrmCustomerTagRelDO crmCustomerTagRelDO = CrmCustomerTagRelInfrastructureStructMapping.instance.crmCustomerTagRelToCrmCustomerTagRelDO(crmCustomerTagRel);
		if (crmCustomerTagRelDO.getId() == null) {
			crmCustomerTagRelDO.setAddControl(crmCustomerTagRel.getAddControl());
			CrmCustomerTagRelDO add = iCrmCustomerTagRelService.add(crmCustomerTagRelDO);
			crmCustomerTagRel.setId(CrmCustomerTagRelId.of(add.getId()));
			return add != null;
		}
		crmCustomerTagRelDO.setUpdateControl(crmCustomerTagRel.getUpdateControl());
		CrmCustomerTagRelDO update = iCrmCustomerTagRelService.update(crmCustomerTagRelDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerTagRelId crmCustomerTagRelId) {
		return iCrmCustomerTagRelService.deleteById(crmCustomerTagRelId.getId());
	}


	@Autowired
	public void setICrmCustomerTagRelService(ICrmCustomerTagRelService iCrmCustomerTagRelService) {
		this.iCrmCustomerTagRelService = iCrmCustomerTagRelService;
	}
}
