package com.particle.particledemo.test;

import cn.hutool.core.annotation.AnnotationUtil;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/10/9 16:47
 */
public class TempTest {
    public static void main(String[] args) {
        // System.out.println(AnnotationUtils.findAnnotation(AreaPageQueryCommand.class, ParameterObject.class));
        // System.out.println( AnnotationUtil.getAnnotation(AreaPageQueryCommand.class, ParameterObject.class));
        Annotation[] annotations = AnnotationUtil.getAnnotations(AreaPageQueryCommand.class, false);
        System.out.println(annotations);
        // System.out.println( AnnotatedElementUtils.isAnnotated(AreaPageQueryCommand.class, ParameterObject.class));
    }
}
