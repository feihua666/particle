package com.particle.global.security.security.login;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.dto.login.UserGrantedAuthority;
import com.particle.global.tool.thread.ThreadContextTool;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 登录相关工具，主要是控制一些登录逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023/11/1 10:53
 */
public class LoginTool {

    private  static String ignoreTenantResourceAndClearTenantLocalKey = "ignoreTenantResourceAndClearTenantLocal";


    /**
     * 用来判断是否忽略租户解析和清空本地线程已经解析到的租户信息
     * @return
     */
    public static boolean checkIgnoreTenantResolveAndClearTenantLocal() {
        Boolean b = (Boolean)ThreadContextTool.get(ignoreTenantResourceAndClearTenantLocalKey);
        return b != null && b;
    }

    /**
     * 设置忽略租户解析和清空本地线程已经解析到的租户信息
     */
    public static void doIgnoreTenantResolveAndClearTenantLocal() {
        ThreadContextTool.put(ignoreTenantResourceAndClearTenantLocalKey,true);
    }

    /**
     * 注意：必须先处理设置完信息后刷新
     * 刷新用户认证信息
     * 主要是重新刷新权限信息这在切换租户和角色时使用
     */
    public static void refreshAuthorities(LoginUser loginUser){
        List<UserGrantedAuthority> userGrantedAuthorities = loginUser.getUserGrantedAuthorities();
        List<SecurityUserGrantedAuthority> securityUserGrantedAuthorities = SecurityUserGrantedAuthority.create(userGrantedAuthorities);
        LoginTool.refreshAuthorities(securityUserGrantedAuthorities);
    }
    /**
     * 刷新用户认证信息
     * 主要是重新刷新权限信息这在切换租户和角色时使用
     */
    public static void refreshAuthorities(Collection<? extends GrantedAuthority> getAuthorities){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), getAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            throw new RuntimeException(StrUtil.format("token for type of {} is not support", authentication.getClass()));
        }
    }
}
