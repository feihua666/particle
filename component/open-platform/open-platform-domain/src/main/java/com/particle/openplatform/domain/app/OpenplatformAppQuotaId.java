package com.particle.openplatform.domain.app;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用额度 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
public class OpenplatformAppQuotaId extends Id {

	public OpenplatformAppQuotaId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用额度 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformAppQuotaId of(Long id){
		return new OpenplatformAppQuotaId(id);
	}
}
