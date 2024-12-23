package com.particle.global.captcha.store;

import com.particle.global.captcha.gen.CaptchaGenResultDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * http session 存储实现
 * 如果在分布式情况下，可以结合spring session，所以一般使用 http session是没有问题的
 * 使用 http session存储在用户未登录时不可用，因为用户登录之前还没有 session
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 11:48
 */
public class HttpSessionStoreServiceImpl extends AbstractCaptchaStoreService{

	private HttpServletRequest request;

	@Override
	public boolean doSave(CaptchaGenResultDTO captchaGenResultDTO) {

		request.getSession().setAttribute(captchaGenResultDTO.getCaptchaUniqueIdentifier(),captchaGenResultDTO);
		return true;
	}

	@Override
	public CaptchaGenResultDTO doGet(String captchaUniqueIdentifier) {
		return (CaptchaGenResultDTO) request.getSession().getAttribute(captchaUniqueIdentifier);
	}

	@Override
	public boolean doRemove(String captchaUniqueIdentifier) {
		request.getSession().removeAttribute(captchaUniqueIdentifier);
		return true;
	}

	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
