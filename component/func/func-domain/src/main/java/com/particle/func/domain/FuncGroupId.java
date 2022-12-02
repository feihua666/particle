package com.particle.func.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 功能组 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
public class FuncGroupId extends Id {

	public FuncGroupId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 功能组 领域模型id
	 * @param id
	 * @return
	 */
	public static FuncGroupId of(Long id){
		return new FuncGroupId(id);
	}
}
