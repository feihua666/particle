package com.particle.global.captcha.store;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;

/**
 * <p>
 * memory 存储实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:48
 */
public class MemoryStoreServiceImpl extends AbstractCaptchaStoreService{

	private static final TimedCache<String, CaptchaGenResultDTO> timedCache = CacheUtil.newTimedCache(2 * 60 * 1000);

	@Override
	public boolean doSave(CaptchaGenResultDTO captchaGenResultDTO) {
		timedCache.put(captchaGenResultDTO.getCaptchaUniqueIdentifier(),captchaGenResultDTO);
		return true;
	}

	@Override
	public CaptchaGenResultDTO doGet(String captchaUniqueIdentifier) {
		return timedCache.get(captchaUniqueIdentifier);
	}

	@Override
	public boolean doRemove(String captchaUniqueIdentifier) {
		timedCache.remove(captchaUniqueIdentifier);
		return true;
	}
}
