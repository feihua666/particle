package com.particle.usagecount.domain.gateway;

import com.particle.usagecount.domain.UsageCountRecord;
import com.particle.usagecount.domain.UsageCountRecordId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 使用次数记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
public interface UsageCountRecordGateway extends IBaseGateway<UsageCountRecordId,UsageCountRecord> {
}
