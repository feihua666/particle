package com.particle.global.security;


import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.security.security.login.LoginUserTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


/**
 * controller 异常统一处理类
 * 不要在该类的任何地方抛出异常，因为这里就是处理异常的地方，如果这里抛出异常，将会被转发到BasicErrorController.error方法处理
 * 注意：这里处理的返回结果也会进入到GlobalResponseBodyAdvice里处理
 * @author  yangwei
 * Created at 2019/7/25 20:24
 */
@RestControllerAdvice
@Slf4j
// 注意：顺序需要早于全局异常处理
@Order(Ordered.LOWEST_PRECEDENCE + 5)
public class SecurityExceptionAdvice {

    public static Response createRM(IErrorCode errorCode, String userTip, Object data, Exception e) {
        log.warn("警告： errStatus={}, errCode={}, errMessage={}, data={}, exceptionMsg={}, exceptionName={} ",
                Optional.ofNullable(errorCode).map(IErrorCode::getStatus).orElse(null),
                Optional.ofNullable(errorCode).map(IErrorCode::getErrCode).orElse(null),
                Optional.ofNullable(errorCode).map(IErrorCode::getErrMessage).orElse(null),
                data,e.getMessage(), e.getClass().getName());
        if (data != null) {
            SingleResponse singleResponse = SingleResponse.buildFailure(errorCode, Optional.ofNullable(userTip).orElse(e.getMessage()));
            singleResponse.setData(data);
            return singleResponse;
        }

        return Response.buildFailure(errorCode, e.getMessage());
    }

    /**
     *  异常没有权限的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
        if (LoginUserTool.isAnonymous()!=null && LoginUserTool.isAnonymous()) {
            return createRM(ErrorCodeGlobalEnum.NO_PRIVILEGE_ANONYMOUS_ERROR, ErrorCodeGlobalEnum.NO_PRIVILEGE_ANONYMOUS_ERROR.getErrMessage(), request.getRequestURI(), ex);

        }
        return createRM(ErrorCodeGlobalEnum.NO_PRIVILEGE_ERROR, ErrorCodeGlobalEnum.NO_PRIVILEGE_ERROR.getErrMessage(), request.getRequestURI(), ex);
    }


    /**
     *  用户认证异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleAuthenticationException(HttpServletRequest request, AuthenticationException ex) {
        return createRM(ErrorCodeGlobalEnum.AUTHENTICATION_ERROR, ex.getMessage(), request.getRequestURI(), ex);
    }
    /**
     *  用户认证异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleAuthenticationCredentialsNotFoundException(HttpServletRequest request, AuthenticationCredentialsNotFoundException ex) {
        return createRM(ErrorCodeGlobalEnum.UNAUTHORIZED_ERROR, "未找到身份验证凭据", request.getRequestURI(), ex);
    }

    /**
     *  登录帐号未知异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleUsernameNotFoundException(HttpServletRequest request, UsernameNotFoundException ex) {
        return createRM(ErrorCodeGlobalEnum.INVALID_ACCOUNT_ERROR, ErrorCodeGlobalEnum.INVALID_ACCOUNT_ERROR.getErrMessage(), request.getRequestURI(), ex);
    }

    /**
     * 登录密码不正确
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleBadCredentialsException(HttpServletRequest request, BadCredentialsException ex) {
        return createRM(ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR, ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR.getErrMessage(), request.getRequestURI(), ex);
    }

    /**
     *  登录帐号已被锁定
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleLockedAccountException(HttpServletRequest request, LockedException ex) {
        return createRM(ErrorCodeGlobalEnum.ACCOUNT_LOACKED_ERROR, ErrorCodeGlobalEnum.ACCOUNT_LOACKED_ERROR.getErrMessage(), request.getRequestURI(), ex);
    }

}
