package com.particle.component.adapter.tenant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户添加监听，用来添加部门
 * </p>
 *
 * @author yangwei
 * @since 2023-05-17 09:50:35
 */
public class DeptTenantUserServiceListener implements IAddServiceListener<TenantUserDO>, IQueryWrapperHandler<TenantUserDO> {

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

	@Override
	public void handle(QueryWrapper<TenantUserDO> queryWrapper, QueryCommand queryForm) {
		Long deptId = null;
		if (queryForm instanceof TenantUserQueryListCommand) {
			deptId = ((TenantUserQueryListCommand) queryForm).getDeptId();
		}else if (queryForm instanceof TenantUserPageQueryCommand) {
			deptId = ((TenantUserPageQueryCommand) queryForm).getDeptId();
		}

		if (deptId != null) {
			// 存在根据部门id查询，拼接查询条件
			List<DeptUserRelDO> deptUserRelDOS = deptUserRelService.list(Wrappers.<DeptUserRelDO>lambdaQuery().eq(DeptUserRelDO::getDeptId, deptId));
			List<Long> collect = deptUserRelDOS.stream().map(DeptUserRelDO::getUserId).collect(Collectors.toList());
			if (collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(TenantUserDO.COLUMN_USER_ID,collect);
			}
		}
	}
}
