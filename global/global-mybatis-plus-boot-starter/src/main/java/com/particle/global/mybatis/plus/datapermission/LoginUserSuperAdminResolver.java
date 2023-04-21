package com.particle.global.mybatis.plus.datapermission;

/**
 * <p>
 * 获取当前登录用户是否为超级管理员，如果为超级管理员，会涉及一些数据权限不限制等操作
 * </p>
 *
 * @author yangwei
 * @since 2023-04-21 14:48:34
 */
public interface LoginUserSuperAdminResolver {

	/**
	 * 默认用户不是超级管理员
	 */
	public static boolean DEFAULT_USER_SUPER_ADMIN = false;


	boolean resolve();
}
