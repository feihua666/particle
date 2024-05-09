package com.particle.crm.infrastructure.company.gateway.impl;

import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.CrmCompanyId;
import com.particle.crm.domain.company.gateway.CrmCompanyGateway;
import com.particle.crm.infrastructure.company.service.ICrmCompanyService;
import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.crm.infrastructure.company.structmapping.CrmCompanyInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户公司 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Component
public class CrmCompanyGatewayImpl extends AbstractBaseGatewayImpl<CrmCompanyId,CrmCompany> implements CrmCompanyGateway {

	private ICrmCompanyService iCrmCompanyService;

	@Override
	public CrmCompany getById(CrmCompanyId crmCompanyId) {
		CrmCompanyDO byId = iCrmCompanyService.getById(crmCompanyId.getId());
		CrmCompany crmCompany = DomainFactory.create(CrmCompany.class);
		crmCompany = CrmCompanyInfrastructureStructMapping.instance. crmCompanyDOToCrmCompany(crmCompany,byId);
		return crmCompany;
	}

	@Override
	public boolean doSave(CrmCompany crmCompany) {
		CrmCompanyDO crmCompanyDO = CrmCompanyInfrastructureStructMapping.instance.crmCompanyToCrmCompanyDO(crmCompany);
		if (crmCompanyDO.getId() == null) {
			crmCompanyDO.setAddControl(crmCompany.getAddControl());
			CrmCompanyDO add = iCrmCompanyService.add(crmCompanyDO);
			crmCompany.setId(CrmCompanyId.of(add.getId()));
			return add != null;
		}
		crmCompanyDO.setUpdateControl(crmCompany.getUpdateControl());
		CrmCompanyDO update = iCrmCompanyService.update(crmCompanyDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmCompanyId crmCompanyId) {
		return iCrmCompanyService.deleteById(crmCompanyId.getId());
	}


	@Autowired
	public void setICrmCompanyService(ICrmCompanyService iCrmCompanyService) {
		this.iCrmCompanyService = iCrmCompanyService;
	}
}
