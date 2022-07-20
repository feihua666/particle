package com.particle.global.security.security.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yangwei
 * @since 2020/7/29 14:57
 */
public interface CustomWebSecurityConfigure {

    default void configure(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {};

    default void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {}
}
