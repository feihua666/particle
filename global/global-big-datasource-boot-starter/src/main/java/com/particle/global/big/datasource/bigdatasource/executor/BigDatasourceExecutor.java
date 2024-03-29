package com.particle.global.big.datasource.bigdatasource.executor;

/**
 * <p>
 * 大数据源 执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:42
 */
public interface BigDatasourceExecutor<R> {
	/**
	 * 执行代理模式响应
	 * @param command 执行参数
	 * @param queryString 针对http请求的请求地址参数字符串如：a=b&c=d
	 * @return 返回结果
	 */
	Object execute(Object command,String queryString);

}
