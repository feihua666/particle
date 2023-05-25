package com.particle.user.adapter.login.captcha;

import cn.hutool.crypto.digest.DigestUtil;
import com.particle.global.captcha.ICaptchaService;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.security.exception.BadCaptchaAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import static com.particle.global.captcha.endpoint.CaptchaController.dynamic_suffix;

/**
 * <p>
 * 验证码登录使用
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 17:07
 */
public class CaptchaDaoAuthenticationProvider extends DaoAuthenticationProvider {

	private ICaptchaService captchaService;

	@Override
	public boolean supports(Class<?> authentication) {
		return (CaptchaUsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String identifier = username;
		String presentedPassword = authentication.getCredentials().toString();
		// 密码即是验证码
		String captchaValue = presentedPassword;

		String captchaUniqueIdentifier = ((CaptchaUsernamePasswordAuthenticationToken) authentication).getCaptchaUniqueIdentifier();

		// 验证key需要和 {@link com.particle.global.captcha.endpoint.CaptchaController.getDynamicCaptcha} 的 md5Hex 变量一致
		String md5Hex = DigestUtil.md5Hex(captchaUniqueIdentifier + dynamic_suffix + identifier);
		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(md5Hex, captchaValue);
		boolean verify = captchaService.verify(captchaVerifyDTO);
		if (!verify) {
			throw new BadCaptchaAuthenticationException("invalid captcha");
		}
	}

	@Autowired
	public void setCaptchaService(ICaptchaService captchaService) {
		this.captchaService = captchaService;
	}
}
