package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.dto.login.UserGrantedAuthority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangwei
 * @since 2020/10/13 14:57
 */
@Slf4j
@Schema(description = "登录用户信息")
public class SecurityLoginUser extends LoginUser implements UserDetails {

    @JsonView(UserWebIgnoreView.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserGrantedAuthority> userGrantedAuthorities = getUserGrantedAuthorities();
        return SecurityUserGrantedAuthority.create(userGrantedAuthorities);
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


}
