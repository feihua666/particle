package com.particle.tools.adapter.web.front;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tools.client.dto.command.AesDecryptCommand;
import com.particle.tools.client.dto.command.AesEncryptCommand;
import com.particle.tools.client.dto.command.RsaDecryptCommand;
import com.particle.tools.client.dto.command.RsaEncryptCommand;
import com.particle.tools.client.dto.data.PublicAndPrivateKeyVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by yangwei
 * Created at 2025-04-20 10:13:18
 */
@Tag(name = "rsa相关接口")
@RestController
@RequestMapping("/front/web/rsa")
public class RsaController extends AbstractBaseWebAdapter {

    @Operation(summary = "生成随机base64编码密钥")
    @GetMapping("/randomKey/base64")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<PublicAndPrivateKeyVO> randomKey() {
        KeyPair keyPair = SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue());

        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();

        byte[] encodedPublic = aPublic.getEncoded();
        byte[] encodedPrivate = aPrivate.getEncoded();

        String randomKeyPublic = Base64.encode(encodedPublic);
        String randomKeyPrivate = Base64.encode(encodedPrivate);

        PublicAndPrivateKeyVO publicAndPrivateKeyVO = PublicAndPrivateKeyVO.create(randomKeyPublic, randomKeyPrivate);
        return SingleResponse.of(publicAndPrivateKeyVO);
    }
    @Operation(summary = "加密")
    @PostMapping("/encrypt/base64")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> encrypt( @RequestBody @Validated RsaEncryptCommand form) {
        RSA rsa = SecureUtil.rsa(null, Base64.decode(form.getPublicKey()));
        String encryptBase64 = rsa.encryptBase64(form.getData(), KeyType.PublicKey);
        return SingleResponse.of(encryptBase64);
    }

    @Operation(summary = "解密")
    @PostMapping("/decrypt")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> decrypt( @RequestBody @Validated RsaDecryptCommand form) {
        RSA rsa = SecureUtil.rsa(Base64.decode(form.getPrivateKey()),null);
        String encryptBase64 = rsa.decryptStr(form.getData(), KeyType.PrivateKey);
        return SingleResponse.of(encryptBase64);
    }
}
