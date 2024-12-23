package com.particle.global.openapi.api.portal;

import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;

/**
 * <p>
 * 开放接口执行供应商
 * </p>
 *
 * @author yangwei
 * @since 2023-08-17 14:01
 */
public interface OpenapiExecuteProvider {

	/**
	 * 是否支持某个接口
	 *
	 * @param apiCode
	 * @return
	 */
	boolean supportApi(String apiCode);

	/**
	 * 获取 供应商 编码
	 * 该编码必须同接口配置保持一致
	 * 用来匹配该接口支持的供应商
	 * @return
	 */
	String getProviderIdentifier();


	/**
	 * 执行
	 * 注意：在处理过程中需要收集供应商调用数据 参见：{@link OpenapiContext#addProviderDTO(com.particle.global.openapi.data.OpenapiCollectProviderDTO)}
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	public Object execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext);
}
