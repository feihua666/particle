package com.particle.global.captcha.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>
 * 定义一个接口，用来提供扩展，是否需要验证码校验逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023/12/15 13:16
 */
public interface ICaptchaSecurityCheck {
    /**
     * 是否需要验证验证码
     * @param request
     * @param response
     * @return false=不需要验证验证码
     */
    boolean check(ServletRequest request, ServletResponse response);
}
