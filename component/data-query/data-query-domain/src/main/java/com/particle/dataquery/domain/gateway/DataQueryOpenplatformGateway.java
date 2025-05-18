package com.particle.dataquery.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.dataquery.domain.value.DataQueryOpenplatformProvider;

/**
 * <p>
 * 数据查询依赖开放平台 网关
 * </p>
 *
 * @author yangwei
 * @since 2025/5/8 12:40
 */
public interface DataQueryOpenplatformGateway extends IGateway {
    /**
     * 根据数据查询提供者id获取开放平台提供者信息
     * @param dataQueryProviderId
     * @return
     */
    public DataQueryOpenplatformProvider getOpenplatformProviderByDataQueryProviderId(Long dataQueryProviderId);
}
