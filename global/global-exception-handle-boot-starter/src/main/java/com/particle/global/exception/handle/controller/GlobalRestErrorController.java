package com.particle.global.exception.handle.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.handle.GlobalExceptionAdvice;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.global.light.share.code.IErrorCode;
import com.particle.global.tool.json.JsonTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 异常处理，兼容统一处理返回请求
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalRestErrorController  extends BasicErrorController {

    @Autowired
    private GlobalExceptionAdvice globalExceptionAdvice;


    public GlobalRestErrorController(@Autowired ErrorAttributes errorAttributes,
                                     @Autowired ServerProperties serverProperties,
                                     @Autowired(required = false)  List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 一般这里是filter发生了异常，如果能获取到异常，扔到全局异常统一处理
         */
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        HttpStatus status = getStatus(request);
        HttpStatus resultStatus = status;
        /**
         * 一般是 在 render 阶段抛出了异常，又被容器转发到这里
         * 注意：如果响应对象已经是提交状态，再渲染500响应码也没有用了，因为内容只能追加会导致最终的响应结果是个四不像
         * processDispatchResult 方法 调用 render 如果 如果 render有异常会一直抛出到容器，又被容器转发到这里
         * {@link DispatcherServlet#processDispatchResult(HttpServletRequest, HttpServletResponse, HandlerExecutionChain, ModelAndView, Exception)}
         * {@link DispatcherServlet#render(ModelAndView, HttpServletRequest, HttpServletResponse)}
         */
        if (status != null && status == HttpStatus.OK && attribute != null && !response.isCommitted()) {
            /**
             * 本方法直接返回父级 {@link BasicErrorController#errorHtml(HttpServletRequest, HttpServletResponse)}，父级使用了
             */
            resultStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,resultStatus.value());

        }

        if (attribute instanceof Exception) {
            // 统一异常处理
            globalExceptionAdvice.handleException(request, (Exception) attribute,status.value());
        }
        // 这里会重定向到 /templates/error/{状态码}.{扩展名（对应的模板解析）}

        Map<String, Object> model = Collections
                .unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(resultStatus.value());
        ModelAndView modelAndView = resolveErrorView(request, response, resultStatus, model);
        ModelAndView error = (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
        error.setStatus(resultStatus);
        return error;
    }
    /**
     * error {@link org.apache.catalina.core.StandardWrapperValve#exception(org.apache.catalina.connector.Request, org.apache.catalina.connector.Response, java.lang.Throwable)}
     * @param request
     * @return
     */
    @SneakyThrows
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        /**
         * 一般这里是filter发生了异常，如果能获取到异常，扔到全局异常统一处理
         */
        Object attribute = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        HttpStatus status = getStatus(request);
        if (attribute instanceof Exception) {
            // 统一异常处理
            ResponseEntity<Response> responseResponseEntity = globalExceptionAdvice.handleException(request, (Exception) attribute,status.value());
            Map<String, Object> objectMap = BeanUtil.beanToMap(responseResponseEntity.getBody());
            return new ResponseEntity<>(objectMap, status);
        }
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }
        // key 包括：timestamp、status、error、exception、trace、path
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        SingleResponse singleResponse = null;

        Error error = JSONUtil.toBean(JsonTool.toJsonStr(body), Error.class);
        singleResponse = SingleResponse.buildFailure(getByError(error));
        singleResponse.setData(body);
        Map<String, Object> objectMap = BeanUtil.beanToMap(singleResponse);
        log.error("请求有错误，响应内容: {}", JsonTool.toJsonStr(objectMap));
        return new ResponseEntity<>(objectMap, status);
    }

    /**
     *         "error": "Not Found",
     *         "exception": "com.particle.global.exception.biz.AssertException",
     *         "path": "/admin/web/lowcode-model/loadByModelAndDatasource",
     *         "timestamp": "2023-01-05 13:09:24",
     *         "status": 404
     */
    @Data
    public static class Error{

        private String error;
        private String path;
        private Date timestamp;
        private Integer status;
    }

    private IErrorCode getByError(Error error) {
        IErrorCode errorCode = ErrorCodeGlobalEnum.BAD_REQUEST_ERROR;
        switch (error.getStatus()){
            case 404: {
                errorCode = ErrorCodeGlobalEnum.URL_NOT_FOUND;
            }
        }
        return errorCode;
    }

}
