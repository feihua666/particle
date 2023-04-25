package com.particle.global.captcha.verify;

import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.store.ICaptchaStoreService;

/**
 * <p>
 * 验证码校验默认实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:37
 */
public class DefaultCaptchaVerifyServiceImpl implements ICaptchaVerifyService{

	private ICaptchaStoreService captchaStoreService;

	public DefaultCaptchaVerifyServiceImpl(ICaptchaStoreService captchaStoreService){
		this.captchaStoreService = captchaStoreService;
	}

	@Override
	public boolean verify(CaptchaVerifyDTO captchaVerifyDTO) {
		CaptchaGenResultDTO resultDTO = captchaStoreService.get(captchaVerifyDTO.getCaptchaUniqueIdentifier());
		return resultDTO.getCaptchaValue().equals(captchaVerifyDTO.getCaptchaValue());
	}
}
