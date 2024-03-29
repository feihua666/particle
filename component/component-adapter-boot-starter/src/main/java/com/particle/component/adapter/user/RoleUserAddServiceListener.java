package com.particle.component.adapter.user;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 用户添加监听，用来添加角色
 * </p>
 *
 * @author yangwei
 * @since 2023-05-26 16:38:47
 */
public class RoleUserAddServiceListener implements IAddServiceListener<UserDO> {

	@Autowired
	private IRoleUserRelService roleUserRelService;

	@Override
	public void postAdd(UserDO po) {
		List<Long> roleIds = null;
		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof UserCreateCommand) {
				roleIds = ((UserCreateCommand) po.getAddControl()).getRoleIds();
				if (CollectionUtil.isEmpty(roleIds)) {
					return;
				}
			}
		}


		roleUserRelService.assignRel(po.getId(), roleIds, (relDto) -> new RoleUserRelDO().setUserId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
	}
}
