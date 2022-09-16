package com.particle.global.web.mvc.controller;

import cn.hutool.core.bean.BeanUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
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

import javax.servlet.http.HttpServletRequest;
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

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity<>(status);
        }
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        SingleResponse singleResponse = SingleResponse.buildFailure(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR);
        singleResponse.setData(body);
        Map<String, Object> objectMap = BeanUtil.beanToMap(singleResponse);
        log.error("请求有错误，响应内容: {}", JsonTool.toJsonStr(objectMap));
        return new ResponseEntity<>(objectMap, status);
    }
}
