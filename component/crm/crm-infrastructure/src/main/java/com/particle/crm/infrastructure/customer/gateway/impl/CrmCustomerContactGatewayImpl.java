package com.particle.crm.infrastructure.customer.gateway.impl;

import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.CrmCustomerContactId;
import com.particle.crm.domain.customer.gateway.CrmCustomerContactGateway;
import com.particle.crm.infrastructure.customer.service.ICrmCustomerContactService;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.crm.infrastructure.customer.structmapping.CrmCustomerContactInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户联系方式 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Component
public class CrmCustomerContactGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerContactId,CrmCustomerContact> implements CrmCustomerContactGateway {

	private ICrmCustomerContactService iCrmCustomerContactService;

	@Override
	public CrmCustomerContact getById(CrmCustomerContactId crmCustomerContactId) {
		CrmCustomerContactDO byId = iCrmCustomerContactService.getById(crmCustomerContactId.getId());
		CrmCustomerContact crmCustomerContact = DomainFactory.create(CrmCustomerContact.class);
		crmCustomerContact = CrmCustomerContactInfrastructureStructMapping.instance. crmCustomerContactDOToCrmCustomerContact(crmCustomerContact,byId);
		return crmCustomerContact;
	}

	@Override
	public boolean doSave(CrmCustomerContact crmCustomerContact) {
		CrmCustomerContactDO crmCustomerContactDO = CrmCustomerContactInfrastructureStructMapping.instance.crmCustomerContactToCrmCustomerContactDO(crmCustomerContact);
		if (crmCustomerContactDO.getId() == null) {
			crmCustomerContactDO.setAddControl(crmCustomerContact.getAddControl());
			CrmCustomerContactDO add = iCrmCustomerContactService.add(crmCustomerContactDO);
			crmCustomerContact.setId(CrmCustomerContactId.of(add.getId()));
			return add != null;
		}
		crmCustomerContactDO.setUpdateControl(crmCustomerContact.getUpdateControl());
		CrmCustomerContactDO update = iCrmCustomerContactService.update(crmCustomerContactDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerContactId crmCustomerContactId) {
		return iCrmCustomerContactService.deleteById(crmCustomerContactId.getId());
	}

	@Override
	public boolean delete(CrmCustomerContactId id, IdCommand idCommand) {
		return iCrmCustomerContactService.deleteById(idCommand);
	}

	@Autowired
	public void setICrmCustomerContactService(ICrmCustomerContactService iCrmCustomerContactService) {
		this.iCrmCustomerContactService = iCrmCustomerContactService;
	}
}
