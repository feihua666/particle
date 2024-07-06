package com.particle.dept.infrastructure.gateway.impl;

import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.DeptTreeNameId;
import com.particle.dept.domain.gateway.DeptTreeNameGateway;
import com.particle.dept.infrastructure.service.IDeptTreeNameService;
import com.particle.dept.infrastructure.dos.DeptTreeNameDO;
import com.particle.dept.infrastructure.structmapping.DeptTreeNameInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 部门树名称 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Component
public class DeptTreeNameGatewayImpl extends AbstractBaseGatewayImpl<DeptTreeNameId,DeptTreeName> implements DeptTreeNameGateway {

	private IDeptTreeNameService iDeptTreeNameService;

	@Override
	public DeptTreeName getById(DeptTreeNameId deptTreeNameId) {
		DeptTreeNameDO byId = iDeptTreeNameService.getById(deptTreeNameId.getId());
		DeptTreeName deptTreeName = DomainFactory.create(DeptTreeName.class);
		deptTreeName = DeptTreeNameInfrastructureStructMapping.instance. deptTreeNameDOToDeptTreeName(deptTreeName,byId);
		return deptTreeName;
	}

	@Override
	public boolean doSave(DeptTreeName deptTreeName) {
		DeptTreeNameDO deptTreeNameDO = DeptTreeNameInfrastructureStructMapping.instance.deptTreeNameToDeptTreeNameDO(deptTreeName);
		if (deptTreeNameDO.getId() == null) {
			deptTreeNameDO.setAddControl(deptTreeName.getAddControl());
			DeptTreeNameDO add = iDeptTreeNameService.add(deptTreeNameDO);
			deptTreeName.setId(DeptTreeNameId.of(add.getId()));
			return add != null;
		}
		deptTreeNameDO.setUpdateControl(deptTreeName.getUpdateControl());
		DeptTreeNameDO update = iDeptTreeNameService.update(deptTreeNameDO);
		return update != null;
	}

	@Override
	public boolean delete(DeptTreeNameId deptTreeNameId) {
		return iDeptTreeNameService.deleteById(deptTreeNameId.getId());
	}

	@Override
	public boolean delete(DeptTreeNameId id, IdCommand idCommand) {
		return iDeptTreeNameService.deleteById(idCommand);
	}

	@Autowired
	public void setIDeptTreeNameService(IDeptTreeNameService iDeptTreeNameService) {
		this.iDeptTreeNameService = iDeptTreeNameService;
	}
}
