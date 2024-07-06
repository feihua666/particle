package com.particle.dept.infrastructure.depttreeuserrel.gateway.impl;

import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRelId;
import com.particle.dept.domain.depttreeuserrel.gateway.DeptTreeUserRelGateway;
import com.particle.dept.infrastructure.depttreeuserrel.service.IDeptTreeUserRelService;
import com.particle.dept.infrastructure.depttreeuserrel.dos.DeptTreeUserRelDO;
import com.particle.dept.infrastructure.depttreeuserrel.structmapping.DeptTreeUserRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 部门树用户关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Component
public class DeptTreeUserRelGatewayImpl extends AbstractBaseGatewayImpl<DeptTreeUserRelId,DeptTreeUserRel> implements DeptTreeUserRelGateway {

	private IDeptTreeUserRelService iDeptTreeUserRelService;

	@Override
	public DeptTreeUserRel getById(DeptTreeUserRelId deptTreeUserRelId) {
		DeptTreeUserRelDO byId = iDeptTreeUserRelService.getById(deptTreeUserRelId.getId());
		DeptTreeUserRel deptTreeUserRel = DomainFactory.create(DeptTreeUserRel.class);
		deptTreeUserRel = DeptTreeUserRelInfrastructureStructMapping.instance. deptTreeUserRelDOToDeptTreeUserRel(deptTreeUserRel,byId);
		return deptTreeUserRel;
	}

	@Override
	public boolean doSave(DeptTreeUserRel deptTreeUserRel) {
		DeptTreeUserRelDO deptTreeUserRelDO = DeptTreeUserRelInfrastructureStructMapping.instance.deptTreeUserRelToDeptTreeUserRelDO(deptTreeUserRel);
		if (deptTreeUserRelDO.getId() == null) {
			deptTreeUserRelDO.setAddControl(deptTreeUserRel.getAddControl());
			DeptTreeUserRelDO add = iDeptTreeUserRelService.add(deptTreeUserRelDO);
			deptTreeUserRel.setId(DeptTreeUserRelId.of(add.getId()));
			return add != null;
		}
		deptTreeUserRelDO.setUpdateControl(deptTreeUserRel.getUpdateControl());
		DeptTreeUserRelDO update = iDeptTreeUserRelService.update(deptTreeUserRelDO);
		return update != null;
	}

	@Override
	public boolean delete(DeptTreeUserRelId deptTreeUserRelId) {
		return iDeptTreeUserRelService.deleteById(deptTreeUserRelId.getId());
	}

	@Override
	public boolean delete(DeptTreeUserRelId id, IdCommand idCommand) {
		return iDeptTreeUserRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIDeptTreeUserRelService(IDeptTreeUserRelService iDeptTreeUserRelService) {
		this.iDeptTreeUserRelService = iDeptTreeUserRelService;
	}
}
