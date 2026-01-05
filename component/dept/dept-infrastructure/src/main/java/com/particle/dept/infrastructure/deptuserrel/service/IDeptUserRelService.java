package com.particle.dept.infrastructure.deptuserrel.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 部门用户关系 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
public interface IDeptUserRelService extends IBaseService<DeptUserRelDO> {

	/**
	 * 根据用户id删除部门用户关系
	 * @param
	 * @return
	 */
	default boolean deleteByUserId(Long userId) {
		Assert.notNull(userId, "userId 不能为空");
		return remove(Wrappers.<DeptUserRelDO>lambdaQuery().eq(DeptUserRelDO::getUserId, userId));
	}

}
