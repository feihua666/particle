package com.particle.global.web.filter;

import brave.Tracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.sleuth.autoconfig.TraceConfiguration;
import org.springframework.cloud.sleuth.autoconfig.instrument.web.SleuthWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	@ConditionalOnClass(TraceConfiguration.class)
	public ResponseTraceIdFilter responseTraceIdFilterBean(Tracer tracer) {
		return new ResponseTraceIdFilter(tracer);
	}
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
}
