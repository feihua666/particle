package com.particle.oauth2authorization.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 客户端访问范围 字典项
 * 对应：{@link org.springframework.security.oauth2.core.oidc.OidcScopes}
 * </p>
 *
 * @author yw
 * @since 2023-07-26 10:40:09
 */
public enum Oauth2AuthorizationScope implements IDictItem {

	/**
	 * openid
	 */
	openid
	,
	/**
	 * profile
	 */
	profile
	,
	/**
	 * email
	 */
	email
	,
	/**
	 * address
	 */
	address
	,
	/**
	 * phone
	 */
	phone
	;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.oauth2_authorization_scope.groupCode();
	}

	/**
	 * 客户端访问范围 字典组
	 */
	public enum Group implements IDictGroup {
		oauth2_authorization_scope;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}

