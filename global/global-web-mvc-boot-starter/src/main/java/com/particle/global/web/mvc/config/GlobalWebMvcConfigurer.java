package com.particle.global.web.mvc.config;

import com.particle.global.web.mvc.LoginUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 全局 web mvc 配置
 * </p>
 *
 * @author yangwei
 * @since 2022-06-22 14:07
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class GlobalWebMvcConfigurer implements WebMvcConfigurer {

	/**
	 * 该converter是在 {@org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration.MappingJackson2HttpMessageConverterConfiguration#mappingJackson2HttpMessageConverter(com.fasterxml.jackson.databind.ObjectMapper)} 中注入的
	 */
	@Autowired(required = false)
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	@Autowired(required = false)
	private StringHttpMessageConverter stringHttpMessageConverter;
	@Autowired(required = false)
	private ByteArrayHttpMessageConverter byteArrayHttpMessageConverter;

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		/**
		 * spring mvc 默认添加了converter {@link WebMvcConfigurationSupport#addDefaultHttpMessageConverters(java.util.List)}
		 * 将默认的去掉，自动注入的添加了更多配置信息
		 */
		if (Objects.nonNull(mappingJackson2HttpMessageConverter)) {
			converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
			converters.add(0, mappingJackson2HttpMessageConverter);
		}
		if (Objects.nonNull(stringHttpMessageConverter)) {
			converters.removeIf(converter -> converter instanceof StringHttpMessageConverter);
			converters.add(0, stringHttpMessageConverter);
		}
		// 添加了swagger openapi3，原因是其资源大部分返回的字节数组 参见：https://github.com/springdoc/springdoc-openapi/issues/2246
		// 注意顺序很重要
		if (Objects.nonNull(byteArrayHttpMessageConverter)) {
			addByteArrayHttpMessageConverter(converters, byteArrayHttpMessageConverter);
		}else {
			ByteArrayHttpMessageConverter byteArrayHttpMessageConverterInited = null;
			for (HttpMessageConverter<?> converter : converters) {
				if (converter instanceof ByteArrayHttpMessageConverter) {
					byteArrayHttpMessageConverterInited = ((ByteArrayHttpMessageConverter) converter);
					break;
				}
			}
			if (Objects.nonNull(byteArrayHttpMessageConverterInited)) {
				addByteArrayHttpMessageConverter(converters, byteArrayHttpMessageConverterInited);
			}else {
				addByteArrayHttpMessageConverter(converters, new ByteArrayHttpMessageConverter());

			}
		}
	}

	/**
	 * 主要是为了使用springdoc添加
	 * @param converters
	 * @param byteArrayHttpMessageConverter
	 */
	private void addByteArrayHttpMessageConverter(List<HttpMessageConverter<?>> converters,ByteArrayHttpMessageConverter byteArrayHttpMessageConverter) {
		if (Objects.nonNull(byteArrayHttpMessageConverter)) {
			converters.removeIf(converter -> converter instanceof ByteArrayHttpMessageConverter);
			converters.add(0, byteArrayHttpMessageConverter);
		}
	}
	/**
	 * 注入当前登录用户
	 * @param resolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new LoginUserArgumentResolver());
	}
}
