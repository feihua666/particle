package com.particle.oauth2authorization.client.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * oauth2算法 通用列表查询指令对象
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
     * mac: {@link org.springframework.security.oauth2.jose.jws.MacAlgorithm}
     * signature: {@link org.springframework.security.oauth2.jose.jws.SignatureAlgorithm}
     */
    @Schema(description = "算法类型,两种 mac和signature")
    private String type;


    public enum AlgorithmType{
        /**
         * 消息确认码
         * mac: {@link org.springframework.security.oauth2.jose.jws.MacAlgorithm}
         */
        mac,
        /**
         * 签名
         * signature: {@link org.springframework.security.oauth2.jose.jws.SignatureAlgorithm}
         */
        signature
    }
}
