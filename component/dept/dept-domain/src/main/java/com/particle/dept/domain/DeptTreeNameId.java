package com.particle.dept.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 部门树名称 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
public class DeptTreeNameId extends Id {

	public DeptTreeNameId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 部门树名称 领域模型id
	 * @param id
	 * @return
	 */
	public static DeptTreeNameId of(Long id){
		return new DeptTreeNameId(id);
	}
}
