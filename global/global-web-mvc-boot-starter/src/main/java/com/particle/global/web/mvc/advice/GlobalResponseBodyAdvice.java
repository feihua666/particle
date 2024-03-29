package com.particle.global.web.mvc.advice;

import com.particle.global.dto.basic.VO;
import com.particle.global.dto.response.*;
import com.particle.global.exception.code.IErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局响应格式封装
 * 注意：使用@RestController注解或@ResponseBody注解才能被拦截到
 * Created by yangwei
 * Created at 2019/7/26 19:42
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice {
    /**
     * 哪些类或注解处理
     */
    private static final Class[] SUPPORTS = {
            VO.class,
            PageResponse.class,
            SingleResponse.class,
            MultiResponse.class,
            RawResponse.class,
            List.class,
            Response.class,
            Object.class,
    };

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        Class containingClass = returnType.getContainingClass();

        List<Class> classes = new ArrayList<>();
        classes.addAll(Arrays.stream(SUPPORTS).collect(Collectors.toList()));
        return classes.stream().anyMatch(anno ->
                anno.isAnnotation() && (element.isAnnotationPresent(anno) || containingClass !=null && containingClass.isAnnotationPresent(anno) )
                        || anno.isAssignableFrom(containingClass) || anno.isAssignableFrom(returnType.getMethod().getReturnType())

        );
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 根据响应数据设置响应状态码，以反应再加准备的数据
        if (body instanceof Response) {
            Long status = ((Response) body).getStatus();
            if (status != null) {
                int statusCode = IErrorCode.httpStatusResolve(status);
                response.setStatusCode(HttpStatus.valueOf(statusCode));
            }

        }
        // 正常数据不再包装
        return body;
    }
}
