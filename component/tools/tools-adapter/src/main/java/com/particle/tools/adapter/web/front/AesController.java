package com.particle.tools.adapter.web.front;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tools.client.dto.command.AesDecryptCommand;
import com.particle.tools.client.dto.command.AesEncryptCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

/**
 * Created by yangwei
 * Created at 2021/2/8 15:35
 */
@Tag(name = "aes相关接口")
@RestController
@RequestMapping("/front/web/aes")
public class AesController extends AbstractBaseWebAdapter {

    @Operation(summary = "生成随机base64编码密钥")
    @GetMapping("/randomKey/base64")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> randomKey() {
        SecretKey secretKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue());
        byte[] encoded = secretKey.getEncoded();
        String randomKey = Base64.encode(encoded);
        return SingleResponse.of(randomKey);
    }
    @Operation(summary = "加密")
    @PostMapping("/encrypt/base64")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> encrypt( @RequestBody @Validated AesEncryptCommand form) {
        AES aes = SecureUtil.aes(Base64.decode(form.getKey()));
        String encryptBase64 = aes.encryptBase64(form.getData());
        return SingleResponse.of(encryptBase64);
    }
    @Operation(summary = "解密")
    @PostMapping("/decrypt")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> decrypt( @RequestBody @Validated AesDecryptCommand form) {
        AES aes = SecureUtil.aes(Base64.decode(form.getKey()));
        String data = aes.decryptStr(form.getData());
        return SingleResponse.of(data);
    }
}
