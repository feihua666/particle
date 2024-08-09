package com.particle.oplog.domain.error;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 操作异常日志内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
public class OpLogErrorContentId extends Id {

	public OpLogErrorContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 操作异常日志内容 领域模型id
	 * @param id
	 * @return
	 */
	public static OpLogErrorContentId of(Long id){
		return new OpLogErrorContentId(id);
	}
}
