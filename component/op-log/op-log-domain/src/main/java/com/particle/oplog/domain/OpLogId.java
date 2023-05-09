package com.particle.oplog.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 操作日志 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
public class OpLogId extends Id {

	public OpLogId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 操作日志 领域模型id
	 * @param id
	 * @return
	 */
	public static OpLogId of(Long id){
		return new OpLogId(id);
	}
}
