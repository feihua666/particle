package com.particle.global.captcha;

import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.gen.ICaptchaGenService;
import com.particle.global.captcha.store.ICaptchaStoreService;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.captcha.verify.ICaptchaVerifyService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 默认的验证码服务实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:32
 */
@Component
public class DefaultCaptchaServiceImpl implements ICaptchaService{


	private ICaptchaGenService captchaGenService;

	private ICaptchaVerifyService captchaVerifyService;

	private ICaptchaStoreService captchaStoreService;

	public DefaultCaptchaServiceImpl(
			ICaptchaGenService captchaGenService,
			ICaptchaVerifyService captchaVerifyService,
			ICaptchaStoreService captchaStoreService
	) {
		this.captchaGenService = captchaGenService;
		this.captchaVerifyService = captchaVerifyService;
		this.captchaStoreService = captchaStoreService;
	}

	@Override
	public CaptchaGenResultDTO gen(CaptchaGenDTO captchaGenDTO) {
		CaptchaGenResultDTO resultDTO =  captchaGenService.gen(captchaGenDTO);
		captchaStoreService.save(resultDTO.cloneForSave());
		return resultDTO;
	}

	@Override
	public boolean verify(CaptchaVerifyDTO captchaVerifyDTO) {
		boolean result = captchaVerifyService.verify(captchaVerifyDTO);
		if(captchaVerifyDTO.getIsRemove() != null && captchaVerifyDTO.getIsRemove()){
			captchaStoreService.remove(captchaVerifyDTO.getCaptchaUniqueIdentifier());
		}
		return result;
	}
}
