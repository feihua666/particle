package com.particle.oauth2authorization.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户端的身份验证方法 字典项
 * 对应：{@link org.springframework.security.oauth2.core.ClientAuthenticationMethod}
 * </p>
 *
 * @author yw
 * @since 2023-07-26 10:33:02
 */
public enum Oauth2AuthorizationClientAuthenticationMethod implements IDictItem {

	/**
	 * client_secret_basic
	 */
	client_secret_basic(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
	,
	/**
	 * client_secret_post
	 */
	client_secret_post(ClientAuthenticationMethod.CLIENT_SECRET_POST)
	,
	/**
	 * client_secret_jwt
	 */
	client_secret_jwt(ClientAuthenticationMethod.CLIENT_SECRET_JWT)
	,
	/**
	 * private_key_jwt
	 */
	private_key_jwt(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
	,
	/**
	 * none
	 */
	none(ClientAuthenticationMethod.NONE)
	;

	private ClientAuthenticationMethod clientAuthenticationMethod;

	Oauth2AuthorizationClientAuthenticationMethod(ClientAuthenticationMethod clientAuthenticationMethod) {
		this.clientAuthenticationMethod = clientAuthenticationMethod;
	}

	@Override
	public String itemValue() {
		return this.name();
	}

	public ClientAuthenticationMethod getClientAuthenticationMethod() {
		return clientAuthenticationMethod;
	}

	@Override
	public String groupCode() {
		return Group.oauth2_authorization_client_authentication_method.groupCode();
	}


	/**
	 * 转为 authorization server 方法对象
	 * @param clientAuthenticationMethods 多个以逗号分隔
	 * @return
	 */
	public static List<ClientAuthenticationMethod> convertClientAuthenticationMethods(String clientAuthenticationMethods) {
		if (Strings.isEmpty(clientAuthenticationMethods)) {
			return Collections.emptyList();
		}
		return  Arrays.stream(clientAuthenticationMethods.split(","))
				.map(Oauth2AuthorizationClientAuthenticationMethod::valueOf)
				.map(Oauth2AuthorizationClientAuthenticationMethod::getClientAuthenticationMethod)
				.collect(Collectors.toList());
	}

	/**
	 * 客户端的身份验证方法 字典组
	 */
	public enum Group implements IDictGroup {
		oauth2_authorization_client_authentication_method;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

