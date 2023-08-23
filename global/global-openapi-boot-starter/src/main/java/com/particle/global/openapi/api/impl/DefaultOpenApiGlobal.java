package com.particle.global.openapi.api.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.openapi.api.AbstractGlobalOpenapi;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * <p>
 * 全局默认开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:04
 */
public class DefaultOpenApiGlobal extends AbstractGlobalOpenapi {

	public DefaultOpenApiGlobal(Set<String> urlPatterns) {
		super();
		// 默认支持开放接口开头的接口
		super.urlPatterns.add("/openapi/**");
		if (CollectionUtil.isNotEmpty(urlPatterns)) {
			super.urlPatterns.addAll(urlPatterns);
		}
	}

	@Override
	public boolean support(HttpServletRequest request) {
		for (AntPathRequestMatcher antPathRequestMatcher : antPathRequestMatchers) {
			boolean matches = antPathRequestMatcher.matches(request);
			if (matches) {
				return matches;
			}
		}
		return false;
	}

}
