package com.particle.area.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 区域领域模型id
 * </p>
 *
 * @author yangwei
 * @since 2022-04-30 17:43
 */
public class AreaId extends Id {

	public AreaId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转区域id
	 * @param id
	 * @return
	 */
	public static AreaId of(Long id){
		return new AreaId(id);
	}
}
