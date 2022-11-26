package com.particle.user.adapter.login;

import com.particle.global.security.security.config.CustomWebSecurityConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户登录配置
 * </p>
 *
 * @author yangwei
 * @since 2022-11-25 20:16
 */
@Component
public class UserDefaultLoginCustomWebSecurityConfigure implements CustomWebSecurityConfigure {

	@Autowired
	private IdentifierUserDetailsServiceImpl identifierUserDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
		auth.userDetailsService(identifierUserDetailsService).passwordEncoder(passwordEncoder);
	}
}
