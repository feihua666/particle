package com.particle.tenant.domain.createapply;

import com.particle.common.domain.event.DomainEvent;

/**
 * <p>
 * 租户创建申请审核领域事件
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 14:32
 */
public class TenantCreateApplyAuditDomainEvent extends DomainEvent<Long> {
	/**
	 * 构造方法
	 * @param identifier
	 * @param data 为申请数据表id
	 */
	public TenantCreateApplyAuditDomainEvent(String identifier, Long data) {
		super(identifier, data);
	}
}
