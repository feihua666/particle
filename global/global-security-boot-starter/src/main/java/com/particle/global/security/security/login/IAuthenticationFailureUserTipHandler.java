package com.particle.global.security.security.login;

import com.particle.global.exception.code.IErrorCode;

/**
 * <p>
 * 认证失败时，响应码对应的消息提示
 * </p>
 *
 * @author yangwei
 * @since 2023/10/26 14:43
 */
public interface IAuthenticationFailureUserTipHandler {


    /**
     * 是否支持
     * @param errorCode
     * @return
     */
    boolean support(IErrorCode errorCode);

    /**
     * 返回用户示例消息
     * @param errorCode
     * @return
     */
    String handle(IErrorCode errorCode);
}
