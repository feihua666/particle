package com.particle.component.autoconfigure.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户用户添加监听，用来添加角色
 * </p>
 *
 * @author yangwei
 * @since 2023-05-26 16:38:47
 */
public class RoleTenantUserServiceListener implements IAddServiceListener<TenantUserDO>, IDeleteServiceListener<TenantUserDO> {

	@Autowired
	private RoleUserRelRpcFeignClient roleUserRelRpcFeignClient;

	@Override
	public void postAdd(TenantUserDO po) {
		List<Long> roleIds = null;
		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof TenantUserCreateCommand) {
				roleIds = ((TenantUserCreateCommand) po.getAddControl()).getRoleIds();
			}
		}
        if (CollectionUtil.isNotEmpty(roleIds)) {
            return;
        }
        UserAssignRoleCommand userAssignRoleCommand = new UserAssignRoleCommand();
        userAssignRoleCommand.setUserId(po.getUserId());
        userAssignRoleCommand.setCheckedRoleIds(roleIds);
        roleUserRelRpcFeignClient.userAssignRole(userAssignRoleCommand);
	}

	@Override
	public void postDeleteById(Long id, TenantUserDO tenantUserDO) {
        IdCommand idCommand = IdCommand.create(tenantUserDO.getUserId());
        roleUserRelRpcFeignClient.deleteByUserId(idCommand);
	}
	@Override
	public void postDeleteByColumn(Object columnId, SFunction<TenantUserDO, ?> column, List<TenantUserDO> tenantUserDOS) {
		List<Long> userIds = tenantUserDOS.stream().map(TenantUserDO::getUserId).collect(Collectors.toList());
		for (Long userId : userIds) {
            IdCommand idCommand = IdCommand.create(userId);
            roleUserRelRpcFeignClient.deleteByUserId(idCommand);
		}
	}
}
