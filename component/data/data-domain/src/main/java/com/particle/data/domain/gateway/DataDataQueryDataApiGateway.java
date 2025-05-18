package com.particle.data.domain.gateway;

/**
 * <p>
 * 数据模块依赖数据查询api服务远程调用
 * </p>
 *
 * @author yangwei
 * @since 2025-04-23 17:33:31
 */
public interface DataDataQueryDataApiGateway {
	/**
	 * 调用数据查询api
	 * @param code 一般是一个类似于url的字符串 如：/jc/quality_analyse
	 * @param command
	 * @param queryString
	 * @return
	 */
	Object invoke(String code, Object command, String queryString);
}


