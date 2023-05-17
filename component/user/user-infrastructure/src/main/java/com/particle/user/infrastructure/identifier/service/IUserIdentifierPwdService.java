package com.particle.user.infrastructure.identifier.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户密码 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierPwdService extends IBaseService<UserIdentifierPwdDO> {

	/**
	 * 根据编码查询
	 * @param identifierId
	 * @return
	 */
	default UserIdentifierPwdDO getByIdentifierId(Long identifierId) {
		Assert.notNull(identifierId,"identifierId不能为空");
		return getOne(Wrappers.<UserIdentifierPwdDO>lambdaQuery().eq(UserIdentifierPwdDO::getIdentifierId, identifierId));
	}
	/**
	 * 根据编码查询多个
	 * @param identifierIds
	 * @return
	 */
	default List<UserIdentifierPwdDO> getByIdentifierIds(List<Long> identifierIds) {
		Assert.notEmpty(identifierIds,"identifierIds不能为空");
		return list(Wrappers.<UserIdentifierPwdDO>lambdaQuery().in(UserIdentifierPwdDO::getIdentifierId, identifierIds));
	}
	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	List<UserIdentifierPwdDO> getByUserId(Long userId);

	/**
	 * 根据用户id重置用户密码
	 * @param userId
	 * @return
	 */
	boolean updatePasswordByUserId(Long userId,String encodedPassword);

	/**
	 * 根据identifierId重置用户密码
	 * @param identifierId
	 * @return
	 */
	boolean updatePasswordByIdentifierId(Long identifierId,String encodedPassword);

	/**
	 * 根据登录标识id删除
	 * @param identifierId
	 * @return
	 */
	default boolean deleteByIdentifierId(Long identifierId){
		return deleteByColumn(identifierId, UserIdentifierPwdDO::getIdentifierId);
	}


	/**
	 * 根据用户id删除
	 * @param userId
	 * @return
	 */
	default boolean deleteByUserId(Long userId) {
		return deleteByColumn(userId, UserIdentifierPwdDO::getUserId);
	}
}
