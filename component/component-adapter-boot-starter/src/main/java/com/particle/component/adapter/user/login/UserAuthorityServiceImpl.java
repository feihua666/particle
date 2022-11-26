package com.particle.component.adapter.user.login;

import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.user.adapter.login.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 获取用户角色
 * Created by yangwei
 * Created at 2020/12/11 18:16
 */
public class UserAuthorityServiceImpl implements UserAuthorityService {
    @Autowired
    private IRoleService iRoleService;
    @Override
    public List<String> retrieveUserAuthoritiesByUserId(Long userId) {
        return Optional.ofNullable(iRoleService.getByUserId(userId,false)).orElse(new ArrayList<>()).stream().map(RoleDO::getCode).collect(Collectors.toList());
    }
}
