package com.particle.component.adapter.tenant;

import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户添加监听，用来添加部门
 * </p>
 *
 * @author yangwei
 * @since 2023-05-17 09:50:35
 */
public class DeptTenantUserServiceListener implements IAddServiceListener<TenantUserDO>{

	@Autowired
	private IDeptUserRelService deptUserRelService;

	@Override
	public void postAdd(TenantUserDO po) {
		// 如果部门id不为空，添加部门关系
		if (po.getAddControl() instanceof TenantUserCreateCommand) {
			TenantUserCreateCommand userCreateCommand = (TenantUserCreateCommand) po.getAddControl();
			if (userCreateCommand.getDeptId() != null) {
				DeptUserRelDO deptUserRelDO = new DeptUserRelDO();
				deptUserRelDO.setDeptId(userCreateCommand.getDeptId());
				deptUserRelDO.setUserId(po.getUserId());
				deptUserRelService.add(deptUserRelDO);
			}
		}
	}

}
