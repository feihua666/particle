package com.particle.global.captcha.test;

import cn.hutool.core.util.IdUtil;
import com.particle.global.captcha.*;
import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.gen.DefaultCaptchaGenServiceImpl;
import com.particle.global.captcha.gen.ICaptchaGenService;
import com.particle.global.captcha.store.ICaptchaStoreService;
import com.particle.global.captcha.store.MemoryStoreServiceImpl;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.captcha.verify.DefaultCaptchaVerifyServiceImpl;
import com.particle.global.captcha.verify.ICaptchaVerifyService;
import com.particle.global.tool.json.JsonTool;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 12:17
 */
public class GlobalCaptchaTest {

	public static void main(String[] args) {
		ICaptchaStoreService memoryStoreService = new MemoryStoreServiceImpl();

		ICaptchaGenService captchaGenService = new DefaultCaptchaGenServiceImpl();
		ICaptchaVerifyService captchaVerifyService = new DefaultCaptchaVerifyServiceImpl(memoryStoreService);

		ICaptchaService iCaptchaService = new DefaultCaptchaServiceImpl(
				captchaGenService, captchaVerifyService, memoryStoreService);


		CaptchaGenDTO captchaGenDTO = CaptchaGenDTO.create(IdUtil.fastSimpleUUID(), CaptchaSceneEnum.api, CaptchaTypeEnum.arithmetic, 300, 60, 4, null);
		CaptchaGenResultDTO gen = iCaptchaService.gen(captchaGenDTO);
		System.out.println(gen.getBase64());
		gen.setBase64(null);
		System.out.println(JsonTool.toJsonStr(gen));


		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(captchaGenDTO.getCaptchaUniqueIdentifier(), "3");

		boolean verify = iCaptchaService.verify(captchaVerifyDTO);

		System.out.println("verify result:" + verify);

	}
}
