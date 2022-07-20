package com.particle.dict.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 字典 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public class DictId extends Id {

	public DictId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 字典 领域模型id
	 * @param id
	 * @return
	 */
	public static DictId of(Long id){
		return new DictId(id);
	}
}
