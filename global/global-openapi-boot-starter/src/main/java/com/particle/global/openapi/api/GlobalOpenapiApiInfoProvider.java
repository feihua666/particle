package com.particle.global.openapi.api;

import com.particle.global.openapi.data.ApiInfo;

/**
 * <p>
 * 开放接口接口地址权限码
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:10
 */
public interface GlobalOpenapiApiInfoProvider {

	/**
	 * 获取apiUrl的配置信息
	 * @param apiUrl
	 * @param appId
	 * @return
	 */
	ApiInfo getApiInfo(String apiUrl,String appId);
}
