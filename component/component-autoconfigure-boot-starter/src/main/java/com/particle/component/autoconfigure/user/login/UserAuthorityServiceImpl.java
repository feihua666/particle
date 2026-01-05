package com.particle.component.autoconfigure.user.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.security.security.login.GrantedRole;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.UserAuthorityService;
import com.particle.global.security.security.login.UserGrantedAuthority;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.client.dto.command.representation.RoleQueryListByUserIdCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 获取用户角色
 * Created by yangwei
 * Created at 2020/12/11 18:16
 */
public class UserAuthorityServiceImpl implements UserAuthorityService {

    @Autowired
    private RoleRpcFeignClient roleRpcFeignClient;

    @Autowired(required = false)
    private UserFuncRetrieve userFuncRetrieve;

    @Override
    public List<UserGrantedAuthority> retrieveUserAuthoritiesByUserId( LoginUser loginUser) {
        List<UserGrantedAuthority> result = new ArrayList<>();
        List<UserGrantedAuthority> userRoleGrantedAuthorities = retrieveRoleUserGrantedAuthorityByUserId(loginUser);
        result.addAll(userRoleGrantedAuthorities);

        return result;
    }

    /**
     * 根据角色获取对应的授权信息
     * @param loginUser
     * @return
     */
    private  List<UserGrantedAuthority> retrieveRoleUserGrantedAuthorityByUserId( LoginUser loginUser) {
        RoleQueryListByUserIdCommand roleQueryListByUserIdCommand = new RoleQueryListByUserIdCommand();
        roleQueryListByUserIdCommand.setId(loginUser.getId());
        roleQueryListByUserIdCommand.setIsDisabled( false);
        MultiResponse<RoleVO> roleVOMultiResponse = roleRpcFeignClient.queryListByUserId(roleQueryListByUserIdCommand);
        List<RoleVO> roleVOS = roleVOMultiResponse.getData();
        if (CollectionUtil.isEmpty(roleVOS)) {
            return Collections.emptyList();
        }
        List<UserGrantedAuthority> result = new ArrayList<>();
        for (RoleVO roleVO : roleVOS) {
            GrantedRole grantedRole = GrantedRole.create(roleVO.getId(),roleVO.getCode(),roleVO.getName(),roleVO.getIsSuperadmin());
            UserGrantedAuthority userGrantedAuthority = UserGrantedAuthority.create(grantedRole, null);
            result.add(userGrantedAuthority);
        }

        if (userFuncRetrieve == null) {
            return result;
        }

        return userFuncRetrieve.retrieveRoleUserGrantedAuthorityByRoles(result,loginUser);
    }
}
