package com.particle.global.mybatis.plus.fill;

/**
 * <p>
 * 获取当前登录用户 id
 * </p>
 *
 * @author yangwei
 * @since 2022-06-27 22:01
 */
public interface LoginUserIdResolver {
	/**
	 * 默认数据填充的用户id
	 */
	public static Long DEFAULT_USER_ID = 1L;

	Long resolve();
}
