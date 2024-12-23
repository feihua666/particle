package com.particle.global.captcha.security;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.security.security.config.WebSecurityConfig;
import com.particle.global.tool.servlet.RequestTool;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 实现一个默认的在登录时，可配置不需要验证码的校验器
 * </p>
 *
 * @author yangwei
 * @since 2023/12/15 13:30
 */
@Data
@ConfigurationProperties(prefix = "particle.captcha.filter.check")
public class DefaultCaptchaSecurityChecker implements ICaptchaSecurityCheck{

    private List<String> noCaptchaLoginUsernames;

    @Override
    public boolean check(ServletRequest request, ServletResponse response) {

        Boolean noCaptchaLoginCheck = noCaptchaLoginCheck(request);
        if (noCaptchaLoginCheck != null) {
            return noCaptchaLoginCheck;
        }


        return true;
    }

    /**
     * 校验哪些用户在登录时不使用验证码
     * @param request
     * @return null = 不明确
     */
    protected Boolean noCaptchaLoginCheck(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = RequestTool.resolveRequestURIStr(httpServletRequest,false);

        if(!Objects.equals(WebSecurityConfig.login_processing_url,requestURI)){
            return null;
        }

        if (CollectionUtil.isEmpty(noCaptchaLoginUsernames)) {
            return null;
        }

        String parameter = httpServletRequest.getParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY);

        // 如果包括就忽略验证码校验
        return !noCaptchaLoginUsernames.contains(parameter);

    }
}
