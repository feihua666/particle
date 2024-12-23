package com.particle.openplatform.client.app.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 开放平台算法 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-07 16:45:37
 */
@Data
@Schema
public class OpenplatformAlgorithmListCommand extends AbstractBaseQueryCommand {


    /**
     * 支持两种，分别支对应
     * degist: {@link com.particle.global.openapi.enums.OpenapiDigestAlgorithm}
     * signature: {@link com.particle.global.openapi.enums.OpenapiSignatureAlgorithm}
     */
    @NotEmpty(message = "算法类型 不能为空")
    @Schema(description = "算法类型,两种 degist 和signature")
    private String type;


    public enum AlgorithmType{
        /**
         * 摘要算法
         * {@link com.particle.global.openapi.enums.OpenapiDigestAlgorithm}
         */
        digest,
        /**
         * 签名算法
         * {@link com.particle.global.openapi.enums.OpenapiSignatureAlgorithm}
         */
        signature
    }
}
