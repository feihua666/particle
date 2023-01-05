package com.particle.lowcode.domain.generator;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 低代码模型 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public class LowcodeModelId extends Id {

	public LowcodeModelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 低代码模型 领域模型id
	 * @param id
	 * @return
	 */
	public static LowcodeModelId of(Long id){
		return new LowcodeModelId(id);
	}
}
