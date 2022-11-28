package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.thread.ThreadContextTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * 抽象获取用户信息基类
 * 所有登录实现建议以该类为基类
 * Created by yangwei
 * Created at 2020/12/10 20:59
 */
public abstract class AbstractUserDetailsService implements UserDetailsService {

    public static String login_loaded_user_in_threadcontext_key ="loginLoadedUser";

    @Autowired(required = false)
    private UserAuthorityService userAuthorityService;

    @Autowired(required = false)
    private List<LoginUserExtPutService> loginUserExtPutServices;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser loginUser = doLoadUserByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 默认添加用户权限
        loginUser.addAuthority(UserGrantedAuthority.userOnlyGrantedAuthority());
        if (userAuthorityService != null) {
            List<UserGrantedAuthority> list = userAuthorityService.retrieveUserAuthoritiesByUserId(loginUser.getId());
            if (CollectionUtil.isNotEmpty(list)) {
                long superAdminRoleCount = list.stream().filter(item ->
                        StrUtil.equals(LoginUser.super_admin_role, Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getCode).orElse(null))
                ).count();
                loginUser.setIsSuperAdmin(superAdminRoleCount > 0);
            }
            loginUser.addAuthority(list);
        }

        if (loginUserExtPutServices != null) {
            for (LoginUserExtPutService loginUserExtPutService : loginUserExtPutServices) {
                loginUserExtPutService.addExt(loginUser);
            }
        }
        // 在线程中放置一个用户，不管用户密码对不对，都可以在后期获取到，主要用来记录用户登录日志
        ThreadContextTool.put(login_loaded_user_in_threadcontext_key,loginUser);
        return loginUser;
    }

    public abstract LoginUser doLoadUserByUsername(String username);
}
