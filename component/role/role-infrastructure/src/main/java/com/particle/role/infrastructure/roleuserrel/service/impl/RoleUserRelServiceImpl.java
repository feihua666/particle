package com.particle.role.infrastructure.roleuserrel.service.impl;

import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.mapper.RoleUserRelMapper;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 角色用户关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleUserRelServiceImpl extends IBaseServiceImpl<RoleUserRelMapper, RoleUserRelDO> implements IRoleUserRelService {
	private IBaseQueryCommandMapStruct<RoleUserRelDO> queryCommandMapStruct;

	@Override
	protected RoleUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<RoleUserRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
