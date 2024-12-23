package com.particle.global.captcha.security;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
public class CaptchaSecurityFilter extends BaseCaptchaSecurityFilter {

}
