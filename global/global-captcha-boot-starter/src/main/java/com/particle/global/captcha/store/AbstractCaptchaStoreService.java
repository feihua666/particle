package com.particle.global.captcha.store;

import cn.hutool.core.util.StrUtil;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码存储抽象基础类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 13:36
 */
public abstract class AbstractCaptchaStoreService implements ICaptchaStoreService{
	@Override
	public boolean save(CaptchaGenResultDTO captchaGenResultDTO) {
		if (captchaGenResultDTO == null) {
			return false;
		}
		return doSave(captchaGenResultDTO);
	}
	public abstract boolean doSave(CaptchaGenResultDTO captchaGenResultDTO);

	@Override
	public CaptchaGenResultDTO get(String captchaUniqueIdentifier) {
		if (StrUtil.isEmpty(captchaUniqueIdentifier)) {
			return null;
		}
		CaptchaGenResultDTO resultDTO = doGet(captchaUniqueIdentifier);

		// 超时时间判断
		if (resultDTO.getExpireAt() != null) {
			if (LocalDateTime.now().isAfter(resultDTO.getExpireAt())) {
				return null;
			}
		}
		return resultDTO;
	}

	public abstract CaptchaGenResultDTO doGet(String captchaUniqueIdentifier);

	@Override
	public boolean remove(String captchaUniqueIdentifier) {
		if (StrUtil.isEmpty(captchaUniqueIdentifier)) {
			return true;
		}
		return doRemove(captchaUniqueIdentifier);
	}

	public abstract boolean doRemove(String captchaUniqueIdentifier);
}
