package com.particle.global.openapi.api.portal.impl;

import com.particle.global.openapi.api.portal.OpenapiExecuteProvider;

/**
 * <p>
 * 供应商执行 本地数据库数据 基础类
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 12:57:22
 */
public abstract class AbstractLocalDataBaseDataOpenapiExecuteProvider implements OpenapiExecuteProvider {

	public static final String provider_code = "local_database_data";

	@Override
	public boolean supportProvider(String providerCode) {
		return provider_code.equals(providerCode);
	}
}
