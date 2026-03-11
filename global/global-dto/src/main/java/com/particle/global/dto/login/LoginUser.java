package com.particle.global.dto.login;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录用户
 * </p>
 *
 * @author yangwei
 * @since 2026/1/29 20:04
 */
@Getter
@Setter
@Schema(description = "登录用户信息")
public class LoginUser extends DTO{

    /**
     * 该字段太大，主要是引用该字段，以在打印日志时忽略该字段
     */
    public static final String userGrantedAuthoritiesFieldName = "userGrantedAuthorities";

    /**
     * 超级管理员角色编码
     */
    public static final String super_admin_role = "superadmin";
    /**
     * 租户超级管理员角色编码
     */
    public static String tenant_super_admin_role = "tenantsuperadmin";

    @Schema(description = "用户id")
    private Long id;

    /**
     * 是否超级管理员,超级管理员是系统级别，无需分配功能，即拥有所有功能权限
     * 参见 {@link com.particle.global.security.security.voter.SuperAdminRoleVoter} 已硬编码
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
    private List<GrantedDataConstraint> dataConstraints;

    @Schema(description = "部门信息")
    private DeptInfo deptInfo;

    @Schema(description = "密码信息")
    private PasswordInfo passwordInfo;

    @Schema(description = "账号信息")
    private UserIdentifierInfo identifierInfo;

    /**
     * 租户信息
     * 通过 {@link com.particle.global.security.tenant.UserTenantService} 获取并set设置
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

    /**
     * 切换租户
     * 参见 {@link com.particle.tenant.adapter.login.TenantLoginController#changeTenant(com.particle.common.client.dto.command.CommonIdCommand, LoginUser, jakarta.servlet.http.HttpServletRequest)}
     * @param defaultTenantId
     */
    public void initTenants(Long defaultTenantId) {
        if (isNotEmpty(tenants)) {
            GrantedTenant grantedTenant = tenants.stream().filter(item -> item.getId().equals(defaultTenantId)).findFirst().orElse(null);
            currentTenant = grantedTenant;
        }else {
            currentTenant = null;
        }
    }

    /**
     * 根据权限初始化角色
     */
    public void initRoles(Long defaultRoleId){
        if (isEmpty(userGrantedAuthorities)) {
            roles =  Collections.emptyList();
            return;
        }
        List<GrantedRole> rolesTemp =  userGrantedAuthorities.stream().map(UserGrantedAuthority::getGrantedPermissionRole).filter(Objects::nonNull).collect(Collectors.toList());
        if (isNotEmpty(rolesTemp)) {
            List<GrantedRole> distinct = rolesTemp.stream()
                    .collect(Collectors.toMap(
                            GrantedRole::getId,  // key 用 id 去重
                            role -> role,        // value 保留整个对象
                            (existing, replacement) -> existing, // id 冲突时保留第一个
                            LinkedHashMap::new   // 保留原顺序
                    ))
                    .values()
                    .stream()
                    .toList();
            roles = distinct;
        }
        if (isNotEmpty(roles)) {
            if (defaultRoleId == null) {
                currentRole = roles.iterator().next();
            } else {
                currentRole = roles.stream().filter(item -> item.getId().equals(defaultRoleId)).findFirst().orElse(null);
            }
        }else {
            currentRole = null;
        }

        if (currentRole != null) {
            boolean superAdminRole = Objects.equals(LoginUser.super_admin_role, currentRole.getCode()) || (currentRole.getIsSuperadmin() != null && currentRole.getIsSuperadmin());
            isSuperAdmin = (superAdminRole);
            boolean tenantsSuperAdminRole = Objects.equals(LoginUser.tenant_super_admin_role, currentRole.getCode());
            isTenantSuperAdmin = (tenantsSuperAdminRole);

            // 权限信息只保留当前角色的
            userGrantedAuthorities = userGrantedAuthorities.stream()
                    .filter(item -> item.getGrantedPermissionRole() == null || (currentRole != null && item.getGrantedPermissionRole().getId().equals(currentRole.getId())))

                    .collect(Collectors.toList());
        }
    }

    /**
     * 该方法返回的值将会输出的前端接口调用
     * @return
     */
    public List<String> getPermissions() {
        if (isEmpty(userGrantedAuthorities)) {
            return Collections.emptyList();
        }
        return userGrantedAuthorities.stream()
                .filter(item -> item.getGrantedPermissionRole() == null || (currentRole != null && item.getGrantedPermissionRole().getId().equals(currentRole.getId())))
                .map(UserGrantedAuthority::getGrantedPermission)
                .filter(Objects::nonNull).map(GrantedPermission::getPermission).filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());

    }

    /**
     * 从当前用户获取最终数据范围约束，目前只支持角色绑定的数据范围约束，后期可能添加针对人直接绑定的数据范围约束
     * @return
     */
    public List<GrantedDataConstraint> finalizeDataConstraints() {
        return dataConstraints;
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
    }

    public void setUserGrantedAuthorities(List<UserGrantedAuthority> userGrantedAuthorities) {
        this.userGrantedAuthorities = userGrantedAuthorities;
        if (this.userGrantedAuthorities == null) {
            this.userGrantedAuthorities = new ArrayList<>();
        }
    }

    public void setTenants(List<GrantedTenant> tenants) {
        this.tenants = tenants;
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
     * 集合是否为非空
     *
     * @param collection 集合
     * @return 是否为非空
     */
    private static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    private static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    /**
     * 用于返回给前端json，忽略某些字段
     */
    public static interface UserWebIgnoreView {}
    public static interface UserWebView {}
}

