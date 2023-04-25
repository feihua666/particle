package com.particle.global.captcha.store;

import com.particle.global.captcha.gen.CaptchaGenResultDTO;

/**
 * <p>
 * jdbc 存储实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:48
 */
public class JdbcStoreServiceImpl extends AbstractCaptchaStoreService{

	@Override
	public boolean doSave(CaptchaGenResultDTO captchaGenResultDTO) {
		return false;
	}

	@Override
	public CaptchaGenResultDTO doGet(String captchaUniqueIdentifier) {
		return null;
	}

	@Override
	public boolean doRemove(String captchaUniqueIdentifier) {
		return false;
	}
}
