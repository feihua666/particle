package com.particle.global.security.security.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>
 * 定义一个空的 UserDetailsService，用于在没有自定义时使用
 * </p>
 *
 * @author yangwei
 * @since 2026/2/28 21:29
 */
@Slf4j
public class EmptyUserDetailsService extends AbstractUserDetailsService {
    @Override
    public SecurityLoginUser doLoadUserByUsername(String username) {
        log.warn("you are using an empty EmptyUserDetailsService instance. null will be returned.");
        return null;
    }
}
