package com.particle.global.captcha.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.captcha.ICaptchaService;
import com.particle.global.captcha.endpoint.CaptchaVerifyCommand;
import com.particle.global.captcha.verify.CaptchaVerifyDTO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.ApplicationContextForSecurityHelper;
import com.particle.global.tool.json.JsonTool;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>
 * 验证码过滤器，将该过虑器添加到spring security过滤器链中
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 15:18:51
 */
@Slf4j
@Setter
@ConfigurationProperties(prefix = "particle.captcha.filter")
public class CaptchaSecurityFilter extends GenericFilterBean {

	private Boolean enable = false;
	/**
	 * 需要添加验证码的地址
	 */
	private List<String> uris;

	private ICaptchaService captchaService;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (enable && CollectionUtil.isNotEmpty(uris)) {
			HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
			String requestURI = httpServletRequest.getRequestURI();
			String contextPath = request.getServletContext().getContextPath();
			if (StrUtil.isNotEmpty(contextPath)) {
				requestURI = requestURI.substring(contextPath.length());
			}
			if (uris.contains(requestURI)) {
				boolean isApplicationJsonContentType = request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE);
				CaptchaVerifyCommand captchaVerifyCommand = null;
				if (isApplicationJsonContentType) {
					String body = IoUtil.readUtf8(httpServletRequest.getInputStream());
					if (StrUtil.isEmpty(body)) {
						log.error("uri = {} is need verify captcha but body is empty",requestURI);
						writeError(((HttpServletResponse) response));
						return;
					}
					captchaVerifyCommand = JSONUtil.toBean(body, CaptchaVerifyCommand.class);

				}else {
					String captchaUniqueIdentifier = httpServletRequest.getParameter(CaptchaVerifyCommand.captchaUniqueIdentifierFieldName);
					String captchaValue = httpServletRequest.getParameter(CaptchaVerifyCommand.captchaValueFieldName);
					captchaVerifyCommand = new CaptchaVerifyCommand();
					captchaVerifyCommand.setCaptchaUniqueIdentifier(captchaUniqueIdentifier);
					captchaVerifyCommand.setCaptchaValue(captchaValue);
				}
				boolean isCaptchaVerifyCommandNotFullySet = captchaVerifyCommand == null || StrUtil.isEmpty(captchaVerifyCommand.getCaptchaUniqueIdentifier()) || StrUtil.isEmpty(captchaVerifyCommand.getCaptchaValue());
				if (isCaptchaVerifyCommandNotFullySet) {
					log.error("uri = {} is need verify captcha but captchaVerifyCommand={} is not set fully",requestURI,JsonTool.toJsonStr(captchaVerifyCommand));
					writeError(((HttpServletResponse) response));
					return;
				}

				boolean verifyCaptcha = verifyCaptcha(captchaVerifyCommand);
				if (!verifyCaptcha) {
					writeError(((HttpServletResponse) response));
					return;
				}

			}
		}


		chain.doFilter(request,response);
	}

	/**
	 * 校验验证码
	 * @param verifyCommand
	 * @return
	 */
	public boolean verifyCaptcha(@Valid CaptchaVerifyCommand verifyCommand){

		CaptchaVerifyDTO captchaVerifyDTO = CaptchaVerifyDTO.create(verifyCommand.getCaptchaUniqueIdentifier(), verifyCommand.getCaptchaValue());
		captchaVerifyDTO.setIsRemove(true);

		boolean verify = captchaService.verify(captchaVerifyDTO);
		return verify;
	}

	/**
	 *
	 * @param response
	 * @throws IOException
	 */
	private void writeError(HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.BAD_REQUEST.value());

		PrintWriter out = response.getWriter();
		SingleResponse singleResponse = SingleResponse.buildFailure(ErrorCodeGlobalEnum.CAPTCHA_ERROR);
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
		String toJsonStrForHttp = JsonTool.toJsonStrForHttp(singleResponse, jackson2HttpMessageConverter.getObjectMapper());
		out.write(toJsonStrForHttp);
		out.flush();
		IoUtil.close(out);
	}
}
