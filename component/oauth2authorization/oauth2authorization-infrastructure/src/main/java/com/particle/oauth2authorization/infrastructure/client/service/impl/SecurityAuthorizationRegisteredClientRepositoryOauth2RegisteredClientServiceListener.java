package com.particle.oauth2authorization.infrastructure.client.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IDeleteServiceListener;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.oauth2authorization.domain.enums.Oauth2AuthorizationAuthorizationGrantType;
import com.particle.oauth2authorization.domain.enums.Oauth2AuthorizationClientAuthenticationMethod;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.mapper.Oauth2RegisteredClientMapper;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 如果在页面上操作，同步修改 authorization server 对应的客户端表
 * 主要是想使用原生的表存储对应的信息
 * </p>
 *
 * @author yangwei
 * @since 2023-07-26 17:23
 */
@Service
public class SecurityAuthorizationRegisteredClientRepositoryOauth2RegisteredClientServiceListener implements IAddServiceListener<Oauth2RegisteredClientDO> , IUpdateServiceListener<Oauth2RegisteredClientDO>, IDeleteServiceListener<Oauth2RegisteredClientDO> {

	public static final String table_name = "oauth2_registered_client";

	/**
	 * 使用无加密密码，参见：{@link com.particle.global.security.security.PasswordEncryptEnum#noop}
	 */
	private static final String encryptFlag = "{noop}";

	@Autowired
	private RegisteredClientRepository registeredClientRepository;
	@Autowired
	private NativeSqlMapper nativeSqlMapper;

	@Autowired
	private Oauth2RegisteredClientMapper oauth2RegisteredClientMapper;

	@Override
	public void postAdd(Oauth2RegisteredClientDO po) {



		RegisteredClient registeredClient = RegisteredClient
				.withId(po.getId().toString())
				.clientId(po.getClientId())
				.clientIdIssuedAt(po.getClientIdIssuedAt().toInstant(ZoneOffset.ofHours(8)))
				.clientSecret(encryptFlag + po.getClientSecret())
				.clientSecretExpiresAt(po.getClientSecretExpiresAt() == null ? null : po.getClientSecretExpiresAt().toInstant(ZoneOffset.ofHours(8)))
				.clientName(po.getClientName())
				.clientAuthenticationMethods(clientAuthenticationMethods -> {
					clientAuthenticationMethods.addAll(Oauth2AuthorizationClientAuthenticationMethod.convertClientAuthenticationMethods(po.getClientAuthenticationMethods()));
				})
				.authorizationGrantTypes(authorizationGrantTypes -> {
					authorizationGrantTypes.addAll(Oauth2AuthorizationAuthorizationGrantType.convertAuthorizationGrantTypes(po.getAuthorizationGrantTypes()));
				})
				.redirectUris(strings -> {
					if (Strings.isNotEmpty(po.getRedirectUris())) {
						strings.addAll(Arrays.stream(po.getRedirectUris().split(",")).collect(Collectors.toList()));
					}
				})
				.scopes(strings -> {
					if (Strings.isNotEmpty(po.getScopes())) {
						strings.addAll(Arrays.stream(po.getScopes().split(",")).collect(Collectors.toList()));
					}
				})
				.clientSettings(convertClientSettings(po.getClientSettings()))
				.tokenSettings(convertTokenSettings(po.getTokenSettings()))
				.build();

		registeredClientRepository.save(registeredClient);
	}

	@Override
	public void postDeleteById(Long id, Oauth2RegisteredClientDO oauth2RegisteredClientDO) {
		deleteById(id.toString());
	}

	@Override
	public void postDeleteByColumn(Object columnId, SFunction<Oauth2RegisteredClientDO, ?> column, List<Oauth2RegisteredClientDO> oauth2RegisteredClientDOS) {
		for (Oauth2RegisteredClientDO oauth2RegisteredClientDO : oauth2RegisteredClientDOS) {
			deleteById(oauth2RegisteredClientDO.getId().toString());
		}
	}

	@Override
	public void postUpdate(Oauth2RegisteredClientDO po) {
		Oauth2RegisteredClientDO oauth2RegisteredClientDO = oauth2RegisteredClientMapper.selectById(po.getId());
		postAdd(oauth2RegisteredClientDO);
	}

