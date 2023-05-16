package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.thread.ThreadContextTool;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 当前登录用户工具类，方便在任何地方直接获取当前登录用户
 * 但尽量不要在 service 中过渡使用或者就干脆不使用为好，尽量保持方法调用登录用户参数传递
 * 注意：particle 中暂不支持在线程池中直接调用
 * @Author yangwei
 * @since 2020/10/13 14:57
 */
public class LoginUserTool {

    public static final String LOGIN_USER_SESSION_KEY = "login_user_session_key";
    public static final String LOGIN_USER_ANONYMOUS_KEY = "login_user_anonymous_key";
    
    public static Long getLoginUserId(){
        return Optional.ofNullable(getLoginUser()).map(LoginUser::getId).orElse(null);
    }
    /**
     * 从thread中获取用户
     * @return
     */
    public static LoginUser getLoginUser() {
        return (LoginUser) ThreadContextTool.get(LOGIN_USER_SESSION_KEY);
    }
    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public static LoginUser retrieveFromSession(HttpServletRequest request) {
        Assert.notNull(request,"request为空，请查检传入参数");
        return (LoginUser) request.getSession().getAttribute(LOGIN_USER_SESSION_KEY);
    }

    /**
     * 设置当前登录用户到session，一般登录成功后使用
     * @param user
     * @param request
     */
    public static void saveToSession(LoginUser user, HttpServletRequest request) {
        ThreadContextTool.put(LOGIN_USER_SESSION_KEY,user);
        request.getSession().setAttribute(LOGIN_USER_SESSION_KEY,user);
    }

    public static Boolean isAnonymous() {
        return (Boolean) ThreadContextTool.get(LOGIN_USER_ANONYMOUS_KEY);
    }

    public static void setAnonymous(Boolean anonymous) {
        ThreadContextTool.put(LOGIN_USER_ANONYMOUS_KEY,anonymous);
    }

    /**
     * 设置当前登录用户到threadContext，一般登录成功后使用
     * @param user
     */
    public static void saveToThreadContext(LoginUser user) {
        ThreadContextTool.put(LOGIN_USER_SESSION_KEY,user);
    }

    public static void clear(){
        ThreadContextTool.remove(LOGIN_USER_ANONYMOUS_KEY);
        ThreadContextTool.remove(LOGIN_USER_SESSION_KEY);
    }

    /**
     * 获取登录用户的角色id
     * @return
     */
    public static List<Long> getLoginUserRoleIds(){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return Collections.emptyList();
        }
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        if (CollectionUtil.isEmpty(authorities)) {
            return Collections.emptyList();
        }
        Set<Long> result = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority instanceof UserGrantedAuthority) {
                Optional.ofNullable(((UserGrantedAuthority) authority).getGrantedPermissionRole()).ifPresent(grantedRole -> {
                    result.add(grantedRole.getId());
                });
            }
        }
        return result.stream().collect(Collectors.toList());
    }
    /**
     * 获取登录用户的权限id
     * @return
     */
    public static List<Long> getLoginUserPermissionIds(){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return Collections.emptyList();
        }
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        if (CollectionUtil.isEmpty(authorities)) {
            return Collections.emptyList();
        }
        Set<Long> result = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            if (authority instanceof UserGrantedAuthority) {
                Optional.ofNullable(((UserGrantedAuthority) authority).getGrantedPermission()).ifPresent(grantedPermission -> {
                    result.add(grantedPermission.getId());
                });
            }
        }
        return result.stream().collect(Collectors.toList());
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
