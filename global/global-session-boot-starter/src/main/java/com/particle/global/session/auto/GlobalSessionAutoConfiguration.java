package com.particle.global.session.auto;

import com.particle.global.session.GlobalSessionProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.Cookie;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.session.web.http.SessionRepositoryFilter;

import java.time.Duration;
import java.util.Arrays;

/**
 * <p>
 * session解决方案自动配置类
 * </p>
 * @see SessionAutoConfiguration
 * @author yangwei
 * @since 2022-07-29 14:13
 */
@EnableConfigurationProperties(GlobalSessionProperties.class)
@Configuration
public class GlobalSessionAutoConfiguration {

	/**
	 * 支持 token 和 cookie两种方式
	 * 默认如果不使用任何配置请求头和COOKIE名称都为:SESSION
	 * 可能过 server.servlet.session.cookie.name 修改
	 * @return
	 */
	@Bean
	public HttpSessionIdResolver customDelegateHttpSessionIdResolver(ServerProperties serverProperties,
																	 ObjectProvider<DefaultCookieSerializerCustomizer> cookieSerializerCustomizers){
		Session.Cookie cookie = serverProperties.getServlet().getSession().getCookie();
		CustomDefaultCookieSerializer cookieSerializer = new CustomDefaultCookieSerializer();
		PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
		map.from(cookie::getName).to(cookieSerializer::setCookieName);
		map.from(cookie::getDomain).to(cookieSerializer::setDomainName);
		map.from(cookie::getPath).to(cookieSerializer::setCookiePath);
		map.from(cookie::getHttpOnly).to(cookieSerializer::setUseHttpOnlyCookie);
		map.from(cookie::getSecure).to(cookieSerializer::setUseSecureCookie);
		map.from(cookie::getMaxAge).asInt(Duration::getSeconds).to(cookieSerializer::setCookieMaxAge);
		map.from(cookie::getSameSite).as(Cookie.SameSite::attributeValue).to(cookieSerializer::setSameSite);
		cookieSerializerCustomizers.orderedStream().forEach((customizer) -> customizer.customize(cookieSerializer));

		CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
		cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer);

		/**
		 * 请求头支持session
		 * 默认会在响应返回时添加响应头session 头信息，参见：{@link SessionRepositoryFilter.SessionRepositoryRequestWrapper#commitSession()}
		 */
		HeaderHttpSessionIdResolver headerHttpSessionIdResolver = new HeaderHttpSessionIdResolver(cookieSerializer.getCookieName());

		return new CustomDelegateHttpSessionIdResolver(
				Arrays.asList(
						headerHttpSessionIdResolver,
						cookieHttpSessionIdResolver
				)
		);
	}



}
