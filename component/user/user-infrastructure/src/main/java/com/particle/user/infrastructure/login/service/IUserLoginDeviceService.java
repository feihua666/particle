package com.particle.user.infrastructure.login.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 用户登录设备 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public interface IUserLoginDeviceService extends IBaseService<UserLoginDeviceDO> {

	/**
	 * 根据用户 id 和 设备 id 查询
	 * @param userId
	 * @param deviceId
	 * @return
	 */
	default UserLoginDeviceDO getByUserIdAndDeviceId(Long userId, String deviceId) {
		Assert.notNull(userId,"userId不能为空");
		Assert.notNull(deviceId,"deviceId不能为空");

		return getOne(Wrappers.<UserLoginDeviceDO>lambdaQuery().eq(UserLoginDeviceDO::getUserId,userId).eq(UserLoginDeviceDO::getDeviceId,deviceId));
	}

	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	default List<UserLoginDeviceDO> getByUserId(Long userId) {
		Assert.notNull(userId,"userId不能为空");
		return list(Wrappers.<UserLoginDeviceDO>lambdaQuery().eq(UserLoginDeviceDO::getUserId,userId));
	}
}
