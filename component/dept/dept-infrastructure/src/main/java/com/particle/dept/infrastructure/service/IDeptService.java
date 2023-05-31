package com.particle.dept.infrastructure.service;

import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
public interface IDeptService extends IBaseService<DeptDO> {

	/**
	 * 根据用户id获取用户部门
	 * @param userId
	 * @return
	 */
	DeptDO getByUserId(Long userId);

	/**
	 * key=用户id，value=部门
	 * @param userIds
	 * @return
	 */
	Map<Long,DeptDO> getMapByUserIds(List<Long> userIds);



}
