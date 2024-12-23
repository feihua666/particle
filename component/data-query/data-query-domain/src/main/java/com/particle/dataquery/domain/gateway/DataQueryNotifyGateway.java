package com.particle.dataquery.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * 通知
 * </p>
 *
 * @author yangwei
 * @since 2024-01-19 15:36:40
 */
public interface DataQueryNotifyGateway extends IGateway {

	void notifySystem(String title, String contentType, String suggest, String content);
}
