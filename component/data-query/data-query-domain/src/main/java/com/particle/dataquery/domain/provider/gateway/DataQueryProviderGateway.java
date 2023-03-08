package com.particle.dataquery.domain.provider.gateway;

import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 数据查询供应商 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
public interface DataQueryProviderGateway extends IBaseGateway<DataQueryProviderId,DataQueryProvider> {
}
