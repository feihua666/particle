package com.particle.crm.infrastructure.tag.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.CrmCustomerTagId;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagGateway;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.crm.infrastructure.tag.service.ICrmCustomerTagService;
import com.particle.crm.infrastructure.tag.structmapping.CrmCustomerTagInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户标签 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Component
public class CrmCustomerTagGatewayImpl extends AbstractBaseGatewayImpl<CrmCustomerTagId,CrmCustomerTag> implements CrmCustomerTagGateway {

	private ICrmCustomerTagService iCrmCustomerTagService;

	@Override
	public CrmCustomerTag getById(CrmCustomerTagId crmCustomerTagId) {
		CrmCustomerTagDO byId = iCrmCustomerTagService.getById(crmCustomerTagId.getId());
		CrmCustomerTag crmCustomerTag = DomainFactory.create(CrmCustomerTag.class);
		crmCustomerTag = CrmCustomerTagInfrastructureStructMapping.instance. crmCustomerTagDOToCrmCustomerTag(crmCustomerTag,byId);
		return crmCustomerTag;
	}

	@Override
	public boolean doSave(CrmCustomerTag crmCustomerTag) {
		CrmCustomerTagDO crmCustomerTagDO = CrmCustomerTagInfrastructureStructMapping.instance.crmCustomerTagToCrmCustomerTagDO(crmCustomerTag);
		if (crmCustomerTagDO.getId() == null) {
			crmCustomerTagDO.setAddControl(crmCustomerTag.getAddControl());
			CrmCustomerTagDO add = iCrmCustomerTagService.add(crmCustomerTagDO);
			crmCustomerTag.setId(CrmCustomerTagId.of(add.getId()));
			return add != null;
		}
		crmCustomerTagDO.setUpdateControl(crmCustomerTag.getUpdateControl());
		CrmCustomerTagDO update = iCrmCustomerTagService.update(crmCustomerTagDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCustomerTagId crmCustomerTagId) {
		return iCrmCustomerTagService.deleteById(crmCustomerTagId.getId());
	}

	@Override
	public boolean delete(CrmCustomerTagId id, IdCommand idCommand) {
		return iCrmCustomerTagService.deleteById(idCommand);
	}

	@Autowired
	public void setICrmCustomerTagService(ICrmCustomerTagService iCrmCustomerTagService) {
		this.iCrmCustomerTagService = iCrmCustomerTagService;
	}
}
