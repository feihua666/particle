package com.particle.user.infrastructure.identifier.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户登录标识 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IUserIdentifierService extends IBaseService<UserIdentifierDO> {

	/**
	 * 根据登录标识查询
	 * @param identifier
	 * @return
	 */
	default UserIdentifierDO getByIdentifier(String identifier) {
		Assert.hasText(identifier,"identifier不能为空");
		return getOne(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getIdentifier, identifier));
	}
	/**
	 * 根据登录标识查询
	 * @param identifiers
	 * @return
	 */
	default List<UserIdentifierDO> getByIdentifiers(List<String> identifiers) {
		Assert.notEmpty(identifiers,"identifiers不能为空");
		return list(Wrappers.<UserIdentifierDO>lambdaQuery().in(UserIdentifierDO::getIdentifier, identifiers));
	}

	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	default List<UserIdentifierDO> getByUserId(Long userId) {
		Assert.notNull(userId,"userId不能为空");
		return list(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getUserId, userId));
	}
	/**
	 * 根据用户id和分组标识查询
	 * @param userId
	 * @return
	 */
	default List<UserIdentifierDO> getByUserIdAndGroupFlag(Long userId,String groupFlag) {
		Assert.notNull(userId,"userId不能为空");
		Assert.hasText(groupFlag,"groupFlag不能为空");
		return list(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getUserId, userId).eq(UserIdentifierDO::getGroupFlag,groupFlag));
	}
	/**
	 * 主要用于查询用户的identifier字段
	 * 谨慎使用，请注意数据准备性，有可能返回多条，尽量使用 {@link IUserIdentifierService#getByUserIdAndIdentifierTypeDictIdAndGroupFlag(java.lang.Long, java.lang.Long, java.lang.String)}
	 * @param userId
	 * @param identifierTypeDictId
	 * @return
	 */
	default UserIdentifierDO getByUserIdAndIdentifierTypeDictId(Long userId,Long identifierTypeDictId) {
		Assert.notNull(userId,"userId 不能为空");
		Assert.notNull(identifierTypeDictId,"identifierType 不能为空");
		return getOne(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getUserId, userId).eq(UserIdentifierDO::getIdentityTypeDictId,identifierTypeDictId));
	}
	/**
	 * 主要用于查询用户的identifier字段
	 * @param userId
	 * @param identifierTypeDictId
	 * @return
	 */
	default UserIdentifierDO getByUserIdAndIdentifierTypeDictIdAndGroupFlag(Long userId,Long identifierTypeDictId,String groupFlag) {
		Assert.notNull(userId,"userId 不能为空");
		Assert.notNull(identifierTypeDictId,"identifierType 不能为空");
		Assert.hasText(groupFlag,"groupFlag不能为空");
		return getOne(Wrappers.<UserIdentifierDO>lambdaQuery().eq(UserIdentifierDO::getUserId, userId)
				.eq(UserIdentifierDO::getIdentityTypeDictId,identifierTypeDictId)
				.eq(UserIdentifierDO::getGroupFlag,groupFlag));
	}

	/**
	 * 根据用户id删除
	 * @param userId
	 * @return
	 */
	default boolean deleteByUserId(Long userId) {
		return deleteByColumn(userId, UserIdentifierDO::getUserId);
	}
}
