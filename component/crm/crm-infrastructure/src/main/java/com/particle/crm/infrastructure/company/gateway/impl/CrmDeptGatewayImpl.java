package com.particle.crm.infrastructure.company.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.CrmDeptId;
import com.particle.crm.domain.company.gateway.CrmDeptGateway;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import com.particle.crm.infrastructure.company.service.ICrmDeptService;
import com.particle.crm.infrastructure.company.structmapping.CrmDeptInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 客户公司部门 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Component
public class CrmDeptGatewayImpl extends AbstractBaseGatewayImpl<CrmDeptId,CrmDept> implements CrmDeptGateway {

	private ICrmDeptService iCrmDeptService;

	@Override
	public CrmDept getById(CrmDeptId crmDeptId) {
		CrmDeptDO byId = iCrmDeptService.getById(crmDeptId.getId());
		CrmDept crmDept = DomainFactory.create(CrmDept.class);
		crmDept = CrmDeptInfrastructureStructMapping.instance. crmDeptDOToCrmDept(crmDept,byId);
		return crmDept;
	}

	@Override
	public boolean doSave(CrmDept crmDept) {
		CrmDeptDO crmDeptDO = CrmDeptInfrastructureStructMapping.instance.crmDeptToCrmDeptDO(crmDept);
		if (crmDeptDO.getId() == null) {
			crmDeptDO.setAddControl(crmDept.getAddControl());
			CrmDeptDO add = iCrmDeptService.add(crmDeptDO);
			crmDept.setId(CrmDeptId.of(add.getId()));
			return add != null;
		}
		crmDeptDO.setUpdateControl(crmDept.getUpdateControl());
		CrmDeptDO update = iCrmDeptService.update(crmDeptDO);
		return update != null;
	}

	@Override
	public boolean delete(CrmDeptId crmDeptId) {
		return iCrmDeptService.deleteById(crmDeptId.getId());
	}

	@Override
	public boolean delete(CrmDeptId id, IdCommand idCommand) {
		return iCrmDeptService.deleteById(idCommand);
	}

	@Autowired
	public void setICrmDeptService(ICrmDeptService iCrmDeptService) {
		this.iCrmDeptService = iCrmDeptService;
	}
}
