package com.particle.oauth2authorization.domain.client;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.UUID;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.oauth2authorization.domain.enums.Oauth2AuthorizationAuthorizationGrantType;
import com.particle.oauth2authorization.domain.enums.Oauth2AuthorizationClientAuthenticationMethod;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * oauth2客户端 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Entity
public class Oauth2RegisteredClient extends AggreateRoot {

    private Oauth2RegisteredClientId id;

    /**
    * 客户端ID，唯一标识客户端
    */
    private String clientId;

    /**
    * 客户端ID的发布时间
    */
    private LocalDateTime clientIdIssuedAt;
    
    /**
    * 客户端秘钥，用于进行客户端认证
    */
    private String clientSecret;

    /**
    * 客户端秘钥的过期时间，为空表示不过期
    */
    private LocalDateTime clientSecretExpiresAt;
    
    /**
    * 客户端名称，用于展示
    */
    private String clientName;

    /**
    * 客户端的身份验证方法，用于验证客户端的身份
    */
    private String clientAuthenticationMethods;

    /**
    * 客户端支持的授权类型，用于指定客户端可以使用的授权方式
    */
    private String authorizationGrantTypes;

    /**
    * 客户端重定向URI，指定通过OAuth 2.0授权流程后重定向到的URI
    */
    private String redirectUris;

    /**
    * 客户端的访问范围，表示客户端可以请求的资源范围
    */
    private String scopes;

    /**
    * 客户端的设置，用于存储其他客户端特定的配置信息
    */
    private String clientSettings;

    /**
    * 令牌的设置，用于存储与令牌相关的配置信息
    */
    private String tokenSettings;


    /**
     * 生成clientid，添加时使用
     */
    public void generateClientId() {
        this.clientId = SnowflakeIdTool.nextId().toString();
    }

    /**
     * 设置发布时间，一般添加时使用
     */
    public void changeClientIdIssuedAtToNow() {
        this.clientIdIssuedAt = LocalDateTime.now();
    }

    /**
     * 生成密钥，添加时使用
     */
    public void generateClientSecret() {
        String rawPassword = UUID.fastUUID().toString(true);
        this.clientSecret = rawPassword;
    }

    /**
     * 默认认证方法，这和 {@link org.springframework.security.oauth2.server.authorization.client.RegisteredClient.Builder#build()} 中的逻辑一致，目的是保持数据尽量一致
     */
    public void changeClientAuthenticationMethodsToDefaultIfEmpty() {
        if (Strings.isEmpty(clientAuthenticationMethods)) {
            //相当于设置了值 ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue()
            this.clientAuthenticationMethods = Oauth2AuthorizationClientAuthenticationMethod.client_secret_basic.itemValue();
        }
    }

    /**
     * 默认客户端设置
     * 注意，必须保证依赖的其它属性已经设置完成
     * 依赖的属性如下：
     * {@link Oauth2RegisteredClient#clientAuthenticationMethods}
     * {@link Oauth2RegisteredClient#authorizationGrantTypes}
     * {@link Oauth2RegisteredClient#redirectUris}
     */
    public void changeClientSettingsToDefaultIfEmpty() {
        if (JsonTool.isJsonStrEmpty(this.clientSettings)) {
            RegisteredClient registeredClient = convertDefaultRegisteredClient();
            Map<String, Object> clientSettings = registeredClient.getClientSettings().getSettings();
            this.clientSettings = JsonTool.toJsonStr(clientSettings);
        }
    }
    /**
     * 默认token设置
     * 注意，必须保证依赖的其它属性已经设置完成
     * 依赖的属性如下：
     * {@link Oauth2RegisteredClient#clientAuthenticationMethods}
     * {@link Oauth2RegisteredClient#authorizationGrantTypes}
     * {@link Oauth2RegisteredClient#redirectUris}
     */
    public void changeTokenSettingsToDefaultIfEmpty() {
        if (JsonTool.isJsonStrEmpty(this.tokenSettings)) {
            RegisteredClient registeredClient = convertDefaultRegisteredClient();
            Map<String, Object> tokenSettings = registeredClient.getTokenSettings().getSettings();
            this.tokenSettings = JsonTool.toJsonStr(tokenSettings);
        }
    }
    /**
     * 主要是用来获取默认值
     * 依赖的属性如下：
     * {@link Oauth2RegisteredClient#clientAuthenticationMethods}
     * {@link Oauth2RegisteredClient#authorizationGrantTypes}
     * {@link Oauth2RegisteredClient#redirectUris}
     * @return
     */
    private RegisteredClient convertDefaultRegisteredClient() {
        RegisteredClient registeredClient = defaultRegisteredClient(
                Oauth2AuthorizationClientAuthenticationMethod.convertClientAuthenticationMethods(this.clientAuthenticationMethods),
                Oauth2AuthorizationAuthorizationGrantType.convertAuthorizationGrantTypes(this.authorizationGrantTypes),
                Strings.isEmpty(redirectUris) ? Collections.EMPTY_LIST : Arrays.stream(redirectUris.split(",")).collect(Collectors.toList())

        );
        return registeredClient;
    }

    /**
     * 创建oauth2客户端领域模型对象
     * @return oauth2客户端领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Oauth2RegisteredClient create(){
        return DomainFactory.create(Oauth2RegisteredClient.class);
    }


    /**
     * 默认的注册客户端,主要是用来获取 clientSettings、tokenSettings 默认值
     * @return
     */
    public static RegisteredClient defaultRegisteredClient(List<ClientAuthenticationMethod> clientAuthenticationMethods,
                                                           List<AuthorizationGrantType> authorizationGrantTypes,
                                                           List<String> redirectUris){
        RegisteredClient.Builder builder = RegisteredClient.withId("id只是一个占位，不使用")
                .clientId("clientId只是一个占位，不使用")
                .clientSecret("clientSecret只是一个占位，不使用");

        if (CollectionUtil.isNotEmpty(clientAuthenticationMethods)) {
            for (ClientAuthenticationMethod clientAuthenticationMethod : clientAuthenticationMethods) {
                builder.clientAuthenticationMethod(clientAuthenticationMethod);
            }
        }
        if (CollectionUtil.isNotEmpty(authorizationGrantTypes)) {
            for (AuthorizationGrantType authorizationGrantType : authorizationGrantTypes) {
                builder.authorizationGrantType(authorizationGrantType);

            }
        }
        if (CollectionUtil.isNotEmpty(redirectUris)) {
            for (String uris : redirectUris) {
                builder.redirectUri(uris);
            }
        }
        RegisteredClient client = builder.build();

        return client;
    }
}
