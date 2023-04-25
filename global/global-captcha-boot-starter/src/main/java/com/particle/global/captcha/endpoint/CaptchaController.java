package com.particle.global.captcha.endpoint;

import cn.hutool.core.util.IdUtil;
import com.particle.global.captcha.CaptchaSceneEnum;
import com.particle.global.captcha.CaptchaTypeEnum;
import com.particle.global.captcha.CustomCaptchaScene;
import com.particle.global.captcha.ICaptchaService;
import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 验证码服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:17
 */
@Api(tags = "全局服务验证码相关接口")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private ICaptchaService captchaService;

	@ApiOperation("获取验证码")
	@GetMapping("/getCaptcha")
	public SingleResponse<CaptchaVO> getCaptcha(@Valid CaptchaGenCommand genCommand){

		CaptchaGenDTO captchaGenDTO = CaptchaGenDTO.create(
				IdUtil.fastSimpleUUID(),
				CustomCaptchaScene.create(genCommand.getCaptchaScene()), CaptchaTypeEnum.arithmetic, 130, 48, 2, null);
		CaptchaGenResultDTO gen = captchaService.gen(captchaGenDTO);

		CaptchaVO captchaVO = CaptchaVO.create(gen.getCaptchaUniqueIdentifier(), gen.getBase64());
		return SingleResponse.of(captchaVO);
	}

	@ApiOperation("校验验证码")
	@PostMapping("/verifyCaptcha")
	public SingleResponse<Boolean> verifyCaptcha(@Valid @RequestBody CaptchaVerifyCommand verifyCommand){

		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(verifyCommand.getCaptchaUniqueIdentifier(), verifyCommand.getCaptchaValue());
		captchaVerifyDTO.setIsRemove(false);

		boolean verify = captchaService.verify(captchaVerifyDTO);
		if (verify) {
			return SingleResponse.of(verify);
		}

		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.CAPTCHA_ERROR);
	}

}
