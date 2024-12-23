package com.particle.component.adapter.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
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
	private IRoleUserRelService roleUserRelService;

	@Override
	public void postAdd(TenantUserDO po) {
		List<Long> roleIds = null;
		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof TenantUserCreateCommand) {
				roleIds = ((TenantUserCreateCommand) po.getAddControl()).getRoleIds();
				if (CollectionUtil.isEmpty(roleIds)) {
					return;
				}
			}
		}
		roleUserRelService.assignRel(po.getUserId(), roleIds, (relDto) -> new RoleUserRelDO().setUserId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
	}

	@Override
	public void postDeleteById(Long id, TenantUserDO tenantUserDO) {
		roleUserRelService.deleteByColumn(tenantUserDO.getUserId(), RoleUserRelDO::getUserId);
	}
	@Override
	public void postDeleteByColumn(Object columnId, SFunction<TenantUserDO, ?> column, List<TenantUserDO> tenantUserDOS) {
		List<Long> userIds = tenantUserDOS.stream().map(TenantUserDO::getUserId).collect(Collectors.toList());
		for (Long userId : userIds) {
			roleUserRelService.deleteByColumn(userId, RoleUserRelDO::getUserId);
		}
	}
}
