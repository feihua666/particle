package com.particle.usagecount.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 使用次数记录明细 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
public class UsageCountRecordDetailId extends Id {

	public UsageCountRecordDetailId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 使用次数记录明细 领域模型id
	 * @param id
	 * @return
	 */
	public static UsageCountRecordDetailId of(Long id){
		return new UsageCountRecordDetailId(id);
	}
}
