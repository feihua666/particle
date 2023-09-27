package com.particle.report.domain.gateway;

/**
 * <p>
 * 报告依赖数据查询api服务远程调用
 * </p>
 *
 * @author yangwei
 * @since 2023-09-11 11:25
 */
public interface ReportDataQueryDataApiGateway {
	/**
	 * 调用数据查询api
	 * @param code 一般是一个类似于url的字符串 如：/jc/quality_analyse
	 * @param command
	 * @param queryString
	 * @return
	 */
	Object invoke(String code, Object command, String queryString);
}


