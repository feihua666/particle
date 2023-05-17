package com.particle.dept.domain.depttreeuserrel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 部门树用户关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
public class DeptTreeUserRelId extends Id {

	public DeptTreeUserRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 部门树用户关系 领域模型id
	 * @param id
	 * @return
	 */
	public static DeptTreeUserRelId of(Long id){
		return new DeptTreeUserRelId(id);
	}
}
