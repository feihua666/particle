package com.particle.usagecount.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.usagecount.domain.UsageCountRecordDetail;
import com.particle.usagecount.domain.UsageCountRecordDetailId;

/**
 * <p>
 * 使用次数记录明细 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
public interface UsageCountRecordDetailGateway extends IBaseGateway<UsageCountRecordDetailId,UsageCountRecordDetail> {
}
