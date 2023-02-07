package com.particle.lowcode.domain.generator;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 低代码片段模板 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
public class LowcodeSegmentTemplateId extends Id {

	public LowcodeSegmentTemplateId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 低代码片段模板 领域模型id
	 * @param id
	 * @return
	 */
	public static LowcodeSegmentTemplateId of(Long id){
		return new LowcodeSegmentTemplateId(id);
	}
}
