package com.particle.global.openapi.api.portal;

import java.util.List;

/**
 * <p>
 * 额外开放接口执行器供应商工厂
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 11:33
 */
public interface AdditionalOpenapiExecuteProviderFactory {
    /**
     * 额外开放接口执行器
     * @return
     */
    public List<OpenapiExecuteProvider> additionalOpenapiExecuteProviders();
}
