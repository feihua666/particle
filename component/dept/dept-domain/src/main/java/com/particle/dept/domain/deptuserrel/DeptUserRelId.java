package com.particle.dept.domain.deptuserrel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 部门用户关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:09
 */
public class DeptUserRelId extends Id {

	public DeptUserRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 部门用户关系 领域模型id
	 * @param id
	 * @return
	 */
	public static DeptUserRelId of(Long id){
		return new DeptUserRelId(id);
	}
}
