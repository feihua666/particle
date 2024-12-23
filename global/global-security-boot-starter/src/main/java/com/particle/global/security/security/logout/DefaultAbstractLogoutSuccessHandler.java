package com.particle.global.security.security.logout;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 定义一个抽象的登出成功处理逻辑
 * 实现方式可以继承该类并实现 spring security 对应的 {@link LogoutSuccessHandler}
 * </p>
 *
 * @author yangwei
 * @since 2021-09-28 10:08
 */
@Slf4j
public abstract class DefaultAbstractLogoutSuccessHandler {

	@Autowired
	protected List<ILogoutSuccessResultService> iAuthenticationResultServices;

	/**
	 * 手动注入，如果没有自动注入的情况下，因为可能是直接new出来的没有托管给spring
	 */
	private void autowiredIAuthenticationResultServicesIfNecessary(){
		if (CollectionUtil.isEmpty(iAuthenticationResultServices)) {
			try {
				Map<String, ILogoutSuccessResultService> beansOfType = ApplicationContextForSecurityHelper.getApplicationContext().getBeansOfType(ILogoutSuccessResultService.class);
				iAuthenticationResultServices = beansOfType.values().stream().collect(Collectors.toList());
			}catch (BeansException e){
				log.warn("手动注入，未找到 ILogoutSuccessResultService 的实现");
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
	public void tryNotifyILogoutSuccessResultServiceOnSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication, Response response) throws IOException {
		autowiredIAuthenticationResultServicesIfNecessary();
		if (CollectionUtil.isNotEmpty(iAuthenticationResultServices)) {
			for (ILogoutSuccessResultService iLogoutSuccessResultService : iAuthenticationResultServices) {
				iLogoutSuccessResultService.onLogoutSuccess(httpServletRequest,httpServletResponse,authentication);
			}
		}
	}
}
