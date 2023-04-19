package com.particle.dept.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 部门 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
public class DeptId extends Id {

	public DeptId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 部门 领域模型id
	 * @param id
	 * @return
	 */
	public static DeptId of(Long id){
		return new DeptId(id);
	}
}
