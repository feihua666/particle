package com.particle.usagecount.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 使用次数记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
public class UsageCountRecordId extends Id {

	public UsageCountRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 使用次数记录 领域模型id
	 * @param id
	 * @return
	 */
	public static UsageCountRecordId of(Long id){
		return new UsageCountRecordId(id);
	}
}
