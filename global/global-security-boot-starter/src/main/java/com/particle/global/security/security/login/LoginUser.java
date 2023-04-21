package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.particle.global.security.security.voter.SuperAdminRoleVoter;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.UserTenantService;
import com.particle.global.tool.json.JsonTool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@ApiModel("登录用户信息")
public class LoginUser implements UserDetails {

    /**
     * 超级管理员角色编码
     */
    public static String super_admin_role = "superadmin";
    /**
     * 租户超级管理员角色编码
     */
    public static String tenant_super_admin_role = "tenantsuperadmin";

    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 是否超级管理员,超级管理员是系统级别，无需分配功能，即拥有所有功能权限
     * 参见 {@link SuperAdminRoleVoter} 已硬编码
     */
    @ApiModelProperty(value = "是否超级管理员")
    private Boolean isSuperAdmin = false;

    /**
     * 是否租户超级管理员,租户超级管理员是租户级别，无需分配功能，即拥有所有租户下功能权限
     * 这在获取用户的功能时已硬编码
     * 该字段仅在多租户下有效，如果不是多租户部署，其在某种程序上等同于 {@link LoginUser#isSuperAdmin}
     */
    @ApiModelProperty(value = "是否租户超级管理员")
    private Boolean isTenantSuperAdmin = false;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar ;

    @ApiModelProperty(value = "用户性别")
    private String gender ;

    @ApiModelProperty(value = "用户登录帐号,登录标识字符串")
    private String username;

    @ApiModelProperty(value = "用户登录密码")
    private String password;

    @ApiModelProperty(value = "帐号是否过期")
    private Boolean isExpired = false;

    @ApiModelProperty(value = "用户是否锁定")
    private Boolean isLocked = false;

    @ApiModelProperty(value = "帐号是否可用")
    private Boolean isEnabled = true;

    @ApiModelProperty(value = "用户登录密码是否过期")
    private Boolean isCredentialsExpired = false;

    /**
     * 前端直接返回了，这里不生成，不显示到前端
     */
    @Getter(AccessLevel.NONE)
    @ApiModelProperty(value = "权限信息")
    private List<UserGrantedAuthority> userGrantedAuthorities;

    /**
     * 角色信息
     * 通过 {@link LoginUser#userGrantedAuthorities} 提取获取
     */
    @Setter(AccessLevel.NONE)
    @ApiModelProperty(value = "角色信息")
    private List<GrantedRole> roles;

    @ApiModelProperty(value = "当前正在使用的角色")
    private GrantedRole currentRole;

    /**
     * 租户信息
     * 通过 {@link UserTenantService} 获取并set设置
     */
    @ApiModelProperty(value = "租户信息")
    private List<GrantedTenant> tenants;

    /**
     * 当前正在使用的租户
     */
    @ApiModelProperty(value = "当前正在使用的租户")
    private GrantedTenant currentTenant;
    /**
     * 权限码信息
     * 通过 {@link LoginUser#userGrantedAuthorities} 获取
     */
    @Setter(AccessLevel.NONE)
    private List<String> permissions;

    @ApiModelProperty(value = "扩展信息")
    private Map<String, Object> ext;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginAt = LocalDateTime.now();

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
     * todo 该方法暂不可能，因为切换租户并没有这么简单，这涉及租户对应的权限重新加载
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
     * @param roleId
     */
    public void changeRole(Long roleId) {
        if (CollectionUtil.isNotEmpty(tenants)) {

            GrantedRole grantedRole = roles.stream().filter(item -> item.getId().equals(roleId)).findFirst().get();
            currentRole = grantedRole;
        }
    }
    /**
     * 根据权限初始化角色
     */
    private void initRoles(){
        if (CollectionUtil.isEmpty(userGrantedAuthorities)) {
            roles =  Collections.emptyList();
            return;
        }
        roles =  userGrantedAuthorities.stream().map(UserGrantedAuthority::getGrantedPermissionRole).filter(Objects::nonNull).collect(Collectors.toList());
        if (currentRole == null) {
            if (CollectionUtil.isNotEmpty(roles)) {
                currentRole = roles.iterator().next();
            }
        }else {
            if (CollectionUtil.isNotEmpty(roles)) {
                long count = roles.stream().filter(item -> item.getId().equals(currentRole.getId())).count();
                // 如果可用的角色不包含当前角色，将当前角色重新设置
                if (count <= 0) {
                    GrantedRole newCurrentRole = roles.iterator().next();
                    log.warn("currentRole changed to {} because of user roles not include old role {}", JsonTool.toJsonStr(newCurrentRole),JsonTool.toJsonStr(newCurrentRole));
                    currentRole = newCurrentRole;
                }
            }else {
                // 用户没有可用角色，将当前角色清空
                currentRole = null;
            }
        }
    }
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
}
