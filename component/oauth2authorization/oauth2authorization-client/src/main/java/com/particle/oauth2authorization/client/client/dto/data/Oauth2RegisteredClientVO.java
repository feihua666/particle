package com.particle.oauth2authorization.client.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * oauth2客户端 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Schema
public class Oauth2RegisteredClientVO extends AbstractBaseIdVO {

    @Schema(description = "客户端ID")
    private String clientId;
    
    @Schema(description = "客户端ID的发布时间")
    private LocalDateTime clientIdIssuedAt;
        
    @Schema(description = "客户端秘钥")
    private String clientSecret;
    
    @Schema(description = "客户端秘钥的过期时间")
    private LocalDateTime clientSecretExpiresAt;
        
    @Schema(description = "客户端名称")
    private String clientName;
    
    @Schema(description = "客户端的身份验证方法")
    private String clientAuthenticationMethods;
    
    @Schema(description = "客户端支持的授权类型")
    private String authorizationGrantTypes;
    
    @Schema(description = "客户端重定向URI")
    private String redirectUris;
    
    @Schema(description = "客户端的访问范围")
    private String scopes;
    
    @Schema(description = "客户端的设置")
    private String clientSettings;
    
    @Schema(description = "令牌的设置")
    private String tokenSettings;
    


}
