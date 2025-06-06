package com.particle.global.captcha.security;

import com.particle.global.security.security.config.CustomWebSecurityConfigure;
import com.particle.global.security.security.config.CustomWebSecurityConfigureExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 添加验证码校验过滤器为安全提供支持
 * @author yangwei
 * @since 2023-04-25 15:14:36
 */
@Component
public class CaptchaSecurityConfigure implements CustomWebSecurityConfigure {
    @Autowired
    private CaptchaSecurityFilter captchaSecurityFilter;
    @Autowired
    private DynamicCaptchaSecurityFilter dynamicCaptchaSecurityFilter;

    @Override
    public void configure(HttpSecurity http, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder, CustomWebSecurityConfigureExt ext) throws Exception {

        http.addFilterBefore(captchaSecurityFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(dynamicCaptchaSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
