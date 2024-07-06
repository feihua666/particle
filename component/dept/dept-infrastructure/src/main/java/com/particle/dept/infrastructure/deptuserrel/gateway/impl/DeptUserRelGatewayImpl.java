package com.particle.dept.infrastructure.deptuserrel.gateway.impl;

import com.particle.dept.domain.deptuserrel.DeptUserRel;
import com.particle.dept.domain.deptuserrel.DeptUserRelId;
import com.particle.dept.domain.deptuserrel.gateway.DeptUserRelGateway;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.structmapping.DeptUserRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 部门用户关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Component
public class DeptUserRelGatewayImpl extends AbstractBaseGatewayImpl<DeptUserRelId,DeptUserRel> implements DeptUserRelGateway {

	private IDeptUserRelService iDeptUserRelService;

	@Override
	public DeptUserRel getById(DeptUserRelId deptUserRelId) {
		DeptUserRelDO byId = iDeptUserRelService.getById(deptUserRelId.getId());
		DeptUserRel deptUserRel = DomainFactory.create(DeptUserRel.class);
		deptUserRel = DeptUserRelInfrastructureStructMapping.instance. deptUserRelDOToDeptUserRel(deptUserRel,byId);
		return deptUserRel;
	}

	@Override
	public boolean doSave(DeptUserRel deptUserRel) {
		DeptUserRelDO deptUserRelDO = DeptUserRelInfrastructureStructMapping.instance.deptUserRelToDeptUserRelDO(deptUserRel);
		if (deptUserRelDO.getId() == null) {
			deptUserRelDO.setAddControl(deptUserRel.getAddControl());
			DeptUserRelDO add = iDeptUserRelService.add(deptUserRelDO);
			deptUserRel.setId(DeptUserRelId.of(add.getId()));
			return add != null;
		}
		deptUserRelDO.setUpdateControl(deptUserRel.getUpdateControl());
		DeptUserRelDO update = iDeptUserRelService.update(deptUserRelDO);
		return update != null;
	}

	@Override
	public boolean delete(DeptUserRelId deptUserRelId) {
		return iDeptUserRelService.deleteById(deptUserRelId.getId());
	}

	@Override
	public boolean delete(DeptUserRelId id, IdCommand idCommand) {
		return iDeptUserRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIDeptUserRelService(IDeptUserRelService iDeptUserRelService) {
		this.iDeptUserRelService = iDeptUserRelService;
	}
}
