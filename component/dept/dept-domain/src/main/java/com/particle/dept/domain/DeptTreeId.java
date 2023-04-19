package com.particle.dept.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 部门树 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
public class DeptTreeId extends Id {

	public DeptTreeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 部门树 领域模型id
	 * @param id
	 * @return
	 */
	public static DeptTreeId of(Long id){
		return new DeptTreeId(id);
	}
}
