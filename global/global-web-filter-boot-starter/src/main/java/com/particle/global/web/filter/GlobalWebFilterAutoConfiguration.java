package com.particle.global.web.filter;

import brave.Tracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.sleuth.autoconfig.TraceConfiguration;
import org.springframework.cloud.sleuth.autoconfig.instrument.web.SleuthWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * <p>
 * 过滤器们配置类
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 14:21
 */
@Configuration(proxyBeanMethods = true)
public class GlobalWebFilterAutoConfiguration {


	private static int span = 5;

	@Bean
	public RequestBodyReadableFilter requestBodyReadableFilterBean() {
		return new RequestBodyReadableFilter();
	}
	@Bean
	public RequestParamValidateFilter requestParamValidateFilterBean() {
		return new RequestParamValidateFilter();
	}
	@Bean
	public RequestResponseLogFilter requestResponseLogFilterBean() {
		return new RequestResponseLogFilter();
	}

	@Bean
	public CorsFilter corsFilterBean() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		configuration.setExposedHeaders(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}
	@Bean
	public FaviconFilter faviconFilterBean() {
		return new FaviconFilter();
	}

	@Bean
	public LogoFilter logoFilterBean() {
		return new LogoFilter();
	}

	@Bean
	public LogoTextFilter logoTextFilterBean() {
		return new LogoTextFilter();
	}

	@Bean
	public WebTitleFilter webTitleFilterBean() {
		return new WebTitleFilter();
	}

	@Configuration
	@ConditionalOnClass(TraceConfiguration.class)
	protected static class TraceConfigurationDependConfig{

		/**
		 * 响应头添加traceid过滤器
		 * @return
		 */
		@Bean
		@ConditionalOnClass(TraceConfiguration.class)
		public FilterRegistrationBean responseTraceIdFilter(Tracer tracer) {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(responseTraceIdFilterBean(tracer));
			registrationBean.setOrder(SleuthWebProperties.TRACING_FILTER_ORDER + span);
			return registrationBean;
		}

		@Bean
		@ConditionalOnClass(TraceConfiguration.class)
		public ResponseTraceIdFilter responseTraceIdFilterBean(Tracer tracer) {
			return new ResponseTraceIdFilter(tracer);
		}
	}

	/**
	 * 可读请求体过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean requestBodyReadableFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(requestBodyReadableFilterBean());
		registrationBean.setOrder(SleuthWebProperties.TRACING_FILTER_ORDER + span * 2);
		return registrationBean;
	}
	/**
	 * 参数校验过滤器，如签名等
	 * @return
	 */
	@Bean
	public FilterRegistrationBean requestParamValidateFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(requestParamValidateFilterBean());
		registrationBean.setOrder(SleuthWebProperties.TRACING_FILTER_ORDER + span * 3);
		return registrationBean;
	}

	/**
	 * 请求响应日志打印过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean requestResponseLogFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(requestResponseLogFilterBean());
		registrationBean.setOrder(SleuthWebProperties.TRACING_FILTER_ORDER + span * 4);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(corsFilterBean());
		registrationBean.setOrder(SleuthWebProperties.TRACING_FILTER_ORDER + span +2);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean faviconFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(faviconFilterBean());
		registrationBean.setUrlPatterns(Arrays.asList("/favicon.ico"));
		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean logoFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(logoFilterBean());
		registrationBean.setUrlPatterns(Arrays.asList("/logo"));
		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean logoTextFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(logoTextFilterBean());
		registrationBean.setUrlPatterns(Arrays.asList("/logo-text"));
		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean webTitleFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(webTitleFilterBean());
		registrationBean.setUrlPatterns(Arrays.asList("/web-title"));
		registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registrationBean;
	}
}
