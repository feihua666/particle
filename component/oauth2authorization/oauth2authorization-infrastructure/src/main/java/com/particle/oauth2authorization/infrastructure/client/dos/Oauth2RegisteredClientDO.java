package com.particle.oauth2authorization.infrastructure.client.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * oauth2客户端表
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@TableName("component_oauth2_registered_client")
public class Oauth2RegisteredClientDO extends BaseDO {

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


}
