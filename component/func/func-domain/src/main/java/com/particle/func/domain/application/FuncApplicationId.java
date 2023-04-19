package com.particle.func.domain.application;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 功能应用 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
public class FuncApplicationId extends Id {

	public FuncApplicationId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 功能应用 领域模型id
	 * @param id
	 * @return
	 */
	public static FuncApplicationId of(Long id){
		return new FuncApplicationId(id);
	}
}
