package com.particle.global.big.datasource.bigdatasource.impl.http.httpclient.impl.feign;

import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>
 * 自定义feign client builder，提供静态工具方法
 * todo 基于 feign的client实现
 * </p>
 *
 * @author yangwei
 * @since 2023-03-28 11:19
 */
public class BigDatasourceFeignClientBuilder implements ApplicationContextAware {

	public static FeignClientBuilder builder;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		builder = new FeignClientBuilder(applicationContext);
	}
}
