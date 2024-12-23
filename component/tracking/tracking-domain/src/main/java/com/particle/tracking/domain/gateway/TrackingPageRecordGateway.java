package com.particle.tracking.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.tracking.domain.TrackingPageRecord;
import com.particle.tracking.domain.TrackingPageRecordId;

/**
 * <p>
 * 页面埋点记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
public interface TrackingPageRecordGateway extends IBaseGateway<TrackingPageRecordId,TrackingPageRecord> {

}
