package com.particle.openplatform.domain.openapirecord;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口调用记录参数 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
public class OpenplatformOpenapiRecordParamId extends Id {

	public OpenplatformOpenapiRecordParamId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口调用记录参数 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiRecordParamId of(Long id){
		return new OpenplatformOpenapiRecordParamId(id);
	}
}
