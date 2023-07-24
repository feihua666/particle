package com.particle.global.security.security.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p>
 * 将租户信息持久化线程变量，方便后面获取
 * 支持按域名获取租户信息
 * </p>
 *
 * @author yangwei
 * @since 2023-07-13 14:42:05
 */
@Slf4j
public class TenantToolPersistentSecurityFilter extends GenericFilterBean {
	@Setter
	private GrantedTenantResolveAndPersistentHelper grantedTenantResolveAndPersistentHelper;
	@Override

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			grantedTenantResolveAndPersistentHelper.resolveAndPersistent(request);
			chain.doFilter(request, response);
		} finally {
			grantedTenantResolveAndPersistentHelper.removeFromPsrsistent();

		}
	}

}
