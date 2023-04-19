package com.particle.dept.infrastructure.gateway.impl;

import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.DeptTreeId;
import com.particle.dept.domain.gateway.DeptTreeGateway;
import com.particle.dept.infrastructure.service.IDeptTreeService;
import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.dept.infrastructure.structmapping.DeptTreeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 部门树 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Component
public class DeptTreeGatewayImpl extends AbstractBaseGatewayImpl<DeptTreeId,DeptTree> implements DeptTreeGateway {

	private IDeptTreeService iDeptTreeService;

	@Override
	public DeptTree getById(DeptTreeId deptTreeId) {
		DeptTreeDO byId = iDeptTreeService.getById(deptTreeId.getId());
		DeptTree deptTree = DomainFactory.create(DeptTree.class);
		deptTree = DeptTreeInfrastructureStructMapping.instance. deptTreeDOToDeptTree(deptTree,byId);
		return deptTree;
	}

	@Override
	public boolean doSave(DeptTree deptTree) {
		DeptTreeDO deptTreeDO = DeptTreeInfrastructureStructMapping.instance.deptTreeToDeptTreeDO(deptTree);
		if (deptTreeDO.getId() == null) {
			deptTreeDO.setAddControl(deptTree.getAddControl());
			DeptTreeDO add = iDeptTreeService.add(deptTreeDO);
			deptTree.setId(DeptTreeId.of(add.getId()));
			return add != null;
		}
		deptTreeDO.setUpdateControl(deptTree.getUpdateControl());
		DeptTreeDO update = iDeptTreeService.update(deptTreeDO);
		return update != null;
	}

	@Override
	public boolean delete(DeptTreeId deptTreeId) {
		return iDeptTreeService.deleteById(deptTreeId.getId());
	}


	@Autowired
	public void setIDeptTreeService(IDeptTreeService iDeptTreeService) {
		this.iDeptTreeService = iDeptTreeService;
	}
}
