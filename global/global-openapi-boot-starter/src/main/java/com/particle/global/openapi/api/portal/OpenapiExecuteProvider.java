package com.particle.global.openapi.api.portal;

import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;

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
	 * @param apiCode 接口编码、接口标识
	 * @param apiVersion 接口版本
	 * @return
	 */
	boolean supportApi(String apiCode,String apiVersion);

	/**
	 * 是否支持某个供应商
	 * 用来匹配该接口支持的供应商
	 * @return
	 */
	boolean supportProvider(String providerCode);


	/**
	 * 执行
	 * 注意：在处理过程中需要收集供应商调用数据 参见：{@link OpenapiContext#addProviderDTO(com.particle.global.openapi.data.OpenapiCollectProviderDTO)}
	 * @param openapiCommand
	 * @param openapiContext
	 * @return
	 */
	public <T> T  execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext);

	/**
	 * 是否支持入库
	 * @return
	 */
	default boolean supportWareHouse() {
		return false;
	}

	/**
	 * 入库支持
	 * @param warehouseCommand
	 * @param openapiCommand
	 * @param openapiContext
	 */
	default public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand, OpenapiContext openapiContext) {

	}
}
