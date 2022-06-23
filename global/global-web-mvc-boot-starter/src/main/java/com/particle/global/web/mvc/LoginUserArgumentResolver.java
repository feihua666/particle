package com.particle.global.web.mvc;

import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * controller中注入当前登录用户参数
 * 只要controller方法中含有 LoginUser 类型的参数即可注入，这里没有自定义注解标识
 * Created by yangwei
 * Created at 2020/4/9 12:57
 */
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isHasLoginUserParameter = parameter.getParameterType().isAssignableFrom(LoginUser.class);

        return isHasLoginUserParameter;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object r = Optional.ofNullable(LoginUserTool.getLoginUser())
                .orElse(LoginUserTool.retrieveFromSession(((HttpServletRequest) webRequest.getNativeRequest())));
        if (r == null) {
            r = Optional.ofNullable(SecurityContextHolder.getContext()).map(context->context.getAuthentication()).map(authentication -> authentication.getPrincipal()).orElse(null);
            if (!(r instanceof LoginUser)) {
                r = null;
            }
        }
        return r;
    }
}
