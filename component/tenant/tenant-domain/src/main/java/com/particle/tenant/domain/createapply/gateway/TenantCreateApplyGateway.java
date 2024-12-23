package com.particle.tenant.domain.createapply.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;

/**
 * <p>
 * 租户创建申请 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
public interface TenantCreateApplyGateway extends IBaseGateway<TenantCreateApplyId,TenantCreateApply> {
}
