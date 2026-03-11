package com.particle.global.light.share.login;

/**
 * <p>
 * 登录相关常量
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 09:15
 */
public class LoginConstants {
    /**
     * 自定义请求头 token
     * 主要用于登录后返回响应头中，方便前端获取
     * 主要用于登录后请求头携带，获取用户信息
     */
    public static final String header_c_token = "X-Token-Id";
    /**
     * 自定义请求头 host
     * 主要用于根据域名获取租户id
     */
    public static final String header_c_host = "X-Host";

    /**
     * 登录url
     */
    public static final String login_url = "/login";
    /**
     * 动态验证码登录url
     */
    public static final String login_captcha_url = "/loginCaptcha";

    /**
     * 登出url
     */
    public static final String logout_url = "/logout";
    /**
     * 登录页面url
     */
    public static final String login_page_url = "/loginpage";
}
