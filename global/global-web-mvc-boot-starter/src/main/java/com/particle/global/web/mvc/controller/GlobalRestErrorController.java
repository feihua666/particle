package com.particle.global.web.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;
import com.particle.global.tool.json.JsonTool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
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

    public GlobalRestErrorController(@Autowired ErrorAttributes errorAttributes,
                                     @Autowired ServerProperties serverProperties,
                                     @Autowired(required = false)  List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
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
        if (attribute instanceof Exception) {
            throw ((Exception) attribute);
        }
        HttpStatus status = getStatus(request);
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
