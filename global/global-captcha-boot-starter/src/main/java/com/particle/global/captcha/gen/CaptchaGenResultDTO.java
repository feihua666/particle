package com.particle.global.captcha.gen;

import com.particle.global.captcha.ICaptchaScene;
import com.particle.global.captcha.ICaptchaType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码生成结果
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 09:49
 */
@Data
public class CaptchaGenResultDTO extends CaptchaGenDTO{

	/**
	 * base64的结果
	 */
	private String base64;
	/**
	 * 图片的内容
	 */
	private String base64Content;
	/**
	 * 真实结果
	 */
	private String captchaValue;




	public static CaptchaGenResultDTO create(String captchaUniqueIdentifier,
											 ICaptchaScene captchaScene,
											 ICaptchaType captchaType,
											 Integer width,
											 Integer height,
											 Integer length,
											 LocalDateTime expireAt,
											 String base64,String captchaValue) {
		CaptchaGenResultDTO captchaGenResultDTO = new CaptchaGenResultDTO();
		captchaGenResultDTO.fill(captchaUniqueIdentifier, captchaScene, captchaType,width,height,length, expireAt);
		captchaGenResultDTO.setBase64(base64);
		captchaGenResultDTO.setCaptchaValue(captchaValue);
		return captchaGenResultDTO;
	}

	public static CaptchaGenResultDTO createByGenDTO(CaptchaGenDTO captchaGenDTO,
													 String base64,String captchaValue) {

		return create(captchaGenDTO.getCaptchaUniqueIdentifier(),
				captchaGenDTO.getCaptchaScene(),
				captchaGenDTO.getCaptchaType(),
				captchaGenDTO.getWidth(),
				captchaGenDTO.getHeight(),
				captchaGenDTO.getLength(),
				captchaGenDTO.getExpireAt(),
				base64,captchaValue);
	}


	/**
	 * 在保存的时候不需要保存图片，太大
	 * @return
	 */
	public CaptchaGenResultDTO cloneForSave() {
		return create(getCaptchaUniqueIdentifier(),
				getCaptchaScene(),
				getCaptchaType(),
				getWidth(),
				getHeight(), getLength(), getExpireAt(), null, getCaptchaValue());
	}
}
