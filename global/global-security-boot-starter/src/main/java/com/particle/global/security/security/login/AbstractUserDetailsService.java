package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import com.particle.global.security.security.config.GrantedTenantResolveAndPersistentHelper;
import com.particle.global.security.tenant.*;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.thread.ThreadContextTool;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

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
    private UserDeptService userDeptService;

    @Autowired(required = false)
    private RoleDataConstraintService roleDataConstraintService;

    @Autowired(required = false)
    private List<IUserTenantChangeListener> iUserTenantChangeListeners;

    @Autowired(required = false)
    private UserAuthorityService userAuthorityService;

    @Autowired(required = false)
    private List<LoginUserExtPutService> loginUserExtPutServices;

    @Qualifier(ConcurrencyConstants.default_global_asyn_slot_task_executor)
    @Autowired
    private ExecutorService asynSlotTaskExecutor;

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ITenantResolveService iTenantResolveService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedTenant grantedTenant = null;
        if (LoginTool.checkIgnoreTenantResolveAndClearTenantLocal()) {
            // 在登录时重置租户解析，重新解析，因为在登录之前可能用户已经登录或配置了域名已经解析到租户了
            TenantTool.clear();
            // 不再解析租户信息
        }else {
            grantedTenant = iTenantResolveService.resolveGrantedTenant(httpServletRequest,false);
        }

        LoginUser loginUser = doLoadUserByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 加载额外信息
        loginUserDetailsFill(loginUser,null,Optional.ofNullable(grantedTenant).map(GrantedTenant::getId).orElse(null));

        return loginUser;
    }


    /**
     * 用户额外详细信息加载
     * @param loginUser
     * @param defaultTenantId 默认切换到的租户id,如果为空默认切换到第一个
     * @param limitedTenantId 限制租户id，如果指定，只能限制在该租户下
     */
    public void loginUserDetailsFill(LoginUser loginUser,Long defaultTenantId,Long limitedTenantId) {

        String clientIP = RequestTool.getClientIP(httpServletRequest);
        loginUser.setLoginIp(clientIP);

        if (userTenantService != null) {
            // limitedTenantId，这一般是通过 com.particle.global.security.security.config.TenantToolPersistentSecurityFilter 过滤器根据域名配置获取到的
            // 如果有值那么租户就锁定在该租户下
            List<GrantedTenant> grantedTenants = userTenantService.retrieveUserTenantByUserId(loginUser.getId(),limitedTenantId);
            if (limitedTenantId != null) {
                if (CollectionUtil.isNotEmpty(grantedTenants)) {
                    // 限定在已解析到的租户下
                    grantedTenants = grantedTenants.stream().filter(item -> limitedTenantId.equals(item.getId())).collect(Collectors.toList());
                }else {
                    throw new UsernameNotFoundException("未获取到租户数据userId="+loginUser.getId());
                }
                defaultTenantId = limitedTenantId;
            }
            loginUser.setTenants(grantedTenants);
            if (CollectionUtil.isNotEmpty(grantedTenants)) {
                // 默认使用第一个租户
                GrantedTenant defaultFirstGrantedTenant = grantedTenants.iterator().next();
                if (defaultTenantId != null) {
                    Long finalDefaultTenantId = defaultTenantId;
                    GrantedTenant first = grantedTenants.stream().filter(item -> finalDefaultTenantId.equals(item.getId())).findFirst().orElse(null);
                    if (first == null) {
                        log.warn("defaultTenantId has value but can not found grantedTenant, user default instead. defaultTenantId={}",defaultFirstGrantedTenant.getId());
                        loginUser.setCurrentTenant(defaultFirstGrantedTenant);
                    }else {
                        loginUser.setCurrentTenant(first);
                    }
                }else {
                    loginUser.setCurrentTenant(defaultFirstGrantedTenant);
                }
                // 如果已经解析租户，不再处理
                if (iUserTenantChangeListeners != null) {
                    for (IUserTenantChangeListener iUserTenantChangeListener : iUserTenantChangeListeners) {
                        iUserTenantChangeListener.onTenantChanged(loginUser.getCurrentTenant(),null);
                    }
                }
            }
        }
        // 默认添加用户权限,注意该块代码的位置，不能在最后执行，否则可能导致角色等绑定的数据范围约束有误
        loginUser.addAuthority(UserGrantedAuthority.userGrantedAuthority);

        CountDownLatch countDownLatch = new CountDownLatch(3);
        if (userAuthorityService != null) {
            Long tenantId = TenantTool.getTenantId();
            asynSlotTaskExecutor.execute(() -> {
                try {
                    TenantTool.setTenantId(tenantId);
                    List<UserGrantedAuthority> list = userAuthorityService.retrieveUserAuthoritiesByUserId(loginUser);
                    if (CollectionUtil.isNotEmpty(list)) {
                        boolean superAdminRole = list.stream().anyMatch(item ->
                                // 包括超级管理员编码或角色设置了超级管理员
                                StrUtil.equals(LoginUser.super_admin_role, Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getCode).orElse(null))
                                        || Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getIsSuperadmin).orElse(false)
                        );
                        loginUser.setIsSuperAdmin(superAdminRole);

                        boolean tenantsSuperAdminRole = list.stream().anyMatch(item ->
                                StrUtil.equals(LoginUser.tenant_super_admin_role, Optional.ofNullable(item).map(UserGrantedAuthority::getGrantedPermissionRole).map(GrantedRole::getCode).orElse(null))
                        );
                        loginUser.setIsTenantSuperAdmin(tenantsSuperAdminRole);
                    }
                    // 在这个方法里面初始化默认选中的角色
                    loginUser.addAuthority(list);
                    if (roleDataConstraintService != null) {
                        GrantedRole currentRole = loginUser.getCurrentRole();
                        if (currentRole != null) {
                            List<GrantedDataConstraint> grantedDataConstraints = roleDataConstraintService.retrieveRoleDataConstraintByRoleId(currentRole.getId());
                            loginUser.setCurrentRoleBindDataConstraints(grantedDataConstraints);
                        }
                    }


                } finally {
                    countDownLatch.countDown();
                    TenantTool.clear();

                }
            });

        }else {
            countDownLatch.countDown();
        }

        // 部门信息
        if (userDeptService != null) {
            asynSlotTaskExecutor.execute(() -> {
                try {
                    DeptInfo deptInfo = userDeptService.retrieveUserDeptInfoByUserId(loginUser.getId());
                    loginUser.setDeptInfo(deptInfo);
                } finally {
                    countDownLatch.countDown();
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
            log.error("用户 username={} 登录被中断，已抛出 {} 异常",loginUser.getUsername(),msg,e);
            // 在等待过程中，有可能被中断，比如系统 shutdown 停机
            throw new UsernameNotFoundException(msg);
        }
        // 在线程中放置一个用户，不管用户密码对不对，都可以在后期获取到，主要用来记录用户登录日志
        ThreadContextTool.put(login_loaded_user_in_threadcontext_key,loginUser);

    }
    public abstract LoginUser doLoadUserByUsername(String username);
}
