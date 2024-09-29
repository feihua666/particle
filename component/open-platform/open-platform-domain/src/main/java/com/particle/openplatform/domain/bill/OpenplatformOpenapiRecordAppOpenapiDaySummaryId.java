package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用开放接口日汇总 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryId extends Id {

	public OpenplatformOpenapiRecordAppOpenapiDaySummaryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用开放接口日汇总 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiRecordAppOpenapiDaySummaryId of(Long id){
		return new OpenplatformOpenapiRecordAppOpenapiDaySummaryId(id);
	}
}
