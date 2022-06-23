package com.particle.global.security.security.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by yangwei
 * Created at 2020/10/13 14:57
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
    private String id;

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

    @ApiModelProperty(value = "权限字符串信息")
    private List<String> stringAuthorities;

    @ApiModelProperty(value = "扩展信息")
    private Map<String, Object> ext;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginAt = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (stringAuthorities == null) {
            return null;
        }
        return AuthorityUtils.createAuthorityList(stringAuthorities.toArray(new String[0]));
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
     * 添加权限信息
     * @param authority
     */
    public void addAuthority(String authority) {
        if (stringAuthorities == null) {
            stringAuthorities = new ArrayList<>();
        }
        stringAuthorities.add(authority);
    }

    /**
     * 添加多个权限信息
     * @param stringAuthorities
     */
    public void addAuthority(List<String> stringAuthorities) {
        if (this.stringAuthorities == null) {
            this.stringAuthorities = new ArrayList<>();
        }
        this.stringAuthorities.addAll(stringAuthorities);
    }

    public void addExt(String key, Object obj) {
        if (ext == null) {
            ext = new HashMap<>();
        }
        ext.put(key, obj);
    }
}
