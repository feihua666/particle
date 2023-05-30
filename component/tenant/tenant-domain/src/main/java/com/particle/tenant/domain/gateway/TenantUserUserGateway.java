package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import lombok.Getter;
import lombok.Setter;

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
	 * @param identifiers
	 * @param password
	 * @return 返回成功后用户的id
	 */
	Long createUser(String name, String nickname, List<IdentifierParam> identifiers,String password,String userAddScene) ;


	/**
	 * 根据登录标识查询用户id
	 * @param identifier
	 * @return 存在返回用户id，不存在返回null
	 */
	Long getByUserIdentifier(String identifier);

	/**
	 *
	 * @param userId
	 * @return
	 */
	String getIdentifierByUserIdAndType(Long userId, Long identityTypeDictId);

	@Setter
	@Getter
	public static class IdentifierParam{
		private String identifier;

		/**
		 * 字典id和下面的字典值二选一
		 */
		private Long identityTypeDictId;
		/**
		 * 上面的字典id和字典值二选一
		 */
		private String identityTypeDictValue;

		public static IdentifierParam create(String identifier, Long identityTypeDictId, String identityTypeDictValue) {
			IdentifierParam identifierParam = new IdentifierParam();

			identifierParam.setIdentifier(identifier);
			identifierParam.setIdentityTypeDictId(identityTypeDictId);
			identifierParam.setIdentityTypeDictValue(identityTypeDictValue);

			return identifierParam;
		}
	}
}
