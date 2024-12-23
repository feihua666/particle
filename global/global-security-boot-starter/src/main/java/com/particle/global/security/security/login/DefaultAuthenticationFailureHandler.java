package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.security.exception.BadCaptchaAuthenticationException;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.servlet.RequestTool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * particle 默认的登录失败处理器，写入json响应数据
 * @Author yangwei
 * @since 2020/12/11 13:32
 */
@Slf4j
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private GlobalSecurityAuthenticationHandler globalSecurityAuthenticationHandler;

    @Autowired(required = false)
    private List<IAuthenticationFailureUserTipHandler> iAuthenticationFailureUserTipHandlers;


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        Response responseResult = getResponseByException(e);
        boolean ajaxRequest = RequestTool.isAjaxRequest(httpServletRequest);
        String accept = httpServletRequest.getHeader(HttpHeaders.ACCEPT);
        if (ajaxRequest || StrUtil.contains(accept, MediaType.APPLICATION_JSON_VALUE)) {
            outJson(httpServletResponse,responseResult);
        }
        globalSecurityAuthenticationHandler.tryNotifyIAuthenticationResultServicesOnFailure(httpServletRequest,httpServletResponse,e,responseResult);

        if (!ajaxRequest) {
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }
    }

    protected void outJson(HttpServletResponse httpServletResponse,Response responseResult) throws IOException{
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter out = httpServletResponse.getWriter();
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
        String toJsonStrForHttp = JsonTool.toJsonStrForHttp(responseResult, jackson2HttpMessageConverter.getObjectMapper());

        out.write(toJsonStrForHttp);

        out.flush();
        IoUtil.close(out);
    }

    /**
     * 根据异常返回不同的响应
     * @param e
     * @return
     */
    private Response getResponseByException(AuthenticationException e){
        IErrorCode iErrorCode = errorCodeMap.get(e.getClass());
        if (iErrorCode == null) {
            if (e.getCause() instanceof BizException) {
                iErrorCode = ((BizException) e.getCause()).getError();
            }else {
                iErrorCode = ErrorCodeGlobalEnum.AUTHENTICATION_ERROR;
                log.error("登录异常",e);
            }

        }

        Response response = Response.buildFailure(iErrorCode,handleUserTip(iErrorCode));

        return response;
    }

    /**
     * 处理用户提示信息
     * @param iErrorCode
     * @return
     */
    private String handleUserTip(IErrorCode iErrorCode) {
        if (iAuthenticationFailureUserTipHandlers != null) {
            for (IAuthenticationFailureUserTipHandler iAuthenticationFailureUserTipHandler : iAuthenticationFailureUserTipHandlers) {
                if (iAuthenticationFailureUserTipHandler.support(iErrorCode)) {
                    return iAuthenticationFailureUserTipHandler.handle(iErrorCode);
                }
            }
        }
        return null;
    }

    private static Map<Class,IErrorCode> errorCodeMap = new HashMap();
    static {
        errorCodeMap.put(AccountExpiredException.class, ErrorCodeGlobalEnum.ACCOUNT_EXPIRED_ERROR);
        errorCodeMap.put(CredentialsExpiredException.class, ErrorCodeGlobalEnum.PASSWORD_EXPIRED_ERROR);
        errorCodeMap.put(DisabledException.class, ErrorCodeGlobalEnum.ACCOUNT_DISABLED_ERROR);
        errorCodeMap.put(LockedException.class, ErrorCodeGlobalEnum.ACCOUNT_LOACKED_ERROR);
        errorCodeMap.put(UsernameNotFoundException.class, ErrorCodeGlobalEnum.INVALID_ACCOUNT_ERROR);
        errorCodeMap.put(BadCredentialsException.class, ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR);
        errorCodeMap.put(BadCaptchaAuthenticationException.class, ErrorCodeGlobalEnum.CAPTCHA_ERROR);
    }
}
