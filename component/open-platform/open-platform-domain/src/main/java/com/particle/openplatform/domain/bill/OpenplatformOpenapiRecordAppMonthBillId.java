package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用月账单 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
public class OpenplatformOpenapiRecordAppMonthBillId extends Id {

	public OpenplatformOpenapiRecordAppMonthBillId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用月账单 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiRecordAppMonthBillId of(Long id){
		return new OpenplatformOpenapiRecordAppMonthBillId(id);
	}
}
