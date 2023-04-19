package com.particle.component.adapter.user.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import com.particle.global.security.security.login.GrantedPermission;
import com.particle.global.security.security.login.GrantedRole;
import com.particle.global.security.security.login.UserGrantedAuthority;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 单独加一个类，隔离模块不存在时不调用
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 12:08
 */
public class UserFuncRetrieve {

	@Autowired
	private IFuncService iFuncService;

	@Autowired
	private IRoleFuncRelService iRoleFuncRelService;
	@Qualifier(ConcurrencyConstants.default_global_asyn_slot_task_executor)
	@Autowired
	private ExecutorService asynSlotTaskExecutor;
	/**
	 * 根据角色获取
	 * @param roleDOS
	 * @return
	 */
	List<UserGrantedAuthority> retrieveRoleUserGrantedAuthorityByRoles(List<RoleDO> roleDOS) {
		List<UserGrantedAuthority> result = new ArrayList<>();
		Map<Long, List<GrantedPermission>> roleGrantedPermissions = getRoleFuncs(roleDOS);

		for (RoleDO roleDO : roleDOS) {
			GrantedRole grantedRole = GrantedRole.create(roleDO.getId(),roleDO.getCode(),roleDO.getName(),roleDO.getIsSuperadmin());
			List<GrantedPermission> grantedPermissions = roleGrantedPermissions.get(roleDO.getId());
			if (CollectionUtil.isEmpty(grantedPermissions)) {
				result.add(
						UserGrantedAuthority.create(grantedRole,null)
				);
				continue;
			}
			for (GrantedPermission grantedPermission : grantedPermissions) {
				result.add(
						UserGrantedAuthority.create(grantedRole,grantedPermission)
				);
			}

		}
		return result;

	}


	/**
	 * 每一个角色下的权限
	 * @param roleDOS
	 * @return
	 */
	private Map<Long,List<GrantedPermission>> getRoleFuncs(List<RoleDO> roleDOS){
		List<Long> roleIds = roleDOS.stream().map(RoleDO::getId).collect(Collectors.toList());
		List<RoleFuncRelDO> byRoleIds = iRoleFuncRelService.listByColumns(roleIds,RoleFuncRelDO::getRoleId);
		if (CollectionUtil.isEmpty(byRoleIds)) {
			return Collections.EMPTY_MAP;
		}
		List<Long> funcIds = byRoleIds.stream().map(RoleFuncRelDO::getFuncId).collect(Collectors.toList());

		if (CollectionUtil.isEmpty(funcIds)) {
			return Collections.EMPTY_MAP;
		}

		List<FuncDO> funcDOS = iFuncService.listByFuncIds(funcIds,false);

		if (CollectionUtil.isEmpty(funcDOS)) {
			return Collections.EMPTY_MAP;
		}

		Map<Long, RoleDO> longRoleDOMap = roleDOS.stream().collect(Collectors.toMap(RoleDO::getId, Function.identity()));

		Map<Long, Long> longLongMap = byRoleIds.stream().collect(Collectors.toMap(RoleFuncRelDO::getFuncId, RoleFuncRelDO::getRoleId));

		Map<Long, List<GrantedPermission>> result = new HashMap<>();
		for (FuncDO funcDO : funcDOS) {
			RoleDO roleDO = longRoleDOMap.get(longLongMap.get(funcDO.getId()));

			List<GrantedPermission> grantedPermissions = result.get(roleDO.getId());
			if (grantedPermissions == null) {
				grantedPermissions = new ArrayList<>();
				result.put(roleDO.getId(), grantedPermissions);
			}
			if (StrUtil.isNotEmpty(funcDO.getPermissions())) {
				// 将权限以逗号分隔处理，建议在添加权限时不到添加逗号，在 controller中手动指定，更明确
				for (String permission : funcDO.getPermissions().split(",")) {


					grantedPermissions.add(
							GrantedPermission.create(funcDO.getId(),
									permission,
									funcDO.getName(),
									//	类型暂不添加
									null,
									GrantedPermission.Source.role,
									roleDO.getId()
									)
					);
				}// end inner for
			}else {
				// 将空的权限也添加上，保证角色的分配功能授权完整
				grantedPermissions.add(
						GrantedPermission.create(funcDO.getId(),
								funcDO.getPermissions(),
								funcDO.getName(),
								//	类型暂不添加
								null,
								GrantedPermission.Source.role,
								roleDO.getId()
						)
				);
			}
		}

		return result;
	}
}
