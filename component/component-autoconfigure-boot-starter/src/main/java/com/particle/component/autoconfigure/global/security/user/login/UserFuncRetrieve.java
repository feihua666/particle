package com.particle.component.autoconfigure.global.security.user.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.func.adapter.feign.client.rpc.FuncRpcFeignClient;
import com.particle.func.client.dto.command.representation.FuncQueryListByIdsCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.login.GrantedPermission;
import com.particle.global.dto.login.GrantedRole;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.dto.login.UserGrantedAuthority;
import com.particle.global.dto.response.MultiResponse;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListByRoleIdsCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.tenant.adapter.feign.client.tenantfunc.rpc.TenantFuncRpcFeignClient;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private FuncRpcFeignClient funcRpcFeignClient;
    @Autowired
    private RoleFuncRelRpcFeignClient roleFuncRelRpcFeignClient;

	/**
	 * 根据角色获取
	 * @param userGrantedAuthorities
	 * @return
	 */
	List<UserGrantedAuthority> retrieveRoleUserGrantedAuthorityByRoles(List<UserGrantedAuthority> userGrantedAuthorities, LoginUser loginUser) {
        List<UserGrantedAuthority> result = new ArrayList<>();
        List<GrantedRole> grantedRoleList = userGrantedAuthorities.stream().map(item -> item.getGrantedPermissionRole()).collect(Collectors.toList());
        Map<Long, List<GrantedPermission>> roleGrantedPermissions = getRoleFuncs(grantedRoleList);

		for (GrantedRole grantedRole : grantedRoleList) {
			List<GrantedPermission> grantedPermissions = roleGrantedPermissions.get(grantedRole.getId());
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


		boolean isTenantSuperAdmin = grantedRoleList.stream().anyMatch(r -> LoginUser.tenant_super_admin_role.equals(r.getCode()));
		// 如果是租户管理员，直接获取全部
		if (isTenantSuperAdmin) {
            List<UserGrantedAuthority> userGrantedAuthorities1 = retrieveGrantedAuthority(loginUser);
            result.addAll(userGrantedAuthorities1);
		}
		return result;

	}

	/**
	 * 直接获取所有的功能授权，不考虑角色
	 * 添加该方法主要是支持在多租户场景下，租户超级管理员角色无需分配功能即可拥有所有功能
     * 在子类中实现，方便隔离在租户组件不存在时也能正常使用
	 * @return
	 */
	public List<UserGrantedAuthority> retrieveGrantedAuthority(LoginUser loginUser) {
        return Collections.emptyList();

	}

	/**
	 * 每一个角色下的权限
	 * @param grantedRoles
	 * @return
	 */
	private Map<Long,List<GrantedPermission>> getRoleFuncs(List<GrantedRole> grantedRoles){
		List<Long> roleIds = grantedRoles.stream().map(GrantedRole::getId).collect(Collectors.toList());
        RoleFuncRelQueryListByRoleIdsCommand roleFuncRelQueryListByRoleIdsCommand = new RoleFuncRelQueryListByRoleIdsCommand();
        roleFuncRelQueryListByRoleIdsCommand.setIds(roleIds);
        MultiResponse<RoleFuncRelVO> roleVOMultiResponse = roleFuncRelRpcFeignClient.queryListByRoleIds(roleFuncRelQueryListByRoleIdsCommand);
        List<RoleFuncRelVO> roleFuncRelVOS = roleVOMultiResponse.getData();
		if (CollectionUtil.isEmpty(roleFuncRelVOS)) {
			return Collections.EMPTY_MAP;
		}
		List<Long> funcIds = roleFuncRelVOS.stream().map(RoleFuncRelVO::getFuncId).collect(Collectors.toList());

		if (CollectionUtil.isEmpty(funcIds)) {
			return Collections.EMPTY_MAP;
		}

        FuncQueryListByIdsCommand funcQueryListByIdsCommand = new FuncQueryListByIdsCommand();
        funcQueryListByIdsCommand.setIds(funcIds);
        funcQueryListByIdsCommand.setIsDisabled(false);
        MultiResponse<FuncVO> funcVOMultiResponse = funcRpcFeignClient.queryListByIds(funcQueryListByIdsCommand);
        List<FuncVO> funcVOS = funcVOMultiResponse.getData();

		if (CollectionUtil.isEmpty(funcVOS)) {
			return Collections.EMPTY_MAP;
		}

		Map<Long, GrantedRole> longRoleDOMap = grantedRoles.stream().collect(Collectors.toMap(GrantedRole::getId, Function.identity()));

		Map<Long, Long> longLongMap = roleFuncRelVOS.stream().collect(Collectors.toMap(RoleFuncRelVO::getFuncId, RoleFuncRelVO::getRoleId));

		Map<Long, List<GrantedPermission>> result = new HashMap<>();
		for (FuncVO funcVO : funcVOS) {
            GrantedRole grantedRole = longRoleDOMap.get(longLongMap.get(funcVO.getId()));

			List<GrantedPermission> grantedPermissions = result.get(grantedRole.getId());
			if (grantedPermissions == null) {
				grantedPermissions = new ArrayList<>();
				result.put(grantedRole.getId(), grantedPermissions);
			}

			grantedPermissions.addAll(createGrantedPermission(funcVO, GrantedPermission.Source.role, grantedRole.getId()));
		}

		return result;
	}


	/**
	 * funcDo转授权
	 * @param funcVO
	 * @param source
	 * @param sourceId
	 * @return
	 */
	protected List<GrantedPermission> createGrantedPermission(FuncVO funcVO, GrantedPermission.Source source, Long sourceId) {
		List<GrantedPermission> grantedPermissions = new ArrayList<>();
		if (StrUtil.isNotEmpty(funcVO.getPermissions())) {
			// 将权限以逗号分隔处理，建议在添加权限时不到添加逗号，在 controller中手动指定，更明确
			for (String permission : funcVO.getPermissions().split(",")) {


				grantedPermissions.add(
						GrantedPermission.create(funcVO.getId(),
								permission,
								funcVO.getName(),
								//	类型暂不添加
								null,
								source,
								sourceId
						)
				);
			}// end inner for
		}else {
			// 将空的权限也添加上，保证角色的分配功能授权完整
			grantedPermissions.add(
					GrantedPermission.create(funcVO.getId(),
							funcVO.getPermissions(),
							funcVO.getName(),
							//	类型暂不添加
							null,
							source,
							sourceId
					)
			);
		}
		return grantedPermissions;
	}


    public static class UserFuncRetrieveSub extends UserFuncRetrieve{

        @Autowired(required = false)
        private TenantFuncRpcFeignClient tenantFuncRpcFeignClient;

        @Autowired
        private FuncRpcFeignClient funcRpcFeignClient;
        public List<UserGrantedAuthority> retrieveGrantedAuthority(LoginUser loginUser) {
            Long tenantId = Optional.ofNullable(loginUser.getCurrentTenant()).map(tenant -> {
                return tenant.getId();
            }).orElse(null);
            if (tenantId == null) {
                return Collections.emptyList();
            }
            TenantFuncQueryListCommand tenantFuncQueryListCommand = new TenantFuncQueryListCommand();
            tenantFuncQueryListCommand.setTenantId(tenantId);
            MultiResponse<TenantFuncVO> tenantFuncVOMultiResponse = tenantFuncRpcFeignClient.queryList(tenantFuncQueryListCommand);
            List<TenantFuncVO> tenantFuncVOList = tenantFuncVOMultiResponse.getData();
            if (CollectionUtil.isEmpty(tenantFuncVOList)) {
                return Collections.emptyList();
            }
            List<Long> funcIds = tenantFuncVOList.stream().map(item -> {
                return item.getFuncId();
            }).collect(Collectors.toList());
            FuncQueryListByIdsCommand funcQueryListByIdsCommand = new FuncQueryListByIdsCommand();
            funcQueryListByIdsCommand.setIds(funcIds);
            funcQueryListByIdsCommand.setIsDisabled(false);
            MultiResponse<FuncVO> funcVOMultiResponse = funcRpcFeignClient.queryListByIds(funcQueryListByIdsCommand);
            List<FuncVO> funcVOS = funcVOMultiResponse.getData();
            if (CollectionUtil.isEmpty(funcVOS)) {
                return Collections.emptyList();
            }
            List<GrantedPermission> grantedPermissions = new ArrayList<>(funcVOS.size());
            for (FuncVO funcVO : funcVOS) {
                grantedPermissions.addAll(createGrantedPermission(funcVO, GrantedPermission.Source.other, null));
            }
            Stream<UserGrantedAuthority> userGrantedAuthorityStream = grantedPermissions.stream().map(item -> UserGrantedAuthority.create(null, item));

            return userGrantedAuthorityStream.collect(Collectors.toList());

        }
    }
}
