package com.particle.openplatform.domain.bill;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台供应商接口日汇总 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
public class OpenplatformProviderRecordPrdApiDaySummaryId extends Id {

	public OpenplatformProviderRecordPrdApiDaySummaryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台供应商接口日汇总 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderRecordPrdApiDaySummaryId of(Long id){
		return new OpenplatformProviderRecordPrdApiDaySummaryId(id);
	}
}
