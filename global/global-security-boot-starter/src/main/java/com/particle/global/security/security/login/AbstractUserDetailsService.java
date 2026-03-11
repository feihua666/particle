package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.login.*;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import com.particle.global.security.tenant.SecurityUserTenantService;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.tenant.TenantTool;
import com.particle.global.tool.thread.ThreadContextTool;
import jakarta.servlet.http.HttpServletRequest;
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
    private SecurityUserTenantService securityUserTenantService;

    @Autowired(required = false)
    private SecurityUserDeptService securityUserDeptService;

    @Autowired(required = false)
    private SecurityRoleDataConstraintService securityRoleDataConstraintService;

    @Autowired(required = false)
    private SecurityUserAuthorityService securityUserAuthorityService;

    @Autowired(required = false)
    private List<LoginUserExtPutService> loginUserExtPutServices;

    @Qualifier(ConcurrencyConstants.default_global_asyn_slot_task_executor)
    @Autowired
    private ExecutorService asynSlotTaskExecutor;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityLoginUser loginUser = doLoadUserByUsername(username);
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        /**
         * 填充额外信息
         * TenantTool.getTenantId() 在用户登录前已经设置 参考 {@link com.particle.global.web.filter.TenantContextLoginFilter}
         */
        loginUserDetailsFillTenant(loginUser, null, TenantTool.getTenantId());
        loginUserDetailsFill(loginUser,null);

        return loginUser;
    }


    /**
     * 实际获取用户信息
     * @param username
     * @return
     */
    public abstract SecurityLoginUser doLoadUserByUsername(String username);
    /**
     * 登录用户信息填充 租户
     * @param loginUser
     * @param defaultTenantId 默认切换到的租户id,如果为空默认切换到第一个
     * @param limitedTenantId 限制租户id，如果指定，只能限制在该租户下
     */
    public void loginUserDetailsFillTenant(LoginUser loginUser, Long defaultTenantId, Long limitedTenantId) {
        if (securityUserTenantService != null) {
            // 如果有值那么租户就锁定在该租户下
            List<GrantedTenant> grantedTenants = securityUserTenantService.retrieveUserTenantByUserId(loginUser.getId());
            if (CollectionUtil.isNotEmpty(grantedTenants) && limitedTenantId != null) {
                // 限定在已解析到的租户下
                grantedTenants = grantedTenants.stream().filter(item -> limitedTenantId.equals(item.getId())).collect(Collectors.toList());
                if (CollectionUtil.isEmpty(grantedTenants)) {
                    throw new UsernameNotFoundException("未获取到租户数据 userId=" + loginUser.getId());
                }
            }
            loginUser.setTenants(grantedTenants);
            if (limitedTenantId != null) {
                defaultTenantId = limitedTenantId;
            }
            loginUser.initTenants(defaultTenantId);
        }
    }
    /**
     * 用户额外详细信息加载
     * @param loginUser
     */
    public void loginUserDetailsFill(LoginUser loginUser,Long defaultRoleId) {

        String clientIP = RequestTool.getClientRealIP(httpServletRequest);
        loginUser.setLoginIp(clientIP);

        // 默认添加用户权限,注意该块代码的位置，不能在最后执行，否则可能导致角色等绑定的数据范围约束有误
        loginUser.addAuthority(UserGrantedAuthority.userGrantedAuthority);

        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 角色和权限
        loginUserDetailsFillRoleAndAuthority(loginUser, countDownLatch,defaultRoleId);
        // 部门信息
        loginUserDetailsFillDept(loginUser, countDownLatch);
        // 扩展信息
        loginUserDetailsFillExt(loginUser, countDownLatch);

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
    /**
     * 登录用户信息填充 角色和权限
     * @param loginUser
     */
    public void loginUserDetailsFillRoleAndAuthority(LoginUser loginUser,Long defaultRoleId) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        loginUserDetailsFillRoleAndAuthority(loginUser, countDownLatch,defaultRoleId);
    }

    /**
     * 登录用户信息填充 角色和权限
     * @param loginUser
     */
    private void loginUserDetailsFillRoleAndAuthority(LoginUser loginUser,CountDownLatch countDownLatch,Long defaultRoleId) {
        if (securityUserAuthorityService != null) {
            Long tenantId = Optional.ofNullable(loginUser.getCurrentTenant()).map(tenant -> tenant.getId ()).orElse(null);
            asynSlotTaskExecutor.execute(() -> {
                try {
                    TenantTool.setTenantId(tenantId);
                    List<UserGrantedAuthority> list = securityUserAuthorityService.retrieveUserAuthoritiesByUserId(loginUser);
                    // 在这个方法里面初始化默认选中的角色
                    loginUser.addAuthority(list);
                    loginUser.initRoles(defaultRoleId);
                    if (securityRoleDataConstraintService != null) {
                        GrantedRole currentRole = loginUser.getCurrentRole();
                        if (currentRole != null) {
                            List<GrantedDataConstraint> grantedDataConstraints = securityRoleDataConstraintService.retrieveRoleDataConstraintByRoleId(currentRole.getId());
                            loginUser.setDataConstraints(grantedDataConstraints);
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
    }
    /**
     * 登录用户信息填充 部门信息
     * @param loginUser
     */
    private void loginUserDetailsFillDept(LoginUser loginUser,CountDownLatch countDownLatch) {
        // 部门信息
        if (securityUserDeptService != null) {
            asynSlotTaskExecutor.execute(() -> {
                try {
                    DeptInfo deptInfo = securityUserDeptService.retrieveUserDeptInfoByUserId(loginUser.getId());
                    loginUser.setDeptInfo(deptInfo);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }else {
            countDownLatch.countDown();
        }
    }
    /**
     * 登录用户信息填充 扩展信息
     * @param loginUser
     */
    private void loginUserDetailsFillExt(LoginUser loginUser,CountDownLatch countDownLatch) {

        if (loginUserExtPutServices != null) {
            Long tenantId = com.particle.global.tool.tenant.TenantTool.getTenantId();
            asynSlotTaskExecutor.execute(() -> {
                try {
                    com.particle.global.tool.tenant.TenantTool.setTenantId(tenantId);
                    for (LoginUserExtPutService loginUserExtPutService : loginUserExtPutServices) {
                        loginUserExtPutService.addExt(loginUser);
                    }
                } finally {
                    countDownLatch.countDown();
                    com.particle.global.tool.tenant.TenantTool.clear();
                }
            });
        }else {
            countDownLatch.countDown();
        }
    }
}
