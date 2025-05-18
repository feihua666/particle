package com.particle.tools.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公钥和私钥VO
 * </p>
 *
 * @author yangwei
 * @since 2025/4/20 11:05
 */
@Accessors(chain = true)
@Data
@Schema
public class PublicAndPrivateKeyVO extends VO {
    @Schema(description = "公钥")
    private String publicKey;

    @Schema(description = "私钥")
    private String privateKey;

    public static PublicAndPrivateKeyVO create(String publicKey, String privateKey) {
        PublicAndPrivateKeyVO publicAndPrivateKeyVO = new PublicAndPrivateKeyVO();
        publicAndPrivateKeyVO.setPublicKey(publicKey);
        publicAndPrivateKeyVO.setPrivateKey(privateKey);
        return publicAndPrivateKeyVO;
    }
}
