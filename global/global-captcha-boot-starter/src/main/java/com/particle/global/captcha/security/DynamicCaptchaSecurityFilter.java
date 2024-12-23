package com.particle.global.captcha.security;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.captcha.endpoint.CaptchaVerifyCommand;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

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
	/**
	 * key为拦截的uri
	 * value为动态验证码的标识字符，如 mobile=手机号，email=邮箱
	 */
	private Map<String,String> uriIdentifier;

	@SneakyThrows
    @Override
	public CaptchaVerifyDTO mapCaptchaVerifyDTO(CaptchaVerifyCommand verifyCommand, HttpServletRequest httpServletRequest, String uri) {
		String identifier = abtainIdentifier(httpServletRequest, uri);
		// 验证key需要和 {@link com.particle.global.captcha.endpoint.CaptchaController.getDynamicCaptcha} 的 md5Hex 变量一致
		String md5Hex = DigestUtil.md5Hex(verifyCommand.getCaptchaUniqueIdentifier() + dynamic_suffix + identifier);
		verifyCommand.setCaptchaUniqueIdentifier(md5Hex);

		// return super.verifyCaptcha(verifyCommand,request,uri);
		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(verifyCommand.getCaptchaUniqueIdentifier(), verifyCommand.getCaptchaValue());
		// 注意，动态验证码没有在校验后删除，这里仅提供校验，在实际的业务中需要再次校验并删除，
		// 如：用户动态验证码登录，这里也配置校验了，但在实际用户登录时也进行了校验，在实际登录时进行校验删除，这在验证码输入错误时是高效的
		captchaVerifyDTO.setIsRemove(false);
		return captchaVerifyDTO;
	}

	/**
	 * 动态验证码，都需要校验
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	protected boolean check(ServletRequest request, ServletResponse response) {
		return true;
	}

	/**
	 * 获取请求的值
	 * @param request
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	private String abtainIdentifier(HttpServletRequest request, String uri) throws IOException {

		String identifier = "";
		if (uriIdentifier == null || uriIdentifier.isEmpty()) {
			log.debug("uriIdentifier is empty for {}.if your captchaValue is invalid.may be this is the reason",uri);
			return identifier;
		}
		boolean isApplicationJsonContentType = request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE);
		CaptchaVerifyCommand captchaVerifyCommand = null;
		if (isApplicationJsonContentType) {
			String body = IoUtil.readUtf8(request.getInputStream());
			Map map = JSONUtil.toBean(body, Map.class);
		}else {
			String requestIdentifier = request.getParameter(uriIdentifier.get(uri));
			if (requestIdentifier != null) {
				identifier = requestIdentifier;
			}
		}
		return identifier;
	}
}
