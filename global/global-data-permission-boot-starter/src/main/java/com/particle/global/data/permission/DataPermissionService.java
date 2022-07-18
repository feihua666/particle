package com.particle.global.data.permission;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;

/**
 * <p>
 * 数据权限服务
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 14:05
 */
public interface DataPermissionService {

	/**
	 * 数据范围约束条件包装
	 * @param wrapper
	 */
	AbstractWrapper dataConstraint(AbstractWrapper wrapper);
}
