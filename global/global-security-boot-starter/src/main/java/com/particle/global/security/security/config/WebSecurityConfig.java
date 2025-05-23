package com.particle.global.security.security.config;

import cn.hutool.core.util.ReflectUtil;
import com.particle.global.security.GlobalSecurityProperties;
import com.particle.global.security.authorizationserver.AuthorizationServerSecurityAutoConfiguration;
import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.SecurityFilterPersistentLoginUserReadyListener;
import com.particle.global.security.security.logout.DefaultLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import java.util.List;

/**
 * webSecurity config
 * @author yangwei
 * @since 2020/12/10 20:20
 */
@Configuration(proxyBeanMethods = false)
public class WebSecurityConfig {

    public static final int defaultSecurityFilterChainOrder = Ordered.LOWEST_PRECEDENCE - 10000;

    public static final String login_page_url = "/loginpage";
    public static final String login_processing_url = "/login";
    public static final String logout_processing_url = "/logout";

    @Autowired(required = false)
    private GlobalSecurityProperties globalSecurityProperties;

    @Autowired
    private CustomWebSecurityConfigureExt ext;

    /**
     * 密码加解密处理，用户添加的时候也会用到
     * @return
     */

    @Autowired(required = false)
    private List<CustomWebSecurityConfigure> customWebSecurityConfigureList;
    @Autowired(required = false)
    private List<SecurityFilterPersistentLoginUserReadyListener> securityFilterPersistentLoginUserReadyListenerList;

    @Autowired
    private GrantedTenantResolveAndPersistentHelper grantedTenantResolveAndPersistentHelper;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncryptEnum.passwordEncoder();
    }

    /**
     * 默认的security配置
     * @param http 注意：该实例和{@link AuthorizationServerSecurityAutoConfiguration#authorizationServerSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 不是一个实例
     * @param authenticationManagerBuilder
     * @return
     * @throws Exception
     */
    @Bean
    @Order(defaultSecurityFilterChainOrder)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        http.authorizeHttpRequests(authorizeHttpRequests -> {
            // 已启用 EnableGlobalMethodSecurity，这里全部放开，根据权限动态配置
            // 注意：这里配置所有请求并不是 anyRequest,否则在扩展中不能再使用规则了
            authorizeHttpRequests.requestMatchers("/**")
                    .permitAll();
        }).formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage(login_page_url)
                    .successHandler(ext.getDefaultAuthenticationSuccessHandler())
                    .failureHandler(ext.getDefaultAuthenticationFailureHandler())
                    .loginProcessingUrl(login_processing_url)
                    .permitAll();
            if (globalSecurityProperties != null && globalSecurityProperties.getForceNotUseCustomLoginPage() != null && globalSecurityProperties.getForceNotUseCustomLoginPage()) {
                // 单纯改变一下url改不想改变其逻辑，利用反射修改一下
                ReflectUtil.setFieldValue(formLoginConfigurer, "customLoginPage", false);
            }
        }).logout(logoutConfigurer -> {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.setInvalidateHttpSession(true);
            securityContextLogoutHandler.setClearAuthentication(true);
            logoutConfigurer.invalidateHttpSession(false)
                    .clearAuthentication(false)
                    // SecurityContextLogoutHandler 会清空session，导致 在 logout success handler 中获取不到，上面设置为 false
                    .logoutSuccessHandler(new DefaultLogoutSuccessHandler(securityContextLogoutHandler))
                    .logoutUrl(logout_processing_url).permitAll();
        }).csrf(httpSecurityCsrfConfigurer -> {
            httpSecurityCsrfConfigurer.disable();
        }).rememberMe(httpSecurityRememberMeConfigurer -> {
                    //cookie，默认保存两周
                    httpSecurityRememberMeConfigurer.rememberMeParameter("remember");
                });

        /**
         *  下面的先注释掉，但建议不要删除，方便查阅
         *  其也是一个构建 AuthenticationManager 的方式，经查看源码，大概了解到通过http.getSharedObject(AuthenticationManagerBuilder.class)获取的 authenticationManagerBuilder 应该是一个对象，来自于
         * {@link AuthenticationConfiguration#authenticationManagerBuilder(org.springframework.security.config.annotation.ObjectPostProcessor, org.springframework.context.ApplicationContext)}
         */
        //AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        //configure(authenticationManagerBuilder);
        //AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();

        /**
         * 注意这里不能设置，设置了会导致服务服务器 AuthenticationProvider 失效，因为这里提前构建了 AuthenticationManagerBuilder，框架中是在这里创建的{@link HttpSecurity#beforeConfigure()}
         */
        //http.authenticationManager(authenticationManager);


        // 必须保证 LoginUserToolPersistentSecurityFilter 在 TenantToolPersistentSecurityFilter 之前，有依赖关系,因为租户信息以登录用户的为准

        // 自定义当前登录用户工具类
        LoginUserToolPersistentSecurityFilter loginUserToolPersistentSecurityFilter = new LoginUserToolPersistentSecurityFilter();
        loginUserToolPersistentSecurityFilter.setSecurityFilterPersistentLoginUserReadyListenerList(securityFilterPersistentLoginUserReadyListenerList);
        loginUserToolPersistentSecurityFilter.setGrantedTenantResolveAndPersistentHelper(grantedTenantResolveAndPersistentHelper);
        http.addFilterAfter(loginUserToolPersistentSecurityFilter, SecurityContextPersistenceFilter.class);
        /**
         * 默认排序
         * @see https://docs.spring.io/spring-security/reference/5.7/servlet/configuration/xml-namespace.html#filter-stack
         */
        TenantToolPersistentSecurityFilter tenantToolPersistentSecurityFilter = new TenantToolPersistentSecurityFilter();
        tenantToolPersistentSecurityFilter.setGrantedTenantResolveAndPersistentHelper(grantedTenantResolveAndPersistentHelper);
        http.addFilterAfter(tenantToolPersistentSecurityFilter, SecurityContextPersistenceFilter.class);

        // 判断是否为匿名登录
        LoginUserToolAnonymousPersistentSecurityFilter anonymousPersistentSecurityFilter = new LoginUserToolAnonymousPersistentSecurityFilter();
        http.addFilterAfter(anonymousPersistentSecurityFilter, AnonymousAuthenticationFilter.class);
        /**
         * 提供不同的自定义配置
         */
        if (customWebSecurityConfigureList != null) {
            for (CustomWebSecurityConfigure customWebSecurityConfigure : customWebSecurityConfigureList) {
                customWebSecurityConfigure.configure(http,authenticationManagerBuilder,passwordEncoder(),ext);
            }
        }

        DefaultSecurityFilterChain build = http.build();
        return build;
    }
}
