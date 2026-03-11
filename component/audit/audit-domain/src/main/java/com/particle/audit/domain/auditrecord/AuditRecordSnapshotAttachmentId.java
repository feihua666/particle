package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 审核记录附件快照 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
public class AuditRecordSnapshotAttachmentId extends Id {

	public AuditRecordSnapshotAttachmentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 审核记录附件快照 领域模型id
	 * @param id
	 * @return
	 */
	public static AuditRecordSnapshotAttachmentId of(Long id){
		return new AuditRecordSnapshotAttachmentId(id);
	}
}
