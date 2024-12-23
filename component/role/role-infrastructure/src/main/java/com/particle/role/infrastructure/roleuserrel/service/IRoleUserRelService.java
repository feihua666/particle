package com.particle.role.infrastructure.roleuserrel.service;

import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;

import java.util.List;

/**
 * <p>
 * 角色用户关系 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleUserRelService extends IBaseService<RoleUserRelDO> {


	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	default List<RoleUserRelDO> getByUserId(Long userId) {
		return listByColumn(userId, RoleUserRelDO::getUserId);
	}
	/**
	 * 根据用户id查询
	 * @param userIds
	 * @return
	 */
	default List<RoleUserRelDO> getByUserIds(List<Long> userIds) {
		return listByColumns(userIds, RoleUserRelDO::getUserId);
	}
}
