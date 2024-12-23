package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 定义一个登录成功和失败的处理类，主要用来处理登录成功或失败的额外操作
 * 实现方式可以继承该类并实现 spring security 对应的 {@link AuthenticationFailureHandler} 或 {@link AuthenticationSuccessHandler}
 * </p>
 *
 * @author yangwei
 * @since 2021-09-28 10:08
 */
@Slf4j
public class GlobalSecurityAuthenticationHandler {

	@Autowired(required = false)
	protected List<IAuthenticationResultService> iAuthenticationResultServices;

	/**
	 * 手动注入，如果没有自动注入的情况下，因为可能是直接new出来的没有托管给spring
	 */
	private void autowiredIAuthenticationResultServicesIfNecessary(){

		if (CollectionUtil.isEmpty(iAuthenticationResultServices)) {
			try {
				Map<String, IAuthenticationResultService> beansOfType = ApplicationContextForSecurityHelper.getApplicationContext().getBeansOfType(IAuthenticationResultService.class);
				iAuthenticationResultServices = beansOfType.values().stream().collect(Collectors.toList());
			}catch (BeansException e){
				log.warn("手动注入，未找到 IAuthenticationResultService 的实现");
			}
		}
	}

	/**
	 * 认证成功通知调用
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param authentication
	 * @throws IOException
	 */
	public void tryNotifyIAuthenticationResultServicesOnSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication, Response response) throws IOException {
		autowiredIAuthenticationResultServicesIfNecessary();
		if (CollectionUtil.isNotEmpty(iAuthenticationResultServices)) {
			for (IAuthenticationResultService iAuthenticationResultService : iAuthenticationResultServices) {
				iAuthenticationResultService.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication,response);
			}
		}
	}

	/**
	 * 认证失败
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e
	 * @throws IOException
	 */
	public void tryNotifyIAuthenticationResultServicesOnFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e, Response response) throws IOException {
		autowiredIAuthenticationResultServicesIfNecessary();
		if (CollectionUtil.isNotEmpty(iAuthenticationResultServices)) {
			for (IAuthenticationResultService iAuthenticationResultService : iAuthenticationResultServices) {
				iAuthenticationResultService.onAuthenticationFailure(httpServletRequest,httpServletResponse,e,response);
			}
		}
	}
}
