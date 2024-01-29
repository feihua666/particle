package com.particle.global.trans.helper;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.particle.global.dto.basic.VO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.RawResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.servlet.RequestTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 翻译处理
 * 注意：使用@RestController注解或@ResponseBody注解才能被拦截到
 * Created by yangwei
 * Created at 2019/7/26 19:42
 */
@RestControllerAdvice
@Slf4j
public class GlobalTransResponseBodyAdvice implements ResponseBodyAdvice {
    /**
     * 哪些类或注解处理
     */
    private static final Class[] SUPPORTS = {
            VO.class,
            PageResponse.class,
            SingleResponse.class,
            MultiResponse.class,
            RawResponse.class,
            List.class
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

        // 忽略开放接口翻译
        // if (request instanceof HttpServletRequest) {
        //     String requestURIStr = RequestTool.resolveRequestURIStr((HttpServletRequest) request, false);
        //     if (StrUtil.startWith(requestURIStr,"/openapi")) {
        //         return body;
        //     }
        // }
        if (body != null) {
            return TransTool.trans(body);
        }

        // 正常数据不再包装
        return body;
    }

}
