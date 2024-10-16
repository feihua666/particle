package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用开放接口日实时汇总 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId extends Id {

	public OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用开放接口日实时汇总 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId of(Long id){
		return new OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId(id);
	}
}
