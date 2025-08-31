package com.particle.component.adapter.user;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户添加监听，用来添加角色
 * </p>
 *
 * @author yangwei
 * @since 2023-05-26 16:38:47
 */
public class RoleUserAddServiceListener implements IAddServiceListener<UserDO> , IQueryWrapperHandler<UserDO> {

	@Autowired
	private IRoleUserRelService roleUserRelService;
	@Autowired
	private IRoleService roleService;

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
	/**
	 * 查询时，如果存在角色id，添加角色id查询条件，如果存在角色类型字典id，添加角色类型字典id查询条件
	 * @param queryWrapper
	 * @param queryForm
	 */
	@Override
	public void handle(QueryWrapper<UserDO> queryWrapper, QueryCommand queryForm) {
		Long roleId = null;
		Long roleTypeDictId = null;
		if (queryForm instanceof UserQueryListCommand) {
			roleId = ((UserQueryListCommand) queryForm).getRoleId();
		}else if (queryForm instanceof UserPageQueryCommand) {
			roleId = ((UserPageQueryCommand) queryForm).getRoleId();
		}

		if (roleId != null) {
			// 存在根据角色id查询，拼接查询条件
			List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.list(Wrappers.<RoleUserRelDO>lambdaQuery().eq(RoleUserRelDO::getRoleId, roleId));
			List<Long> collect = roleUserRelDOS.stream().map(RoleUserRelDO::getUserId).collect(Collectors.toList());
			if (collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(UserDO.COLUMN_ID,collect);
			}
		}
		// 查询某一类角色的 用户
		if (roleTypeDictId != null) {
			List<RoleDO> roleDOS = roleService.getByRoleTypeDictId(roleTypeDictId, null);
            if (roleDOS.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
            }else{
				List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.getByRoleIds(roleDOS.stream().map(RoleDO::getId).collect(Collectors.toList()));
				List<Long> collect = roleUserRelDOS.stream().map(RoleUserRelDO::getUserId).collect(Collectors.toList());
				if (collect.isEmpty()) {
					// 为空就是不存在，不存在，得加一个不存在的条件
					queryWrapper.apply("false");
				}else {
					queryWrapper.in(UserDO.COLUMN_ID,collect);
				}
			}

		}
	}
}
