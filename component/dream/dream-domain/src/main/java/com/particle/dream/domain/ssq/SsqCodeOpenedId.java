package com.particle.dream.domain.ssq;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 双色球开奖 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
public class SsqCodeOpenedId extends Id {

	public SsqCodeOpenedId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 双色球开奖 领域模型id
	 * @param id
	 * @return
	 */
	public static SsqCodeOpenedId of(Long id){
		return new SsqCodeOpenedId(id);
	}
}
