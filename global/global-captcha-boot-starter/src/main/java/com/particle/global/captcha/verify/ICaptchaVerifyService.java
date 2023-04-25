package com.particle.global.captcha.verify;

/**
 * <p>
 * 验证码验证服务接口类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:36
 */
public interface ICaptchaVerifyService {

	/**
	 * 校验
	 * @param captchaVerifyDTO
	 * @return
	 */
	public boolean verify(CaptchaVerifyDTO captchaVerifyDTO);
}
