package com.particle.func.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 菜单功能 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public class FuncId extends Id {

	public FuncId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 菜单功能 领域模型id
	 * @param id
	 * @return
	 */
	public static FuncId of(Long id){
		return new FuncId(id);
	}
}
