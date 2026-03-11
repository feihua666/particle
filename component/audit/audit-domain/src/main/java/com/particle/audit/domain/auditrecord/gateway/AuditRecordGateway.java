package com.particle.audit.domain.auditrecord.gateway;

import com.particle.audit.domain.auditrecord.AuditRecord;
import com.particle.audit.domain.auditrecord.AuditRecordId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 审核记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
public interface AuditRecordGateway extends IBaseGateway<AuditRecordId,AuditRecord> {
}
