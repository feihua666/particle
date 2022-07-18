package com.particle.area.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 区域 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
public class AreaId extends Id {

	public AreaId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 区域 领域模型id
	 * @param id
	 * @return
	 */
	public static AreaId of(Long id){
		return new AreaId(id);
	}
}
