package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台供应商接口月汇总 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
public class OpenplatformProviderRecordPrdApiMonthSummaryId extends Id {

	public OpenplatformProviderRecordPrdApiMonthSummaryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台供应商接口月汇总 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderRecordPrdApiMonthSummaryId of(Long id){
		return new OpenplatformProviderRecordPrdApiMonthSummaryId(id);
	}
}
