package com.particle.component.autoconfigure.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserPageQueryCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
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
public class DeptTenantUserServiceListener implements IAddServiceListener<TenantUserDO>, IQueryWrapperHandler<TenantUserDO>, IDeleteServiceListener<TenantUserDO> {

	@Autowired
	private DeptUserRelRpcFeignClient deptUserRelRpcFeignClient;

	@Override
	public void postAdd(TenantUserDO po) {
		// 如果部门id不为空，添加部门关系
		if (po.getAddControl() instanceof TenantUserCreateCommand) {
			TenantUserCreateCommand userCreateCommand = (TenantUserCreateCommand) po.getAddControl();
			if (userCreateCommand.getDeptId() != null) {
                DeptUserRelCreateCommand deptUserRelCreateCommand = new DeptUserRelCreateCommand();
				deptUserRelCreateCommand.setDeptId(userCreateCommand.getDeptId());
				deptUserRelCreateCommand.setUserId(po.getUserId());
				deptUserRelRpcFeignClient.create(deptUserRelCreateCommand);
			}
		}
	}

	@Override
	public void postDeleteById(Long id, TenantUserDO tenantUserDO) {
        IdCommand deleteCommand = IdCommand.create(tenantUserDO.getUserId());
        deptUserRelRpcFeignClient.deleteByUserId(deleteCommand);

	}
	@Override
	public void postDeleteByColumn(Object columnId, SFunction<TenantUserDO, ?> column, List<TenantUserDO> tenantUserDOS) {
		List<Long> userIds = tenantUserDOS.stream().map(TenantUserDO::getUserId).collect(Collectors.toList());
		for (Long userId : userIds) {
            IdCommand deleteCommand = IdCommand.create(userId);
            deptUserRelRpcFeignClient.deleteByUserId(deleteCommand);
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
            DeptUserRelQueryListCommand deptUserRelQueryListCommand = new DeptUserRelQueryListCommand();
            deptUserRelQueryListCommand.setDeptId(deptId);
            MultiResponse<DeptUserRelVO> deptUserRelVOMultiResponse = deptUserRelRpcFeignClient.queryList(deptUserRelQueryListCommand);
            List<DeptUserRelVO> deptUserRelVOS = deptUserRelVOMultiResponse.getData();
            if (CollectionUtil.isEmpty(deptUserRelVOS)) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
            }else {
                List<Long> userIds = deptUserRelVOS.stream().map(DeptUserRelVO::getUserId).collect(Collectors.toList());
                queryWrapper.in(TenantUserDO.COLUMN_USER_ID,userIds);
            }
		}
	}
}
