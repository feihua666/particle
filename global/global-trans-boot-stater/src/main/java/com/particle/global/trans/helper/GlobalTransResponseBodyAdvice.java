package com.particle.global.trans.helper;

import com.particle.global.dto.basic.VO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.RawResponse;
import com.particle.global.dto.response.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 翻译处理
 * Created by yangwei
 * Created at 2019/7/26 19:42
 */
@Component
@Aspect
@Slf4j
public class GlobalTransResponseBodyAdvice {
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

    List<Class> SUPPORT_CLASSES = Arrays.stream(SUPPORTS).collect(Collectors.toList());
    /**
     * {@code @Pointcut} 注解参考 {@url https://blog.csdn.net/zhengchao1991/article/details/53391244}
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && execution(public * *(..))")
    public void pointcut(){
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        if (result != null && supports(result)) {
            TransTool.trans(result);
        }
    }
    public boolean supports(Object result) {
        Class containingClass = result.getClass();
        return SUPPORT_CLASSES.stream().anyMatch(supportClass ->supportClass.isAssignableFrom(containingClass));
    }
}
