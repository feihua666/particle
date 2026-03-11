package com.particle.global.web.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.dto.login.GrantedTenant;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.light.share.constant.GlobalRpcConstants;
import com.particle.global.light.share.login.LoginConstants;
import com.particle.global.tool.login.LoginUserTool;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.global.tool.tenant.TenantTool;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 * 租户过滤器
 * 该过滤器用于处理租户信息，从请求中获取租户信息，并设置到线程中，方便后续使用
 * 一般需要配置在登录前和登录后，再次处理，确保登录后使用用户登录的信息
 * </p>
 *
 * @author yangwei
 * @since 2026-01-30 09:58:28
 */
@Slf4j
public class TenantContextFilter extends OncePerRequestFilter {
	private static String header_c_host = LoginConstants.header_c_host;

	private static final String tenant_context_request_tenant_id_key = "tenant_context_request_tenant_id_key";

	@Autowired(required = false)
	private TenantIdResolver tenantIdResolver;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String tenantIdStr = JakartaServletUtil.getHeaderIgnoreCase(request, GlobalRpcConstants.request_header_tenant_id);
		if (StrUtil.isNotEmpty(tenantIdStr)) {
			Long tenantId = Long.parseLong(tenantIdStr);
			handleTenantId(tenantId,"requestHeader");
		}else{
			LoginUser loginUser = LoginUserTool.getLoginUser();
			if (loginUser != null) {
				handleFromUser(loginUser);
			} else {
				handleFromRequest(request);
			}
		}
		filterChain.doFilter(request,response);
	}
	/**
	 * 从用户中获取租户信息
	 * @param loginUser
	 */
	private void handleFromUser(LoginUser loginUser) {
		GrantedTenant currentTenant = loginUser.getCurrentTenant();
		Long tenantId = Optional.ofNullable(currentTenant)
				.map(tenant -> tenant.getId())
				.orElse(null);
		handleTenantId(tenantId,"loginUser");
	}
	/**
	 * 从请求中获取租户信息
	 * @param request
	 */
	private void handleFromRequest(HttpServletRequest request) {
		// 尝试从session中获取
		HttpSession session = request.getSession(false);
		if (session != null) {
			Long tenantId = (Long)session.getAttribute(tenant_context_request_tenant_id_key);
			if (tenantId != null && tenantId > 0) {
				handleTenantId(tenantId,"session");
				return;
			}
		}
		String realDomain = JakartaServletUtil.getHeaderIgnoreCase(request,header_c_host);
        if (StrUtil.isEmpty(realDomain)) {
			realDomain = RequestTool.getRealDomain(request, false, true);
		}
		String finalRealDomain = realDomain;
		Long tenantId = Optional.ofNullable(tenantIdResolver)
				.map(tenantIdResolver -> tenantIdResolver.resolveTenantId(finalRealDomain))
				.orElse(null);
		handleTenantId(tenantId,"domain");
		if (session != null) {
			session.setAttribute(tenant_context_request_tenant_id_key,tenantId);
		}
	}
	private void handleTenantId(Long tenantId,String source) {
		if (tenantId != null) {
			log.info("resolve tenantId from {}. set tenantId:{}",source,tenantId);
			TenantTool.setTenantId(tenantId);
		}else {
			TenantTool.none();
		}
	}
	/**
	 * 租户id解析器
	 */
	public static interface TenantIdResolver {

		/**
		 * 解析租户id
		 * @param domainWithPort 域名带端口如：www.example.com、www.example.com:8080
		 * @return
		 */
		Long resolveTenantId(String domainWithPort);
	}

}
