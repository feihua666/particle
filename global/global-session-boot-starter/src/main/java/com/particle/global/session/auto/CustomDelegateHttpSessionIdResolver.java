package com.particle.global.session.auto;

import cn.hutool.core.util.StrUtil;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义sessionid解决，支持http请求Header和Cookie两种方式
 * </p>
 *
 * @author yangwei
 * @since 2022-07-29 14:20
 */
public class CustomDelegateHttpSessionIdResolver implements HttpSessionIdResolver {

	private List<HttpSessionIdResolver> list;

	public CustomDelegateHttpSessionIdResolver(List<HttpSessionIdResolver> list){
		this.list = list;
	}

	@Override
	public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
		for (HttpSessionIdResolver httpSessionIdResolver : list) {
			List<String> strings = httpSessionIdResolver.resolveSessionIds(httpServletRequest);
			strings = strings.stream().filter(StrUtil::isNotBlank).collect(Collectors.toList());
			if (!strings.isEmpty()) {
				return strings;
			}
		}
		return Collections.emptyList();
	}

	@Override
	public void setSessionId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {
		for (HttpSessionIdResolver httpSessionIdResolver : list) {
			httpSessionIdResolver.setSessionId(httpServletRequest, httpServletResponse, s);
		}
	}

	@Override
	public void expireSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		for (HttpSessionIdResolver httpSessionIdResolver : list) {
			httpSessionIdResolver.expireSession(httpServletRequest, httpServletResponse);
		}
	}
}
