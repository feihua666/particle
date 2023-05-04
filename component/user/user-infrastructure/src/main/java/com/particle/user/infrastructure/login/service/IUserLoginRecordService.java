package com.particle.user.infrastructure.login.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户登录记录 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginRecordService extends IBaseService<UserLoginRecordDO> {


	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	default List<UserLoginRecordDO> getByUserId(Long userId) {
		Assert.notNull(userId,"userId不能为空");
		return list(Wrappers.<UserLoginRecordDO>lambdaQuery().eq(UserLoginRecordDO::getUserId, userId));
	}
}
