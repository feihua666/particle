package com.particle.lowcode.domain.generator;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 低代码生成 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
public class LowcodeSegmentGenId extends Id {

	public LowcodeSegmentGenId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 低代码生成 领域模型id
	 * @param id
	 * @return
	 */
	public static LowcodeSegmentGenId of(Long id){
		return new LowcodeSegmentGenId(id);
	}
}
