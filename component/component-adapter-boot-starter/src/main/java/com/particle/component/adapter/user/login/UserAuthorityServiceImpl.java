package com.particle.component.adapter.user.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.security.security.login.*;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.role.infrastructure.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 获取用户角色
 * Created by yangwei
 * Created at 2020/12/11 18:16
 */
public class UserAuthorityServiceImpl implements UserAuthorityService {
    @Autowired
    private IRoleService iRoleService;

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
        List<RoleDO> roleDOS = iRoleService.getByUserId(loginUser.getId(), false);
        if (CollectionUtil.isEmpty(roleDOS)) {
            return Collections.emptyList();
        }

        if (userFuncRetrieve == null) {
            return Collections.emptyList();
        }

        return userFuncRetrieve.retrieveRoleUserGrantedAuthorityByRoles(roleDOS);
    }
}