	/**
	 * 删除数据
	 * {@link RegisteredClientRepository} 中并没有提供删除的方式，这里使用sql删除
	 * @param id
	 */
	private void deleteById(String id) {
		nativeSqlMapper.delete(StrUtil.format("delete from {} where id = '{}'", table_name, id));
	}

	/**
	 * 转换为客户端设置实体类
	 * @param clientSettings
	 * @return
	 */
	private ClientSettings convertClientSettings(String clientSettings) {
		Map clientSettingsMapRaw = JSONUtil.toBean(clientSettings, Map.class);
		Map<String, Object> clientSettingsMap = new HashMap<>();
		// key 是一个字符串
		for (Object key : clientSettingsMapRaw.keySet()) {
			String strKey = ((String) key);
			Object value = clientSettingsMapRaw.get(strKey);
			// JSONNull 不兼容
			value = value instanceof JSONNull ? null : value;
			if (ConfigurationSettingNames.Client.TOKEN_ENDPOINT_AUTHENTICATION_SIGNING_ALGORITHM.equals(strKey)) {
				String jwsAlgorithmName = (String) value;
				/**
				 * 该key下是一个实现的{@link JwsAlgorithm}接口的枚举
				 */
				JwsAlgorithm jwsAlgorithm = MacAlgorithm.from(jwsAlgorithmName);
				if (jwsAlgorithm == null) {
					jwsAlgorithm = SignatureAlgorithm.from(jwsAlgorithmName);
				}
				value = jwsAlgorithm;
			}
			// JSONNull 不兼容
			clientSettingsMap.put(strKey, value instanceof JSONNull ? null : value);
		}
		return ClientSettings.withSettings(clientSettingsMap).build();
	}

	/**
	 * 转换为token设置实体类
	 * @param tokenSettings
	 * @return
	 */
	private TokenSettings convertTokenSettings(String tokenSettings) {
		Map tokenSettingsMapRaw = JSONUtil.toBean(tokenSettings, Map.class);
		Map<String, Object> tokenSettingsMap = new HashMap<>();
		// key 是一个字符串
		for (Object key : tokenSettingsMapRaw.keySet()) {
			String strKey = ((String) key);
			Object value = tokenSettingsMapRaw.get(strKey);
			// JSONNull 不兼容
			value = value instanceof JSONNull ? null : value;
			// duration转换
			if (ConfigurationSettingNames.Token.AUTHORIZATION_CODE_TIME_TO_LIVE.equals(strKey)
			|| ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE.equals(strKey)
			|| ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE.equals(strKey)) {
				String durationStr = (String) value;
				Duration parse = Duration.parse(durationStr);
				value = parse;
			}
			if (ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT.equals(strKey)) {
				// 如果 1. value 是 map 则一般是通过客户端 builder创建的，value 是 String 则一般是通过前端添加的，
				Map oAuth2TokenFormatMap = null;
				if (value instanceof Map) {
					oAuth2TokenFormatMap = (Map) value;
				}else if (value instanceof String) {
					oAuth2TokenFormatMap = JSONUtil.toBean(value.toString(),Map.class);
				}

				String oAuth2TokenFormatStrValue = (String)oAuth2TokenFormatMap.get("value");
				if (OAuth2TokenFormat.SELF_CONTAINED.getValue().equals(oAuth2TokenFormatStrValue)) {
					value = OAuth2TokenFormat.SELF_CONTAINED;
				}if (OAuth2TokenFormat.REFERENCE.getValue().equals(oAuth2TokenFormatStrValue)) {
					value = OAuth2TokenFormat.REFERENCE;
				}
			}
			if (ConfigurationSettingNames.Token.ID_TOKEN_SIGNATURE_ALGORITHM.equals(strKey)) {
				if (ConfigurationSettingNames.Client.TOKEN_ENDPOINT_AUTHENTICATION_SIGNING_ALGORITHM.equals(strKey)) {
					String SignatureAlgorithmName = (String) value;
					/**
					 * 该key下是一个实现的{@link JwsAlgorithm}接口的枚举
					 */
					SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.from(SignatureAlgorithmName);

					value = signatureAlgorithm;
				}
			}
			tokenSettingsMap.put(strKey, value instanceof JSONNull ? null : value);
		}
		return TokenSettings.withSettings(tokenSettingsMap).build();
	}

}
