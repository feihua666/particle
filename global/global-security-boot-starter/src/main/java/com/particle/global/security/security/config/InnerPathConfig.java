package com.particle.global.security.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 该配置将请求 web 路径包含 inner或rpc 路径的全部拒绝，因为包含有 inner或rpc 路径的请求属于微服务内部调用
 * 适用于单机部署配置或配置于网关中以限制外部网络访问包含 inner或rpc 路径的请求
 * @author yangwei
 * @since 2021/1/28 18:40
 */
public class InnerPathConfig implements CustomWebSecurityConfigure {
    @Override
    public void configure(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder, CustomWebSecurityConfigureExt ext) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers("/**/inner/**").denyAll()
                        .requestMatchers("/**/rpc/**").denyAll()
        );

    }
}
