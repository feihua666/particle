package com.particle.global.openapi.api;

import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.OpenapiCollectProviderDTO;

import java.util.List;

/**
 * <p>
 * 将收集的数据存储服务
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 11:42
 */
public interface GlobalOpenapiCollectPersistentService {


	/**
	 * 以开放平台为入口的统一保存开放平台调用记录，包括供应商调用记录
	 * @param openapiContext
	 */
	void save(OpenapiContext openapiContext);

	/**
	 * 保存供应商调用记录
	 * 在某一个接口中，调用多次供应商数据时，可能会很大，单独保存，以降低内存使用
	 * @param openapiContext
	 */
	void saveProvider(OpenapiContext openapiContext);
}
