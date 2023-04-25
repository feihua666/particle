package com.particle.global.captcha.store;

import com.particle.global.captcha.gen.CaptchaGenResultDTO;

/**
 * <p>
 * 验证码存储接口类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:40
 */
public interface ICaptchaStoreService {

	/**
	 * 存储验证码
	 * @param captchaGenResultDTO
	 * @return
	 */
	boolean save(CaptchaGenResultDTO captchaGenResultDTO);

	/**
	 * 获取存储的验证码
	 * @param captchaUniqueIdentifier
	 * @return
	 */
	CaptchaGenResultDTO get(String captchaUniqueIdentifier);

	/**
	 * 删除验证码
	 * @param captchaUniqueIdentifier
	 * @return
	 */
	boolean remove(String captchaUniqueIdentifier);
}
