package com.particle.global.web.mvc.config;

import com.particle.global.web.mvc.LoginUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * 全局 web mvc 配置
 * </p>
 *
 * @author yangwei
 * @since 2022-06-22 14:07
 */
@Configuration
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 注入当前登录用户
	 * @param resolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginUserArgumentResolver());
	}
}
