package com.particle.oauth2authorization.client.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * oauth2客户端 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Schema
public class Oauth2RegisteredClientPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "客户端ID")
    private String clientId;


    @Schema(description = "客户端ID的发布时间")
    private LocalDateTime clientIdIssuedAt;


    @Schema(description = "客户端秘钥")
    private String clientSecret;


    @Schema(description = "客户端秘钥的过期时间")
    private LocalDateTime clientSecretExpiresAt;


    @Like
        @Schema(description = "客户端名称,左前缀匹配")
    private String clientName;















}
