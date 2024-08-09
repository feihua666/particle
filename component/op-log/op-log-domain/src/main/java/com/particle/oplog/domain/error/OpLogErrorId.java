package com.particle.oplog.domain.error;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 操作异常日志 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
public class OpLogErrorId extends Id {

	public OpLogErrorId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 操作异常日志 领域模型id
	 * @param id
	 * @return
	 */
	public static OpLogErrorId of(Long id){
		return new OpLogErrorId(id);
	}
}
