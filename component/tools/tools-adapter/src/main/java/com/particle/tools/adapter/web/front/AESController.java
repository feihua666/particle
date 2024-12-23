package com.particle.tools.adapter.web.front;

import com.baomidou.mybatisplus.core.toolkit.AES;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tools.client.dto.command.AesEncryptCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangwei
 * Created at 2021/2/8 15:35
 */
@Tag(name = "aes相关接口")
@RestController
@RequestMapping("/front/web/aes")
public class AESController extends AbstractBaseWebAdapter {

    @Operation(summary = "生成 16 位随机 AES 密钥")
    @GetMapping("/randomKey")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> randomKey() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        return SingleResponse.of(randomKey);
    }
    @Operation(summary = "随机密钥加密")
    @PostMapping("/encrypt")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<String> encrypt( @RequestBody @Validated AesEncryptCommand form) {
        // 随机密钥加密
        String result = AES.encrypt(form.getData(), form.getKey());
        return SingleResponse.of(result);
    }
}
