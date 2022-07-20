package com.particle.global.security.security.config;

import com.particle.global.security.security.login.DefaultAuthenticationFailureHandler;
import com.particle.global.security.security.login.DefaultAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

import java.util.List;

/**
 * webSecurity config
 * @author yangwei
 * @since 2020/12/10 20:20
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 密码加解密处理，用户添加的时候也会用到
     * @return
     */

    @Autowired(required = false)
    private List<CustomWebSecurityConfigure> customWebSecurityConfigureList;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 已启动 EnableGlobalMethodSecurity，这里全部放开，根据权限动态配置
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .successHandler(new DefaultAuthenticationSuccessHandler())
                .failureHandler(new DefaultAuthenticationFailureHandler())
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable()
                //cookie，默认保存两周
                .rememberMe().rememberMeParameter("remember");
        AuthenticationManager authenticationManager = super.authenticationManager();

        // 自定义当前登录用户工具类
        http.addFilterAfter(new LoginUserToolPersistentSecurityFilter(), SessionManagementFilter.class);

        if (customWebSecurityConfigureList != null) {
            for (CustomWebSecurityConfigure customWebSecurityConfigure : customWebSecurityConfigureList) {
                customWebSecurityConfigure.configure(http,authenticationManager);
            }
        }
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if (customWebSecurityConfigureList != null) {
            for (CustomWebSecurityConfigure customWebSecurityConfigure : customWebSecurityConfigureList) {
                customWebSecurityConfigure.configure(auth,passwordEncoder());
            }
        }
    }
}
