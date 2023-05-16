package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 用户相关远程调用
 * </p>
 *
 * @author yangwei
 * @since 2023-05-15 13:01:20
 */
public interface TenantUserUserGateway extends IGateway {


	/**
	 * 添加用户
	 * @param name
	 * @param nickname
	 * @param identifier
	 * @param identityTypeDictId
	 * @param password
	 * @return 返回成功后用户的id
	 */
	Long createUser(String name, String nickname, String identifier,Long identityTypeDictId,String password,String userAddScene) ;


	/**
	 * 根据登录标识查询用户id
	 * @param identifier
	 * @return 存在返回用户id，不存在返回null
	 */
	Long getByUserIdentifier(String identifier);
}
