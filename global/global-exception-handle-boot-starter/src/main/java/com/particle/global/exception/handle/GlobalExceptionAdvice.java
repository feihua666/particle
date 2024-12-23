package com.particle.global.exception.handle;


import cn.hutool.core.exceptions.ExceptionUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.biz.AssertException;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.biz.InvalidDataVersionException;
import com.particle.global.exception.biz.NoDataPrivilegeException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.exception.system.SystemException;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;


/**
 * controller 异常统一处理类
 * 不要在该类的任何地方抛出异常，因为这里就是处理异常的地方，如果这里抛出异常，将会被转发到BasicErrorController.error方法处理
 * 注意这里处理的返回结果也会进入到GlobalResponseBodyAdvice里处理
 * @author yangwei
 * @since 2019/7/25 20:24
 */
@RestControllerAdvice
@Slf4j
@Order
public class GlobalExceptionAdvice {

    @Autowired(required = false)
    private List<GlobalMvcExceptionListener> globalMvcExceptionListeners;

    public static Response createRM(IErrorCode errorCode, String userTip, Object data, Exception e) {
        log.warn("警告： errStatus={}, errCode={}, errMessage={}, data={}, exceptionMsg={}, exceptionName={} ",
                Optional.ofNullable(errorCode).map(IErrorCode::getStatus).orElse(null),
                Optional.ofNullable(errorCode).map(IErrorCode::getErrCode).orElse(null),
                Optional.ofNullable(errorCode).map(IErrorCode::getErrMessage).orElse(null),
                data,e.getMessage(), e.getClass().getName());
        if (data != null) {
            SingleResponse singleResponse = SingleResponse.buildFailure(errorCode, userTip);
            singleResponse.setData(data);
            return singleResponse;
        }

        return Response.buildFailure(errorCode, userTip);
    }

