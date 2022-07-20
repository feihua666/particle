package com.particle.global.validation.props;

import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author yangwei
 * @since 2019/12/6 9:53
 */
public interface IPropValidator<T extends Annotation> {

    boolean support(Annotation annotation);

    boolean valid(Object value, T annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext);
}
