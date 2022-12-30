package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangwei
 * @since 2020/10/13 14:57
 */
@Getter
@Setter
@ApiModel("登录用户信息")
public class LoginUser implements UserDetails {

    /**
     * 超级管理员角色
     */
    public static String super_admin_role = "superadmin";
    
    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 是否超级管理员
     */
    @ApiModelProperty(value = "是否超级管理员")
    private Boolean isSuperAdmin = false;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar ;

    @ApiModelProperty(value = "用户性别")
    private String gender ;

    @ApiModelProperty(value = "用户登录帐号")
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
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ApiModelProperty(value = "权限信息")
    private List<UserGrantedAuthority> userGrantedAuthorities;

    /**
     * 角色信息
     * 通过 {@link LoginUser#userGrantedAuthorities} 获取
     */
    @Setter(AccessLevel.NONE)
    private List<GrantedRole> roles;

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

    public List<GrantedRole> getRoles() {
        if (CollectionUtil.isEmpty(userGrantedAuthorities)) {
            return Collections.emptyList();
        }
        return userGrantedAuthorities.stream().map(UserGrantedAuthority::getGrantedPermissionRole).filter(Objects::nonNull).collect(Collectors.toList());
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
        if (userGrantedAuthorities == null) {
            userGrantedAuthorities = new ArrayList<>();
        }
        userGrantedAuthorities.add(authority);
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

    public void addExt(String key, Object obj) {
        if (ext == null) {
            ext = new HashMap<>();
        }
        ext.put(key, obj);
    }
}
