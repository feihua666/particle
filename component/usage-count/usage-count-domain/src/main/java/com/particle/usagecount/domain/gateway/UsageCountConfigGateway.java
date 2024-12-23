package com.particle.usagecount.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;

/**
 * <p>
 * 使用次数配置 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
public interface UsageCountConfigGateway extends IBaseGateway<UsageCountConfigId,UsageCountConfig> {
}
