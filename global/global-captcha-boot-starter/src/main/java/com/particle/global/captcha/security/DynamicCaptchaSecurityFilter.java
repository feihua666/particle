package com.particle.global.captcha.security;

import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.captcha.endpoint.CaptchaVerifyCommand;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
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
	@Override
	public boolean verifyCaptcha(CaptchaVerifyCommand verifyCommand, HttpServletRequest request,String uri) throws IOException {

		String identifier = abtainIdentifier(request, uri);
		// 验证key需要和 {@link com.particle.global.captcha.endpoint.CaptchaController.getDynamicCaptcha} 的 md5Hex 变量一致
		String md5Hex = DigestUtil.md5Hex(verifyCommand.getCaptchaUniqueIdentifier() + dynamic_suffix + identifier);
		verifyCommand.setCaptchaUniqueIdentifier(md5Hex);

		return super.verifyCaptcha(verifyCommand,request,uri);
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