    /**
     * 断言 异常
     * 使用断言打印异常日志，方便由于参数不正确查找问题
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AssertException.class)
    public ResponseEntity<Response> handleAssertException(HttpServletRequest request, AssertException ex) {
        log.error("断言异常：{}",ex.getMessage(),ex);
        NotifyParam notifyParam = NotifyParam.system();
        notifyParam.setContentType("global.restcontrolleradvice.error.assert.exception");
        notifyParam.setTitle("断言异常");
        notifyParam.setContent(ExceptionUtil.stacktraceToString(ex));
        NotifyTool.notify(notifyParam);
        Response rm = createRM(ex.getError(), ex.getMessage(), ex.getData(), ex);
        return ResponseEntity.status(Optional.ofNullable(ex.getError().getHttpStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .body(rm);
    }
    /**
     * 业务 异常
     * 该异常没有打印异常日志，应该是可预知的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<Response> handleBizException(HttpServletRequest request, BizException ex) {
        Response rm = createRM(ex.getError(), ex.getMessage(), ex.getData(), ex);
        return ResponseEntity.status(Optional.ofNullable(ex.getError().getHttpStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .body(rm);
    }
    /**
     * 业务系统 异常
     * 该异常没有打印异常日志，应该是可预知的异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleBusinessException(HttpServletRequest request, SystemException ex) {
        return createRM(ex.getError(), ex.getMessage(), ex.getData(), ex);
    }
    /**
     * 无数据权限异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(NoDataPrivilegeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBusinessDataNoPrivilegeException(HttpServletRequest request, NoDataPrivilegeException ex) {
        return createRM(ex.getError(), ex.getMessage(), ex.getData(), ex);
    }
    /**
     * 数据版本不正确异常，一般为数据被他人修改异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidDataVersionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleInvalidDataVersionException(HttpServletRequest request, InvalidDataVersionException ex) {
        return createRM(ex.getError(), ex.getMessage(), ex.getData(), ex);
    }

    /**
     * 不支持的媒体类型
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Response handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
        return createRM(ErrorCodeGlobalEnum.MEDIA_TYPE_NOT_SUPPORTED_ERROR, ex.getMessage(), null, ex);
    }

    /**
     * 未提供参数异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        String msg = ex.getParameterName() + "不能为空";

        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, msg, null, ex);
    }

    /**
     * 表单验证不对过异常响应
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        // 在脚本验证是没有指定reportOn会有全局异常
        String msg = "";
        String name = "";
        FieldError fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null) {
            msg = fieldError.getDefaultMessage();
            name = fieldError.getField();
        }else {
            msg = ex.getBindingResult().getGlobalError().getDefaultMessage();
        }
        ObjectError next = ex.getBindingResult().getAllErrors().iterator().next();

        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, msg, name, ex);
    }
    /**
     * 表单验证不通过异常响应
     * 发现非requestBody 的get方法表单中有验证时会抛出这个异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleBindException(HttpServletRequest request, BindException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, msg, null, ex);
    }
    /**
     * 表单验证不对过异常响应
     * 发现非requestBody 的get方法表单中有验证时会抛出这个异常以及使用spring 断言
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, ex.getMessage(), null, ex);
    }

    /**
     * 不支持的请求方法
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response handleHttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        return createRM(ErrorCodeGlobalEnum.METHOD_NOT_SUPPORTED_ERROR, "不支持的请求方法" + request.getMethod(), request.getRequestURI(), ex);
    }

    /**
     * sql异常，也可能是数据不对导致的sql执行问题
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleDataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException ex) {
        return createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "数据库请求错误" + ex.getCause().getMessage(), request.getRequestURI(), ex);
    }
    /**
     * sql异常，也可能是数据不对导致的sql执行问题
     * 表不存在也会报这个异常
     * 但这个异常经调式在表不存在是会被 BadSqlGrammarException 异常包装
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleSQLSyntaxErrorException(HttpServletRequest request, SQLSyntaxErrorException ex) {
        return createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "数据库请求错误" + ex.getCause().getMessage(), request.getRequestURI(), ex);
    }
    /**
     * sql异常，也可能是数据不对导致的sql执行问题
     * 表不存在也会报这个异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleBadSqlGrammarException(HttpServletRequest request, BadSqlGrammarException ex) {
        return createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "数据库请求错误" + ex.getCause().getMessage(), request.getRequestURI(), ex);
    }

    /**
     * sql异常，违反数据库约束，如：唯一索引
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleDuplicateKeyException(HttpServletRequest request, DuplicateKeyException ex) {
        return createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "违反数据库约束" + ex.getCause().getMessage(), request.getRequestURI(), ex);
    }

    /**
     * 一般是后台@RequestBody接收参数，但客户端没有传导致
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "没有可用参数或参数格式不正确", request.getRequestURI(), ex);
    }

    /**
     * 上传文件超过限制异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMaxUploadSizeExceededException(HttpServletRequest request, MaxUploadSizeExceededException ex) {
        long size = ex.getMaxUploadSize();
        if (size < 0) {
            if (ex.getCause() instanceof IllegalStateException && ex.getCause().getCause() instanceof FileSizeLimitExceededException) {
                size = ((FileSizeLimitExceededException) ex.getCause().getCause()).getPermittedSize();
            }
        }
        Response rm = createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "超过上传限制，允许最大值" + size, request.getRequestURI(), ex);
        return rm;
    }
    /**
     * 使用注解 {@code @Valid} 且在类上标记 {@code Validated} 会抛出该异常
     * 注意：经测试 在controller 中使用 {@code @Valid} 不是该异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        ConstraintViolation<?> next = ex.getConstraintViolations().iterator().next();
        String message = next.getMessage();
        String propertyName = ((PathImpl) ((ConstraintViolationImpl) next).getPropertyPath()).getLeafNode().asString();
        return createRM(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, message, propertyName, ex);
    }

    /**
     * 其它不可预知的异常，通常定义为系统异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<Response> handleException(HttpServletRequest request, Exception ex) {

        // 兼容一下内部 BizException
        ResponseEntity<Response> responseResponseEntity = handleCauseBizException(request, ex, 5);
        if (responseResponseEntity != null) {
            return responseResponseEntity;
        }

        log.error("系统内部异常：{}",ex.getMessage(),ex);
        String stacktraceToString = ExceptionUtil.stacktraceToString(ex);

        NotifyParam notifyParam = NotifyParam.system();
        notifyParam.setContentType("global.restcontrolleradvice.error.exception");
        notifyParam.setTitle("系统内部异常");
        notifyParam.setContent(stacktraceToString);
        NotifyTool.notify(notifyParam);
        Response rm = createRM(ErrorCodeGlobalEnum.SYSTEM_ERROR, "系统内部异常", null, ex);

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        if (globalMvcExceptionListeners != null) {

            GlobalMvcExceptionDTO globalMvcExceptionDTO = new GlobalMvcExceptionDTO();
            globalMvcExceptionDTO.setException(ex);
            globalMvcExceptionDTO.setStacktrace(stacktraceToString);
            globalMvcExceptionDTO.setRequest(request);
            globalMvcExceptionDTO.setResponseBody(rm);
            globalMvcExceptionDTO.setResponseHttpStatus(httpStatus);
            for (GlobalMvcExceptionListener globalMvcExceptionListener : globalMvcExceptionListeners) {
                globalMvcExceptionListener.onException(globalMvcExceptionDTO);
            }
        }

        return ResponseEntity.status(httpStatus)
                .body(rm);
    }

    /**
     * 获取原始异常
     * @param request
     * @param ex
     * @param deep 深度最小为1
     * @return
     */
    private ResponseEntity<Response> handleCauseBizException(HttpServletRequest request,Throwable ex,int deep) {
        if(deep <= 0 || ex == null){
            return null;
        }
        if (ex != null && ex instanceof BizException) {
            return handleBizException(request, (BizException) ex);
        }else {
            return handleCauseBizException(request, ex.getCause(),deep - 1);
        }

    }
}
