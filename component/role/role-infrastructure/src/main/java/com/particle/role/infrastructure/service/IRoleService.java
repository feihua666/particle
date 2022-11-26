package com.particle.role.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleService extends IBaseService<RoleDO> {

	/**
	 * 根据角色编码查询
	 * @param code
	 * @return
	 */
	default RoleDO getByCode(String code) {
		Assert.hasText(code,"code不能为空");
		return getOne(Wrappers.<RoleDO>lambdaQuery().eq(RoleDO::getCode, code));
	}
	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	List<RoleDO> getByUserId(Long userId, Boolean isDisabled);

	/**
	 * 根据功能id查询
	 * @param funcId
	 * @return
	 */
	List<RoleDO> getByFuncId(Long funcId,Boolean isDisabled);
}
