package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口批量查询记录明细 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
public class OpenplatformOpenapiBatchQueryRecordDetailId extends Id {

	public OpenplatformOpenapiBatchQueryRecordDetailId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口批量查询记录明细 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiBatchQueryRecordDetailId of(Long id){
		return new OpenplatformOpenapiBatchQueryRecordDetailId(id);
	}
}
