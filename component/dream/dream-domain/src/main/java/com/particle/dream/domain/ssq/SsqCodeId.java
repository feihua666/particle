package com.particle.dream.domain.ssq;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 双色球号码 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
public class SsqCodeId extends Id {

	public SsqCodeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 双色球号码 领域模型id
	 * @param id
	 * @return
	 */
	public static SsqCodeId of(Long id){
		return new SsqCodeId(id);
	}
}
