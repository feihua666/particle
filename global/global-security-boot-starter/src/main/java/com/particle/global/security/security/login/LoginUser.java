package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.particle.global.security.security.voter.SuperAdminRoleVoter;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.global.tool.constant.Constants;
import com.particle.global.tool.json.JsonTool;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangwei
 * @since 2020/10/13 14:57
 */
@Slf4j
@Getter
@Setter
@Schema(description = "登录用户信息")
public class LoginUser implements UserDetails {

    /**
     * 该字段太大，主要是引用该字段，以在打印日志时忽略该字段
     */
    public static final String userGrantedAuthoritiesFieldName = "userGrantedAuthorities";

    /**
     * 超级管理员角色编码
     */
    public static final String super_admin_role = Constants.super_admin_role;
    /**
     * 租户超级管理员角色编码
     */
    public static String tenant_super_admin_role = "tenantsuperadmin";

    @Schema(description = "用户id")
    private Long id;

    /**
     * 是否超级管理员,超级管理员是系统级别，无需分配功能，即拥有所有功能权限
     * 参见 {@link SuperAdminRoleVoter} 已硬编码
     */
    @Schema(description = "是否超级管理员")
    private Boolean isSuperAdmin = false;

    /**
     * 是否租户超级管理员,租户超级管理员是租户级别，无需分配功能，即拥有所有租户下功能权限
     * 这在获取用户的功能时已硬编码
     * 该字段仅在多租户下有效，如果不是多租户部署，其在某种程序上等同于 {@link LoginUser#isSuperAdmin}
     */
    @Schema(description = "是否租户超级管理员")
    private Boolean isTenantSuperAdmin = false;

    @Schema(description = "用户姓名")
    private String name;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar ;

    @Schema(description = "用户性别")
    private String gender ;

    @Schema(description = "用户登录帐号,登录标识字符串")
    private String username;

    @Schema(description = "用户登录密码")
    private String password;

    @Schema(description = "帐号是否过期")
    private Boolean isExpired = false;

    @Schema(description = "用户是否锁定")
    private Boolean isLocked = false;

    @Schema(description = "帐号是否可用")
    private Boolean isEnabled = true;

    @Schema(description = "用户登录密码是否过期")
    private Boolean isCredentialsExpired = false;

    /**
     * 前端直接返回了，这里不生成，不显示到前端
     * 不要直接使用set方法设置，使用 addxxx方法设置，会有对应的角色的数据处理逻辑
     */
    //@Getter(AccessLevel.NONE)
    @JsonView(UserWebIgnoreView.class)
    @Schema(description = "权限信息")
    private List<UserGrantedAuthority> userGrantedAuthorities;

    /**
     * 角色信息
     * 通过 {@link LoginUser#userGrantedAuthorities} 提取获取
     * 不要直接通过set方法设置，这在添加权限的时候自动初始化
     */
    //@Setter(AccessLevel.NONE)
    @Schema(description = "角色信息")
    private List<GrantedRole> roles;

    @Schema(description = "当前正在使用的角色")
    private GrantedRole currentRole;

    @JsonView(UserWebIgnoreView.class)
    @Schema(description = "当前正在使用的角色绑定的数据范围约束")
    private List<GrantedDataConstraint> currentRoleBindDataConstraints;

    @Schema(description = "部门信息")
    private DeptInfo deptInfo;

    @Schema(description = "密码信息")
    private PasswordInfo passwordInfo;

    /**
     * 租户信息
     * 通过 {@link UserTenantService} 获取并set设置
     */
    @Schema(description = "租户信息")
    private List<GrantedTenant> tenants;

    /**
     * 当前正在使用的租户
     */
    @Schema(description = "当前正在使用的租户")
    private GrantedTenant currentTenant;
    /**
     * 权限码信息
     * 通过 {@link LoginUser#userGrantedAuthorities} 获取
     * 不要直接通过set方法设置，这在get方法中直接获取
     */
    //@Setter(AccessLevel.NONE)
    private List<String> permissions;

    @Schema(description = "扩展信息")
    private Map<String, Object> ext;

    @Schema(description = "登录时间")
    private LocalDateTime loginAt = LocalDateTime.now();

    @Schema(description = "登录IP")
    private String loginIp;

    @JsonView(UserWebIgnoreView.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userGrantedAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return !getIsExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getIsLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !getIsCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return getIsEnabled();
        }


