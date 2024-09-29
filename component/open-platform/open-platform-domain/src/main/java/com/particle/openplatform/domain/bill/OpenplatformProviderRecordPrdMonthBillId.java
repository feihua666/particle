package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台供应商月账单 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
public class OpenplatformProviderRecordPrdMonthBillId extends Id {

	public OpenplatformProviderRecordPrdMonthBillId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台供应商月账单 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderRecordPrdMonthBillId of(Long id){
		return new OpenplatformProviderRecordPrdMonthBillId(id);
	}
}
