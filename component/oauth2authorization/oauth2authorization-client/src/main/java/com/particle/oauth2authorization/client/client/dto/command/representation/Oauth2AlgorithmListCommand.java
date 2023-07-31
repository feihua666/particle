package com.particle.oauth2authorization.client.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * oauth2客户端 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Data
@Schema
public class Oauth2AlgorithmListCommand extends AbstractBaseQueryCommand {


    /**
     * 支持两种，分别支对应
     * mac: org.springframework.security.oauth2.jose.jws.MacAlgorithm
     * signature: org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
     */
    @Schema(description = "客户端ID,两种 mac和signature")
    private String type;


    public enum AlgorithmType{
        mac,signature
    }
}
