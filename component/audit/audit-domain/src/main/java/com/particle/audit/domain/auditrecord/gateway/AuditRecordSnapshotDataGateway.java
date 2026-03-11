package com.particle.audit.domain.auditrecord.gateway;

import com.particle.audit.domain.auditrecord.AuditRecordSnapshotData;
import com.particle.audit.domain.auditrecord.AuditRecordSnapshotDataId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 审核记录数据快照 防腐层
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
public interface AuditRecordSnapshotDataGateway extends IBaseGateway<AuditRecordSnapshotDataId,AuditRecordSnapshotData> {
}