    /**
     * 切换租户
     * 参见 {@link com.particle.user.adapter.login.UserLoginController#changeTenant(com.particle.common.client.dto.command.IdCommand, LoginUser, HttpServletRequest)}
     * @param tenantId
     */
    public void changeTenant(Long tenantId) {
        if (CollectionUtil.isNotEmpty(tenants)) {

            GrantedTenant grantedTenant = tenants.stream().filter(item -> item.getId().equals(tenantId)).findFirst().get();
            currentTenant = grantedTenant;
        }
    }
    /**
     * 切换角色
     * 参见{@link com.particle.user.adapter.login.UserLoginController#changeRole(com.particle.common.client.dto.command.IdCommand, LoginUser)}
     * @param roleId
     */
    public void changeRole(Long roleId) {
        if (CollectionUtil.isNotEmpty(roles)) {

            GrantedRole grantedRole = roles.stream().filter(item -> item.getId().equals(roleId)).findFirst().get();
            currentRole = grantedRole;
        }
    }

    public void changRole(GrantedRole grantedRole) {
        this.currentRole = grantedRole;
    }
    /**
     * 根据权限初始化角色
     */
    private void initRoles(){
        if (CollectionUtil.isEmpty(userGrantedAuthorities)) {
            roles =  Collections.emptyList();
            return;
        }
        List<GrantedRole> rolesTemp =  userGrantedAuthorities.stream().map(UserGrantedAuthority::getGrantedPermissionRole).filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(rolesTemp)) {
            List<GrantedRole> distinct = CollectionUtil.distinct(rolesTemp, r -> r.getId(), true);
            roles = distinct;
        }
        if (currentRole == null) {
            if (CollectionUtil.isNotEmpty(roles)) {
                changRole(roles.iterator().next());
            }
        }else {
            if (CollectionUtil.isNotEmpty(roles)) {
                long count = roles.stream().filter(item -> item.getId().equals(currentRole.getId())).count();
                // 如果可用的角色不包含当前角色，将当前角色重新设置
                if (count <= 0) {
                    GrantedRole newCurrentRole = roles.iterator().next();
                    log.warn("currentRole changed to {} because of user roles not include old role {}", JsonTool.toJsonStr(newCurrentRole),JsonTool.toJsonStr(newCurrentRole));
                    changRole(newCurrentRole);
                }
            }else {
                // 用户没有可用角色，将当前角色清空
                changRole(null);
            }
        }
    }

    /**
     * 该方法返回的值将会输出的前端接口调用
     * @return
     */
    public List<String> getPermissions() {
        if (CollectionUtil.isEmpty(userGrantedAuthorities)) {
            return Collections.emptyList();
        }
        return userGrantedAuthorities.stream()
                .map(UserGrantedAuthority::getGrantedPermission)
                .filter(Objects::nonNull).map(GrantedPermission::getPermission).filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());

    }

    /**
     * 从当前用户获取最终数据范围约束，目前只支持角色绑定的数据范围约束，后期可能添加针对人直接绑定的数据范围约束
     * @return
     */
    public List<GrantedDataConstraint> finalizeDataConstraints() {
        return currentRoleBindDataConstraints;
    }

    /**
     * 添加权限信息
     * @param authority
     */
    public void addAuthority(UserGrantedAuthority authority) {
        addAuthority(Lists.newArrayList(authority));
    }

    /**
     * 添加多个权限信息
     * @param stringAuthorities
     */
    public void addAuthority(List<UserGrantedAuthority> stringAuthorities) {
        if (this.userGrantedAuthorities == null) {
            this.userGrantedAuthorities = new ArrayList<>();
        }
        this.userGrantedAuthorities.addAll(stringAuthorities);
        initRoles();
    }

    public void setUserGrantedAuthorities(List<UserGrantedAuthority> userGrantedAuthorities) {
        this.userGrantedAuthorities = userGrantedAuthorities;
        if (this.userGrantedAuthorities == null) {
            this.userGrantedAuthorities = new ArrayList<>();
        }
        initRoles();
    }

    /**
     * 清空权限
     * 这一般在重新加载权限时使用
     */
    public void clearUserGrantedAuthorities(){
        if (this.userGrantedAuthorities != null) {
            this.userGrantedAuthorities.clear();
        }
    }

    public void addExt(String key, Object obj) {
        if (ext == null) {
            ext = new HashMap<>();
        }
        ext.put(key, obj);
    }

    /**
     * 用于返回给前端json，忽略某些字段
     */
    public static interface UserWebIgnoreView {}
    public static interface UserWebView {}
}
