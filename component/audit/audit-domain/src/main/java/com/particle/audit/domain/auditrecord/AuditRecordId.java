package com.particle.audit.domain.auditrecord;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 审核记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
public class AuditRecordId extends Id {

	public AuditRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 审核记录 领域模型id
	 * @param id
	 * @return
	 */
	public static AuditRecordId of(Long id){
		return new AuditRecordId(id);
	}
}
