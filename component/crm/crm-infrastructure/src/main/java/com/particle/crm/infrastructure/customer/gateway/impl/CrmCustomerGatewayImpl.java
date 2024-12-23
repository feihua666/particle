package com.particle.crm.infrastructure.customer.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;
import com.particle.crm.domain.customer.gateway.CrmCustomerGateway;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerService;
import com.particle.crm.infrastructure.customer.structmapping.CrmCustomerInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Component
public class CrmCustomerGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerId,CrmCustomer> implements CrmCustomerGateway {

	private ICrmCustomerService iCrmCustomerService;

	@Override
	public CrmCustomer getById(CrmCustomerId crmCustomerId) {
		CrmCustomerDO byId = iCrmCustomerService.getById(crmCustomerId.getId());
		CrmCustomer crmCustomer = DomainFactory.create(CrmCustomer.class);
		crmCustomer = CrmCustomerInfrastructureStructMapping.instance. crmCustomerDOToCrmCustomer(crmCustomer,byId);
		return crmCustomer;
	}

	@Override
	public boolean doSave(CrmCustomer crmCustomer) {
		CrmCustomerDO crmCustomerDO = CrmCustomerInfrastructureStructMapping.instance.crmCustomerToCrmCustomerDO(crmCustomer);
		if (crmCustomerDO.getId() == null) {
			crmCustomerDO.setAddControl(crmCustomer.getAddControl());
			CrmCustomerDO add = iCrmCustomerService.add(crmCustomerDO);
			crmCustomer.setId(CrmCustomerId.of(add.getId()));
			return add != null;
		}
		crmCustomerDO.setUpdateControl(crmCustomer.getUpdateControl());
		CrmCustomerDO update = iCrmCustomerService.update(crmCustomerDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerId crmCustomerId) {
		return iCrmCustomerService.deleteById(crmCustomerId.getId());
	}

	@Override
	public boolean delete(CrmCustomerId id, IdCommand idCommand) {
		return iCrmCustomerService.deleteById(idCommand);
	}

	@Autowired
	public void setICrmCustomerService(ICrmCustomerService iCrmCustomerService) {
		this.iCrmCustomerService = iCrmCustomerService;
	}
}
