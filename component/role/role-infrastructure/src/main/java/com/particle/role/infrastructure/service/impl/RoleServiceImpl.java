package com.particle.role.infrastructure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.mapper.RoleMapper;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.role.infrastructure.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleServiceImpl extends IBaseServiceImpl<RoleMapper, RoleDO> implements IRoleService {

	@Autowired
	private IRoleUserRelService iRoleUserRelService;
	@Autowired
	private IRoleFuncRelService iRoleFuncRelService;

	private IBaseQueryCommandMapStruct<RoleDO> queryCommandMapStruct;

	@Override
	protected RoleDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<RoleDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}


	@Override
	public List<RoleDO> getByUserId(Long userId, Boolean isDisabled) {
		List<Long> roleIds = (List<Long>)iRoleUserRelService.listSingleColumnFieldByColumn(RoleUserRelDO::getRoleId,userId,RoleUserRelDO::getUserId);
		if (CollectionUtil.isEmpty(roleIds)) {
			return Collections.emptyList();
		}
		return getByRoleIds(roleIds,isDisabled);
	}

	@Override
	public List<RoleDO> getByFuncId(Long funcId,Boolean isDisabled) {
		List<Long> roleIds = (List<Long>)iRoleFuncRelService.listSingleColumnFieldByColumn(RoleFuncRelDO::getRoleId,funcId,RoleFuncRelDO::getFuncId);
		if (CollectionUtil.isEmpty(roleIds)) {
			return Collections.emptyList();
		}
		return getByRoleIds(roleIds,isDisabled);
	}
}