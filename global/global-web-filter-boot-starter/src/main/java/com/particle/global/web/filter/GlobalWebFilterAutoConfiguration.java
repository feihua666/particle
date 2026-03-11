package com.particle.global.web.filter;

import com.particle.global.light.share.filter.FilterConstants;
import com.particle.global.light.share.login.LoginConstants;
import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
@AutoConfigureAfter(name = "org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration")
@Configuration(proxyBeanMethods = true)
public class GlobalWebFilterAutoConfiguration {

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
	@ConditionalOnBooleanProperty(prefix = "particle.global.web.filter.cors", name = "enabled",matchIfMissing =  true)
	public CorsFilter corsFilterBean() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowedMethods(Arrays.asList(CorsConfiguration.ALL));
		configuration.setAllowedHeaders(Arrays.asList(CorsConfiguration.ALL));
		/**
		 * 前端 withCredentials: true 后，这里不能单独设置 *，否则拿不到 自定义的响应头
		 * 想要暴露响应头，需要单独设置
		 */
		configuration.setExposedHeaders(Arrays.asList(
				CorsConfiguration.ALL,
				HttpHeaders.AUTHORIZATION,
				HttpHeaders.CONTENT_DISPOSITION,
				LoginConstants.header_c_token,
				ResponseTraceIdFilter.RESPONSE_TRACE_ID_KEY
		));

		/**
		 * 允许前端发送凭据,主要是在跨域情况下携带 cookie
		 * 需要前端请求允许携带 cookie，如下：
		 * // 前端请求
		 * axios.post('/api/login', data, {
		 *   withCredentials: true  // 需要后端配合 allowCredentials(true)
		 * })
		 */
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}
	@Bean
    @ConditionalOnBean(FaviconFilter.FaviconResolver.class)
	public FaviconFilter faviconFilterBean() {
		return new FaviconFilter();
	}

	@Bean
    @ConditionalOnBean(LogoFilter.LogoResolver.class)
	public LogoFilter logoFilterBean() {
		return new LogoFilter();
	}

	@Bean
    @ConditionalOnBean(LogoTextFilter.LogoTextResolver.class)
	public LogoTextFilter logoTextFilterBean() {
		return new LogoTextFilter();
	}

	@Bean
    @ConditionalOnBean(WebTitleFilter.WebTitleResolver.class)
	public WebTitleFilter webTitleFilterBean() {
		return new WebTitleFilter();
	}
	@Bean
	public ThreadContextFilter threadContextFilterBean() {
		return new ThreadContextFilter();
	}
	@Bean
	public TenantContextLoginFilter tenantContextLoginFilterBean() {
		return new TenantContextLoginFilter();
	}
	@Bean
	public TenantContextFilter tenantContextFilterBean() {
		return new TenantContextFilter();
	}

	@Bean
	@ConditionalOnBooleanProperty(prefix = "particle.global.web.filter.cors", name = "enabled",matchIfMissing =  true)
	public FilterRegistrationBean corsFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(corsFilterBean());
		registrationBean.setOrder(FilterConstants.corsFilterOrder);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean threadContextFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(threadContextFilterBean());
		registrationBean.setOrder(FilterConstants.threadContextFilterOrder );
		return registrationBean;
	}

	/**
	 * 响应头添加traceid过滤器
	 * 条件配置参考：{@link org.springframework.boot.actuate.autoconfigure.tracing.BraveAutoConfiguration}
	 */
	@Configuration(proxyBeanMethods = true)
	@ConditionalOnClass({ Tracer.class})
	@ConditionalOnBooleanProperty(prefix = "particle.global.web.filter.response-trace-id", name = "enabled",matchIfMissing =  true)
	protected static class TraceConfigurationDependConfig{

		/**
		 * 响应头添加traceid过滤器
		 * @return
		 */
		@Bean
		@ConditionalOnBean(Tracer.class)
		public FilterRegistrationBean responseTraceIdFilter(Tracer tracer) {
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setFilter(responseTraceIdFilterBean(tracer));
			registrationBean.setOrder(FilterConstants.responseTraceIdFilterOrder);
			return registrationBean;
		}

		@Bean
		@ConditionalOnBean(Tracer.class)
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
		registrationBean.setOrder(FilterConstants.requestBodyReadableFilterOrder);
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
		registrationBean.setOrder(FilterConstants.requestParamValidateFilterOrder);
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
		registrationBean.setOrder(FilterConstants.requestResponseLogFilterOrder);
		return registrationBean;
	}

	/**
	 * 确保在登录之前获取租户信息
	 * @return
	 */
	@Bean
	public FilterRegistrationBean tenantContextLoginFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(tenantContextLoginFilterBean());
		registrationBean.setUrlPatterns(Arrays.asList(LoginConstants.login_url, LoginConstants.login_captcha_url));
		registrationBean.setOrder(FilterConstants.tenantContextLoginFilterOrder);
		return registrationBean;
	}

	/**
	 * 确保在登录之后获取租户信息
	 * @return
	 */
	@Bean
	public FilterRegistrationBean tenantContextFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(tenantContextFilterBean());
		registrationBean.setOrder(FilterConstants.tenantContextFilterOrder);
		return registrationBean;
	}
	@Bean
    @ConditionalOnBean(FaviconFilter.class)
	public FilterRegistrationBean faviconFilter(FaviconFilter faviconFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(faviconFilter);
		registrationBean.setUrlPatterns(Arrays.asList("/favicon.ico"));
		registrationBean.setOrder(FilterConstants.faviconFilterOrder);
		return registrationBean;
	}
	@Bean
    @ConditionalOnBean(LogoFilter.class)
	public FilterRegistrationBean logoFilter(LogoFilter logoFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(logoFilter);
		registrationBean.setUrlPatterns(Arrays.asList("/logo"));
		registrationBean.setOrder( FilterConstants.logoFilterOrder);
		return registrationBean;
	}
	@Bean
    @ConditionalOnBean(LogoTextFilter.class)
	public FilterRegistrationBean logoTextFilter(LogoTextFilter logoTextFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(logoTextFilter);
		registrationBean.setUrlPatterns(Arrays.asList("/logo-text"));
		registrationBean.setOrder( FilterConstants.logoTextFilterOrder);
		return registrationBean;
	}
	@Bean
    @ConditionalOnBean(WebTitleFilter.class)
	public FilterRegistrationBean webTitleFilter(WebTitleFilter webTitleFilter) {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(webTitleFilter);
		registrationBean.setUrlPatterns(Arrays.asList("/web-title"));
		registrationBean.setOrder( FilterConstants.webTitleFilterOrder);
		return registrationBean;
	}


	@Bean
	public UsageCountFilter usageCountFilterBean() {
		return new UsageCountFilter();
	}

	/**
	 * 使用次数统计过滤器，顺序在 spirng security 之后
	 * @return
	 */
	@Bean
	public FilterRegistrationBean usageCountFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(usageCountFilterBean());
		registrationBean.setOrder(FilterConstants.usageCountFilterOrder);
		return registrationBean;
	}
}
