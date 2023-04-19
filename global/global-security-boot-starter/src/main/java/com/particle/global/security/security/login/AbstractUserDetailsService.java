package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.IUserTenantChangeListener;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.global.tool.thread.ThreadContextTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 抽象获取用户信息基类
 * 所有登录实现建议以该类为基类
 * Created by yangwei
 * Created at 2020/12/10 20:59
 */
@Slf4j
public abstract class AbstractUserDetailsService implements UserDetailsService {

    public static String login_loaded_user_in_threadcontext_key ="loginLoadedUser";

    @Autowired(required = false)
    private UserTenantService userTenantService;

    @Autowired(required = false)
    private List<IUserTenantChangeListener> iUserTenantChangeListeners;

    @Autowired(required = false)
    private UserAuthorityService userAuthorityService;

    @Autowired(required = false)
    private List<LoginUserExtPutService> loginUserExtPutServices;

    @Qualifier(ConcurrencyConstants.default_global_asyn_slot_task_executor)
    @Autowired
    private ExecutorService asynSlotTaskExecutor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser loginUser = doLoadUserByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (userTenantService != null) {
            List<GrantedTenant> grantedTenants = userTenantService.retrieveUserTenantByUserId(loginUser.getId());
            loginUser.setTenants(grantedTenants);
            if (CollectionUtil.isNotEmpty(grantedTenants)) {
                // 默认使用第一个租户
                loginUser.setCurrentTenant(grantedTenants.iterator().next());
                if (iUserTenantChangeListeners != null) {
                    for (IUserTenantChangeListener iUserTenantChangeListener : iUserTenantChangeListeners) {
                        iUserTenantChangeListener.onTenantChanged(loginUser.getCurrentTenant(),null);
                    }
                }
            }
        }
        // 默认添加用户权限
        loginUser.addAuthority(UserGrantedAuthority.userGrantedAuthority);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        if (userAuthorityService != null) {
            Long tenantId = TenantTool.getTenantId();
            asynSlotTaskExecutor.execute(() -> {
                try {
                    TenantTool.setTenantId(tenantId);
                    List<UserGrantedAuthority> list = userAuthorityService.retrieveUserAuthoritiesByUserId(loginUser.getId());
                    if (CollectionUtil.isNotEmpty(list)) {
                        long superAdminRoleCount = list.stream().filter(item ->
                                // 包括超级管理员编码或角色设置了超级管理员
                                StrUtil.equals(LoginUser.super_admin_role, Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getCode).orElse(null))
                                || Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getIsSuperadmin).orElse(false)
                        ).count();
                        loginUser.setIsSuperAdmin(superAdminRoleCount > 0);
                    }
                    loginUser.addAuthority(list);
                } finally {
                    countDownLatch.countDown();
                    TenantTool.clear();

                }
            });

        }else {
            countDownLatch.countDown();
        }

        if (loginUserExtPutServices != null) {
            Long tenantId = TenantTool.getTenantId();
            asynSlotTaskExecutor.execute(() -> {
                try {
                    TenantTool.setTenantId(tenantId);
                    for (LoginUserExtPutService loginUserExtPutService : loginUserExtPutServices) {
                        loginUserExtPutService.addExt(loginUser);
                    }
                } finally {
                    countDownLatch.countDown();
                    TenantTool.clear();
                }
            });
        }else {
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            String msg = "用户不存在";
            log.error("用户 username={} 登录被中断，已抛出 {} 异常",username,msg,e);
            // 在等待过程中，有可能被中断，比如系统 shutdown 停机
            throw new UsernameNotFoundException(msg);
        }
        // 在线程中放置一个用户，不管用户密码对不对，都可以在后期获取到，主要用来记录用户登录日志
        ThreadContextTool.put(login_loaded_user_in_threadcontext_key,loginUser);
        return loginUser;
    }

    public abstract LoginUser doLoadUserByUsername(String username);
}
