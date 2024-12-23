package com.particle.global.captcha.endpoint;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.captcha.*;
import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:17
 */
@Tag(name = "全局服务验证码相关接口")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

	/**
	 * 动态验证码添加一个后缀
	 * 否则动态验证码会和正常的验证码混淆，导致安全问题
	 * 也就是说发送了动态验证码，避免通过正常的验证码获取校验时校验通过
	 */
	public static final String dynamic_suffix = "dynamic";

	@Autowired
	private ICaptchaService captchaService;
	@Autowired(required = false)
	private IDynamicCaptchaNotify dynamicCaptchaNotify;
	@Autowired
	private DynamicCaptchaNotifyProperties dynamicCaptchaNotifyProperties;


	@Operation(summary = "获取验证码")
	@GetMapping("/getCaptcha")
	public SingleResponse<CaptchaVO> getCaptcha(@Valid CaptchaGenCommand genCommand){

		CaptchaGenResultDTO gen = gen(genCommand,null,false);

		CaptchaVO captchaVO = CaptchaVO.create(gen.getCaptchaUniqueIdentifier(), gen.getBase64());
		return SingleResponse.of(captchaVO);
	}

	/**
	 * 请注意动态验证码由于唯一标识有 根据 identifier 组件 md5 的规则，因此前端不能直接校验成功
	 * @param verifyCommand
	 * @return
	 */
	@Operation(summary = "校验验证码")
	@PostMapping("/verifyCaptcha")
	public SingleResponse<Boolean> verifyCaptcha(@Valid @RequestBody CaptchaVerifyCommand verifyCommand){

		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(verifyCommand.getCaptchaUniqueIdentifier(), verifyCommand.getCaptchaValue());
		captchaVerifyDTO.setIsRemove(false);

		boolean verify = captchaService.verify(captchaVerifyDTO);
		if (verify) {
			return SingleResponse.of(verify);
		}

		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.CAPTCHA_ERROR);
	}

	/**
	 * 动态获取验证码，支持手机号或邮箱
	 * @param genCommand
	 * @return
	 */
	@Operation(summary = "获取动态验证码")
	@GetMapping("/getDynamicCaptcha")
	public SingleResponse<CaptchaVO> getDynamicCaptcha(@Valid DynamicCaptchaGenCommand genCommand){

		String identifier = genCommand.getIdentifier();

		boolean isEmail = Validator.isEmail(identifier);
		boolean isMobile = Validator.isMobile(identifier);
		Assert.isTrue(isEmail || isMobile,ErrorCodeGlobalEnum.MOBILE_EMAIL_REQUEST_ERROR);

		if (StrUtil.isEmpty(genCommand.getCaptchaType())) {
			genCommand.setCaptchaType(CaptchaTypeEnum.customImage.type());
		}
		String fastSimpleUUID = IdUtil.fastSimpleUUID();
		String md5Hex = DigestUtil.md5Hex(fastSimpleUUID + dynamic_suffix + identifier);
		CaptchaGenResultDTO gen = gen(genCommand,md5Hex,true);

		if (dynamicCaptchaNotify != null) {
			String identifierType = null;
			if (isEmail) {
				identifierType = IDynamicCaptchaNotify.IdentifierType.email.name();
			} else if (isMobile) {
				identifierType = IDynamicCaptchaNotify.IdentifierType.mobile.name();
			}else {
				throw new RuntimeException("this like a bug identifierType is not recognized");
			}
			String notifyIdentifier = dynamicCaptchaNotifyProperties.getNotifyIdentifier(genCommand.getCaptchaScene(), genCommand.getNotifyIdentifier());
			IDynamicCaptchaNotify.NotifyDTO notifyDTO = IDynamicCaptchaNotify.NotifyDTO.create(
					identifier,
					identifierType,
					gen.getBase64Content(),
					notifyIdentifier);
			dynamicCaptchaNotify.doNotify(notifyDTO);
		}

		CaptchaVO captchaVO = CaptchaVO.create(fastSimpleUUID, null);
		return SingleResponse.of(captchaVO);
	}

	/**
	 * 生成验证码
	 * @param genCommand
	 * @return
	 */
	private CaptchaGenResultDTO gen(CaptchaGenCommand genCommand,String captchaUniqueIdentifier,Boolean isDynamic) {

		ICaptchaType captchaType = CaptchaTypeEnum.arithmetic;
		if (StrUtil.isNotEmpty(genCommand.getCaptchaType())) {
			CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.getByName(genCommand.getCaptchaType());
			if (captchaTypeEnum != null) {
				captchaType = captchaTypeEnum;
			}else {
				captchaType = CustomCaptchaType.create(genCommand.getCaptchaType());
			}
		}
		int defaultLength = CaptchaTypeEnum.defaultLength(captchaType.type());

		if (genCommand.getLength() == null) {
			genCommand.setLength(defaultLength);
		}else {
			Assert.isTrue(defaultLength <= genCommand.getLength(),"验证码长度不能小于" + defaultLength);
		}


		CaptchaGenDTO captchaGenDTO = CaptchaGenDTO.create(
				captchaUniqueIdentifier == null ? IdUtil.fastSimpleUUID() : captchaUniqueIdentifier,
				CustomCaptchaScene.create(genCommand.getCaptchaScene()),
				captchaType,
				genCommand.getWidth(),
				genCommand.getHeight(),
				genCommand.getLength(),
				genCommand.getCharType(),
				// 默认超时5分钟
				LocalDateTime.now().plusMinutes(5),isDynamic);
		CaptchaGenResultDTO gen = captchaService.gen(captchaGenDTO);
		return gen;
	}
}
