package com.particle.global.openapi.api;

import com.particle.global.openapi.collect.OpenapiContext;

/**
 * <p>
 * 将收集的数据存储服务
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 11:42
 */
public interface GlobalOpenapiCollectPersistentService {


	void save(OpenapiContext openapiContext);
}
