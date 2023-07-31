package com.particle.oauth2authorization.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户端授权类型 字典项
 * 对应：{@link org.springframework.security.oauth2.core.AuthorizationGrantType}
 * </p>
 *
 * @author yw
 * @since 2023-07-26 10:35:28
 */
public enum Oauth2AuthorizationAuthorizationGrantType implements IDictItem {

	/**
	 * authorization_code
	 */
	authorization_code(AuthorizationGrantType.AUTHORIZATION_CODE)
	,
	/**
	 * refresh_token
	 */
	refresh_token(AuthorizationGrantType.REFRESH_TOKEN)
	,
	/**
	 * client_credentials
	 */
	client_credentials(AuthorizationGrantType.CLIENT_CREDENTIALS)
	,
	/**
	 * password
	 */
	password(AuthorizationGrantType.PASSWORD)
	,
	/**
	 * urn:ietf:params:oauth:grant-type:jwt-bearer
	 */
	urn__ietf__params__oauth__grant_type__jwt_bearer(AuthorizationGrantType.JWT_BEARER)
	;

	Oauth2AuthorizationAuthorizationGrantType(AuthorizationGrantType authorizationGrantType) {
		this.authorizationGrantType = authorizationGrantType;
	}

	private AuthorizationGrantType authorizationGrantType;

	public AuthorizationGrantType getAuthorizationGrantType() {
		return authorizationGrantType;
	}

	@Override
	public String itemValue() {
		if (this == urn__ietf__params__oauth__grant_type__jwt_bearer) {
			return authorizationGrantType.getValue();
		}
		return this.name();
	}

	/**
	 * 自定义valueof，因为实际存储的和枚举 name 不对应
	 * @param value
	 * @return
	 */
	public static Oauth2AuthorizationAuthorizationGrantType myValueOf(String value) {
		if (urn__ietf__params__oauth__grant_type__jwt_bearer.itemValue().equals(value)) {
			return urn__ietf__params__oauth__grant_type__jwt_bearer;
		}
		return Oauth2AuthorizationAuthorizationGrantType.valueOf(value);
	}

	/**
	 * 转为 authorization server 方法对象
	 * @param authorizationGrantTypes 多个以逗号分隔
	 * @return
	 */
	public static List<AuthorizationGrantType> convertAuthorizationGrantTypes(String authorizationGrantTypes) {
		if (Strings.isEmpty(authorizationGrantTypes)) {
			return Collections.emptyList();
		}
		return  Arrays.stream(authorizationGrantTypes.split(","))
				.map(Oauth2AuthorizationAuthorizationGrantType::myValueOf)
				.map(Oauth2AuthorizationAuthorizationGrantType::getAuthorizationGrantType)
				.collect(Collectors.toList());
	}


	@Override
	public String groupCode() {
		return Group.oauth2_authorization_authorization_grant_type.groupCode();
	}

	/**
	 * 客户端授权类型 字典组
	 */
	public enum Group implements IDictGroup {
		oauth2_authorization_authorization_grant_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

