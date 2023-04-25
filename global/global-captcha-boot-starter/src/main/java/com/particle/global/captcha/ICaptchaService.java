package com.particle.global.captcha;

import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;

/**
 * <p>
 * 验证码服务接口类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:32
 */
public interface ICaptchaService {


	/**
	 * 生成验证码
	 * @param captchaGenDTO
	 * @return
	 */
	CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO);

	/**
	 * 校验
	 * @param captchaVerifyDTO
	 * @return
	 */
	public boolean verify(CaptchaVerifyDTO captchaVerifyDTO);
}
