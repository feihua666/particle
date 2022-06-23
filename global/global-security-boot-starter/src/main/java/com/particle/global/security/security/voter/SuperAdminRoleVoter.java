package com.particle.global.security.security.voter;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.security.login.LoginUser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 超级管理员投票器，如果是超级管理员角色则通过
 * @Author yangwei
 * Created at 2020/1/14 9:52
 */
public class SuperAdminRoleVoter extends RoleVoter {

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        Object principal = authentication.getPrincipal();
        if (principal != null && principal instanceof LoginUser && ((LoginUser) principal).getIsSuperAdmin()) {
            return ACCESS_GRANTED;
        }
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (StrUtil.equalsAny(authority.getAuthority(),LoginUser.super_admin_role,getRolePrefix() + LoginUser.super_admin_role)) {
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_ABSTAIN;
    }
}
