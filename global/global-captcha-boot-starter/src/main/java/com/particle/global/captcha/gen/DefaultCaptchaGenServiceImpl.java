package com.particle.global.captcha.gen;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 默认的验证码生成服务
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 09:35
 */
public class DefaultCaptchaGenServiceImpl implements ICaptchaGenService{

	private ICaptchaGenerator captchaGenerator;

	@Override
	public CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO) {
		CaptchaGenResultDTO gen = CaptchaGeneratorTool.gen(captchaGenDTO);
		if (gen == null && captchaGenerator != null) {
			return captchaGenerator.gen(captchaGenDTO);
		}
		return gen;
	}

	@Autowired(required = false)
	public void setCaptchaGenerator(ICaptchaGenerator captchaGenerator) {
		this.captchaGenerator = captchaGenerator;
	}
}
