package com.particle.func.domain.funcapplicationfuncrel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 功能应用功能关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
public class FuncApplicationFuncRelId extends Id {

	public FuncApplicationFuncRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 功能应用功能关系 领域模型id
	 * @param id
	 * @return
	 */
	public static FuncApplicationFuncRelId of(Long id){
		return new FuncApplicationFuncRelId(id);
	}
}
