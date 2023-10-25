package com.particle.usagecount.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 使用次数配置 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
public class UsageCountConfigId extends Id {

	public UsageCountConfigId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 使用次数配置 领域模型id
	 * @param id
	 * @return
	 */
	public static UsageCountConfigId of(Long id){
		return new UsageCountConfigId(id);
	}
}
