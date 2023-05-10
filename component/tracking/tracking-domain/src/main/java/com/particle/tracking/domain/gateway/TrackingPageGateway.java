package com.particle.tracking.domain.gateway;

import com.particle.tracking.domain.TrackingPage;
import com.particle.tracking.domain.TrackingPageId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 埋点页面 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
public interface TrackingPageGateway extends IBaseGateway<TrackingPageId,TrackingPage> {
}
