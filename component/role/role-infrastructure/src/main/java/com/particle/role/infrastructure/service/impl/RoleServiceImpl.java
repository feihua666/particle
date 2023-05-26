package com.particle.role.infrastructure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	protected void preAdd(RoleDO po) {
		// 编码已存在不能添加
		assertByColumn(po.getCode(),RoleDO::getCode,false);
	}

	@Override
	protected void preUpdate(RoleDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			RoleDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),RoleDO::getCode,false);
			}
		}
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
	public Map<Long, List<RoleDO>> getByUserIds(List<Long> userIds, Boolean isDisabled) {
		List<RoleUserRelDO> roleUserRelDOS = iRoleUserRelService.getByUserIds(userIds);
		if (CollectionUtil.isEmpty(roleUserRelDOS)) {
			return Collections.emptyMap();
		}
		List<Long> roleIds = roleUserRelDOS.stream().map(RoleUserRelDO::getRoleId).collect(Collectors.toList());
		List<RoleDO> roleDOS = getByRoleIds(roleIds, isDisabled);
		if (CollectionUtil.isEmpty(roleDOS)) {
			return Collections.emptyMap();
		}
		// key = userId，value = roleIds
		Map<Long, List<Long>> roleUserRelDOSGroupingBy = roleUserRelDOS.stream().collect(Collectors.groupingBy(RoleUserRelDO::getUserId, Collectors.mapping(RoleUserRelDO::getRoleId, Collectors.toList())));

		Map<Long, List<RoleDO>> result = new HashMap<>(roleUserRelDOSGroupingBy.size());

		for (Map.Entry<Long, List<Long>> longListEntry : roleUserRelDOSGroupingBy.entrySet()) {
			result.put(longListEntry.getKey(), roleDOS.stream().filter(roleDO -> longListEntry.getValue().contains(roleDO.getId())).collect(Collectors.toList()));
		}
		return result;
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
