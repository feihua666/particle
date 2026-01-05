package com.particle.role.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.role.infrastructure.dos.RoleDO;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

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
	 * 根据名称查询
	 * @param name
	 * @return
	 */
    default List<RoleDO> listByName(String name) {
		Assert.hasText(name,"name不能为空");
		return list(Wrappers.<RoleDO>lambdaQuery().eq(RoleDO::getName, name));
	}
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    default List<RoleDO> listByLikeName(String name) {
        Assert.hasText(name,"name不能为空");
        return list(Wrappers.<RoleDO>lambdaQuery().like(RoleDO::getName, name));
    }
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
	List<RoleDO> listByUserId(Long userId, Boolean isDisabled);
    /**
	 * 根据用户ids查询
	 * @param userIds
	 * @param isDisabled
	 * @return
	 */
	List<RoleDO> listByUserIds(List<Long> userIds, Boolean isDisabled);

	/**
	 * 根据用户id查询
	 * @param userIds
	 * @param isDisabled
	 * @return key为用户id
	 */
	Map<Long,List<RoleDO>> getByUserIds(List<Long> userIds, Boolean isDisabled);

	/**
	 * 根据功能id查询
	 * @param funcId
	 * @return
	 */
	List<RoleDO> listByFuncId(Long funcId, Boolean isDisabled);
	/**
	 * 根据角色类型字典id查询
	 * @param roleTypeDictId
	 * @return
	 */
	default List<RoleDO> listByRoleTypeDictId(Long roleTypeDictId, Boolean isDisabled) {
		List<RoleDO> list = list(Wrappers.<RoleDO>lambdaQuery().eq(RoleDO::getTypeDictId, roleTypeDictId).eq(isDisabled != null,RoleDO::getIsDisabled,isDisabled));
		return list;
	}

	/**
	 * 根据 roleIds 查询
	 * @param roleIds
	 * @param isDisabled
	 * @return
	 */
	default List<RoleDO> listByRoleIds(List<Long> roleIds, Boolean isDisabled){
		List<RoleDO> list = list(Wrappers.<RoleDO>lambdaQuery().in(RoleDO::getId, roleIds).eq(isDisabled != null,RoleDO::getIsDisabled,isDisabled));
		return list;
	}
}
