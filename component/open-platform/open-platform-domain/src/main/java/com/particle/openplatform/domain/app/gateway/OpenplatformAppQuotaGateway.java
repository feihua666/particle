package com.particle.openplatform.domain.app.gateway;

import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 开放平台应用额度 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
public interface OpenplatformAppQuotaGateway extends IBaseGateway<OpenplatformAppQuotaId,OpenplatformAppQuota> {
}
