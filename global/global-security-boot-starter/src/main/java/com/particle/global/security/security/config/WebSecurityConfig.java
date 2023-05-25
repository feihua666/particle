package com.particle.global.security.security.config;

import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.*;
import com.particle.global.security.security.logout.DefaultLogoutSuccessHandler;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.global.security.tenant.IUserTenantChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.SessionManagementFilter;

import java.util.List;

/**
 * webSecurity config
 * @author yangwei
 * @since 2020/12/10 20:20
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String login_processing_url = "/login";
    public static final String logout_processing_url = "/logout";

    private CustomWebSecurityConfigureExt ext = new CustomWebSecurityConfigureExt();

    /**
     * 密码加解密处理，用户添加的时候也会用到
     * @return
     */

    @Autowired(required = false)
    private List<CustomWebSecurityConfigure> customWebSecurityConfigureList;
    @Autowired(required = false)
    private List<SecurityFilterPersistentLoginUserReadyListener> securityFilterPersistentLoginUserReadyListenerList;

    @Autowired(required = false)
    private List<IUserTenantChangeListener> iUserTenantChangeListeners;

    @Autowired(required = false)
    private ITenantResolveService iTenantResolveService;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncryptEnum.passwordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.setInvalidateHttpSession(true);
        securityContextLogoutHandler.setClearAuthentication(true);
        http.authorizeRequests()
                // 已启动 EnableGlobalMethodSecurity，这里全部放开，根据权限动态配置
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .successHandler(ext.getDefaultAuthenticationSuccessHandler())
                .failureHandler(ext.getDefaultAuthenticationFailureHandler())
                .loginProcessingUrl(login_processing_url)
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(false)
                .clearAuthentication(false)
                // SecurityContextLogoutHandler 会清空session，导致 在 logout success handler 中获取不到，上面设置为 false
                .logoutSuccessHandler(new DefaultLogoutSuccessHandler(securityContextLogoutHandler))
                .logoutUrl(logout_processing_url).permitAll()
                .and().csrf().disable()
                //cookie，默认保存两周
                .rememberMe().rememberMeParameter("remember");
        AuthenticationManager authenticationManager = super.authenticationManagerBean();

        // 自定义当前登录用户工具类
        LoginUserToolPersistentSecurityFilter loginUserToolPersistentSecurityFilter = new LoginUserToolPersistentSecurityFilter();
        loginUserToolPersistentSecurityFilter.setSecurityFilterPersistentLoginUserReadyListenerList(securityFilterPersistentLoginUserReadyListenerList);
        loginUserToolPersistentSecurityFilter.setIUserTenantChangeListeners(iUserTenantChangeListeners);
        loginUserToolPersistentSecurityFilter.setITenantResolveService(iTenantResolveService);
        http.addFilterAfter(loginUserToolPersistentSecurityFilter, SessionManagementFilter.class);

        if (customWebSecurityConfigureList != null) {
            for (CustomWebSecurityConfigure customWebSecurityConfigure : customWebSecurityConfigureList) {
                customWebSecurityConfigure.configure(http,authenticationManager,ext);
            }
        }
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        if (customWebSecurityConfigureList != null) {
            for (CustomWebSecurityConfigure customWebSecurityConfigure : customWebSecurityConfigureList) {
                customWebSecurityConfigure.configure(auth,passwordEncoder(),ext);
            }
        }
    }
}
