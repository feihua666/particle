package com.particle.global.wxjava.advice;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 统一异常处理
 * Created by yangwei
 * Created at 2021/11/22 11:42
 */
@RestControllerAdvice
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE + 10)
public class GlobalWxJavaRestExceptionHandlerAdvice {

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
     * 微信请求出现的异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(WxErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleWxErrorException(HttpServletRequest request, WxErrorException ex) {
        log.error("微信请求异常：{}",ex.getMessage(),ex);
        NotifyParam notifyParam = NotifyParam.system();
        notifyParam.setContentType("global.restcontrolleradvice.error.wx.exception");
        notifyParam.setTitle("微信请求异常");
        notifyParam.setContent(ExceptionUtil.stacktraceToString(ex));
        NotifyTool.notify(notifyParam);
        return createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "微信请求异常", null, ex);
    }
}
