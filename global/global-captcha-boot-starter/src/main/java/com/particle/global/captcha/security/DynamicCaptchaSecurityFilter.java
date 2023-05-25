package com.particle.global.captcha.security;

import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.captcha.endpoint.CaptchaVerifyCommand;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.particle.global.captcha.endpoint.CaptchaController.dynamic_suffix;

/**
 * <p>
 * 动态验证码过滤器，将该过虑器添加到spring security过滤器链中
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 12:55:38
 */
@Slf4j
@Setter
@ConfigurationProperties(prefix = "particle.dynamic-captcha.filter")
public class DynamicCaptchaSecurityFilter extends BaseCaptchaSecurityFilter {
	@Override
	public boolean verifyCaptcha(CaptchaVerifyCommand verifyCommand) {
		String md5Hex = DigestUtil.md5Hex(verifyCommand.getCaptchaUniqueIdentifier() + dynamic_suffix);
		verifyCommand.setCaptchaUniqueIdentifier(md5Hex);
		return super.verifyCaptcha(verifyCommand);
	}
}
