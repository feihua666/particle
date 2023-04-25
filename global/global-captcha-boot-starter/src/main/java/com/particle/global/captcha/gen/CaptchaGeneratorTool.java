package com.particle.global.captcha.gen;

import com.particle.global.captcha.CaptchaTypeEnum;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 验证码生成器
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 10:25
 */
public class CaptchaGeneratorTool {

	private static Map<CaptchaTypeEnum, Function<CaptchaGenDTO,CaptchaGenResultDTO>> configMap;

	static {
		configMap = new HashMap<>();
		configMap.put(CaptchaTypeEnum.customImage,(captchaGenDTO)->{
			SpecCaptcha captcha = new SpecCaptcha(captchaGenDTO.getWidth(), captchaGenDTO.getHeight(),captchaGenDTO.getLength());
			captcha.setCharType(captchaGenDTO.getCharType());
			return toResult(captchaGenDTO,captcha);
		});
		configMap.put(CaptchaTypeEnum.gif,(captchaGenDTO)->{
			GifCaptcha captcha = new GifCaptcha(captchaGenDTO.getWidth(), captchaGenDTO.getHeight(),captchaGenDTO.getLength());

			return toResult(captchaGenDTO,captcha);
		});
		configMap.put(CaptchaTypeEnum.chinese,(captchaGenDTO)->{
			ChineseCaptcha captcha = new ChineseCaptcha(captchaGenDTO.getWidth(), captchaGenDTO.getHeight(),captchaGenDTO.getLength());

			return toResult(captchaGenDTO,captcha);
		});
		configMap.put(CaptchaTypeEnum.chineseGif,(captchaGenDTO)->{
			ChineseGifCaptcha captcha = new ChineseGifCaptcha(captchaGenDTO.getWidth(), captchaGenDTO.getHeight(),captchaGenDTO.getLength());

			return toResult(captchaGenDTO,captcha);
		});

		configMap.put(CaptchaTypeEnum.arithmetic,(captchaGenDTO)->{
			ArithmeticCaptcha captcha = new ArithmeticCaptcha(captchaGenDTO.getWidth(), captchaGenDTO.getHeight(),captchaGenDTO.getLength());

			return toResult(captchaGenDTO,captcha);
		});
	}

	private static CaptchaGenResultDTO toResult(CaptchaGenDTO captchaGenDTO, Captcha captcha) {
		CaptchaGenResultDTO byGenDTO = CaptchaGenResultDTO.createByGenDTO(
				captchaGenDTO,
				captcha.toBase64(),
				captcha.text()
		);
		byGenDTO.setBase64Content(captcha instanceof ArithmeticCaptcha ? ((ArithmeticCaptcha) captcha).getArithmeticString() : captcha.text());
		return byGenDTO;
	}


	/**
	 * 生成内置的验证码
	 * @param captchaGenDTO
	 * @return
	 */
	public static CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO) {
		String type = captchaGenDTO.getCaptchaType().type();
		CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.getByName(type);

		Function<CaptchaGenDTO, CaptchaGenResultDTO> captchaGenDTOCaptchaGenResultDTOFunction = configMap.get(captchaTypeEnum);
		if (captchaGenDTOCaptchaGenResultDTOFunction == null) {
			return null;
		}
		return captchaGenDTOCaptchaGenResultDTOFunction.apply(captchaGenDTO);
	}

}
