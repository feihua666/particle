package com.particle.dept.infrastructure.gateway.impl;

import com.particle.dept.domain.Dept;
import com.particle.dept.domain.DeptId;
import com.particle.dept.domain.gateway.DeptGateway;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.structmapping.DeptInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 部门 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Component
public class DeptGatewayImpl extends AbstractBaseGatewayImpl<DeptId,Dept> implements DeptGateway {

	private IDeptService iDeptService;

	@Override
	public Dept getById(DeptId deptId) {
		DeptDO byId = iDeptService.getById(deptId.getId());
		Dept dept = DomainFactory.create(Dept.class);
		dept = DeptInfrastructureStructMapping.instance. deptDOToDept(dept,byId);
		return dept;
	}

	@Override
	public boolean doSave(Dept dept) {
		DeptDO deptDO = DeptInfrastructureStructMapping.instance.deptToDeptDO(dept);
		if (deptDO.getId() == null) {
			deptDO.setAddControl(dept.getAddControl());
			DeptDO add = iDeptService.add(deptDO);
			dept.setId(DeptId.of(add.getId()));
			return add != null;
		}
		deptDO.setUpdateControl(dept.getUpdateControl());
		DeptDO update = iDeptService.update(deptDO);
		return update != null;
	}

	@Override
	public boolean delete(DeptId deptId) {
		return iDeptService.deleteById(deptId.getId());
	}

	@Override
	public boolean delete(DeptId id, IdCommand idCommand) {
		return iDeptService.deleteById(idCommand);
	}

	@Autowired
	public void setIDeptService(IDeptService iDeptService) {
		this.iDeptService = iDeptService;
	}
}
