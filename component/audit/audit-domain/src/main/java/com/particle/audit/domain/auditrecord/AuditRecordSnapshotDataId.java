package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 审核记录数据快照 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
public class AuditRecordSnapshotDataId extends Id {

	public AuditRecordSnapshotDataId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 审核记录数据快照 领域模型id
	 * @param id
	 * @return
	 */
	public static AuditRecordSnapshotDataId of(Long id){
		return new AuditRecordSnapshotDataId(id);
	}
}
