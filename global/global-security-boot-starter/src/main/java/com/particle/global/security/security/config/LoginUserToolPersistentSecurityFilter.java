package com.particle.global.security.security.config;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.mutable.MutablePair;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.security.login.SecurityFilterPersistentLoginUserReadyListener;
import com.particle.global.tool.json.JsonTool;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.particle.global.tool.json.JsonTool.jsonConfig;

/**
 * <p>
 * 持久化线程变量，方便后面获取
 * </p>
 *
 * @author yangwei
 * @since 2022-06-22 14:45
 */
@Slf4j
public class LoginUserToolPersistentSecurityFilter extends GenericFilterBean {

	@Setter
	private List<SecurityFilterPersistentLoginUserReadyListener> securityFilterPersistentLoginUserReadyListenerList;
	@Setter
	private GrantedTenantResolveAndPersistentHelper grantedTenantResolveAndPersistentHelper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {

			Object principal = Optional.ofNullable(SecurityContextHolder.getContext())
					.map(securityContext -> securityContext.getAuthentication())
					.map(authentication -> authentication.getPrincipal()).orElse(null);
			String userInfo = "";
			if (principal != null) {
				if (!(principal instanceof String)) {
					if (principal instanceof LoginUser) {
						LoginUserTool.saveToSession((LoginUser) principal, ((HttpServletRequest) request));
						LoginUserTool.setAnonymous(false);
					}
					userInfo = principalToString(principal);
				}
			}
			log.info("当前登录用户: loginUser={}", userInfo);
			if (securityFilterPersistentLoginUserReadyListenerList != null) {
				for (SecurityFilterPersistentLoginUserReadyListener securityFilterPersistentLoginUserReadyListener : securityFilterPersistentLoginUserReadyListenerList) {
					securityFilterPersistentLoginUserReadyListener.onLoginUserReady(request);
				}
			}
			grantedTenantResolveAndPersistentHelper.resolveAndPersistentIfNotExist(request);
			chain.doFilter(request, response);
		} finally {
			LoginUserTool.clear();

		}
	}


	/**
	 * 将当前登录用户转为string
	 * @param principal
	 * @return
	 */
	private String principalToString(Object principal) {
		JSONObject jsonObject = JSONUtil.parseObj(principal,
				jsonConfig);
		jsonObject.remove(LoginUser.userGrantedAuthoritiesFieldName);
		String userInfo = jsonObject.toJSONString(0, new Filter<MutablePair<Object, Object>>() {
			@Override
			public boolean accept(MutablePair<Object, Object> objectObjectMutablePair) {
				return !LoginUser.userGrantedAuthoritiesFieldName.equals(objectObjectMutablePair.getKey());
			}
		});
		return userInfo;
	}

}
