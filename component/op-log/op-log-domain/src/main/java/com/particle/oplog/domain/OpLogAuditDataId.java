package com.particle.oplog.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 操作日志审计数据 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
public class OpLogAuditDataId extends Id {

	public OpLogAuditDataId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 操作日志审计数据 领域模型id
	 * @param id
	 * @return
	 */
	public static OpLogAuditDataId of(Long id){
		return new OpLogAuditDataId(id);
	}
}
