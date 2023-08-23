package com.particle.openplatform.domain.providerrecord;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
public class OpenplatformProviderRecordParamId extends Id {

	public OpenplatformProviderRecordParamId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口供应商调用记录参数 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderRecordParamId of(Long id){
		return new OpenplatformProviderRecordParamId(id);
	}
}
