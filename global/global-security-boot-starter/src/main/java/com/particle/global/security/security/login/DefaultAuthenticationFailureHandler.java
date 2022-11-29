package com.particle.global.security.security.login;

import cn.hutool.core.io.IoUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.tool.json.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * particle 默认的登录失败处理器，写入json响应数据
 * @Author yangwei
 * @since 2020/12/11 13:32
 */
@Slf4j
public class DefaultAuthenticationFailureHandler extends DefaultAbstractAuthenticationHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter out = httpServletResponse.getWriter();

        Response response = getResponseByException(e);
        out.write(JsonTool.toJsonStr(response));

        out.flush();
        IoUtil.close(out);
        super.tryNotifyIAuthenticationResultServicesOnFailure(httpServletRequest,httpServletResponse,e,response);
    }

    /**
     * 根据异常返回不同的响应
     * @param e
     * @return
     */
    private Response getResponseByException(AuthenticationException e){
        IErrorCode iErrorCode = errorCodeMap.get(e.getClass());
        if (iErrorCode == null) {
            iErrorCode = ErrorCodeGlobalEnum.AUTHENTICATION_ERROR;
            log.error("登录异常",e);
        }

        Response response = Response.buildFailure(iErrorCode);

        return response;
    }

    private static Map<Class,IErrorCode> errorCodeMap = new HashMap();
    static {
        errorCodeMap.put(AccountExpiredException.class, ErrorCodeGlobalEnum.ACCOUNT_EXPIRED_ERROR);
        errorCodeMap.put(CredentialsExpiredException.class, ErrorCodeGlobalEnum.PASSWORD_EXPIRED_ERROR);
        errorCodeMap.put(DisabledException.class, ErrorCodeGlobalEnum.ACCOUNT_DISABLED_ERROR);
        errorCodeMap.put(LockedException.class, ErrorCodeGlobalEnum.ACCOUNT_LOACKED_ERROR);
        errorCodeMap.put(UsernameNotFoundException.class, ErrorCodeGlobalEnum.INVALID_ACCOUNT_ERROR);
        errorCodeMap.put(BadCredentialsException.class, ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR);
    }
}
