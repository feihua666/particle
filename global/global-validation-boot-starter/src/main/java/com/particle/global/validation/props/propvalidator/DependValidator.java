package com.particle.global.validation.props.propvalidator;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import com.particle.global.validation.props.IPropValidator;
import com.particle.global.validation.props.PropValid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author yangwei
 * @since 2019/12/6 9:56
 */
@Component
public class DependValidator implements IPropValidator<PropValid.Depend> {
    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.Depend;
    }

    @Override
    public boolean valid(Object value, PropValid.Depend annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {

        boolean dependPropHasValue = annotation.dependPropHasValue();
        Object dependOnValue = ReflectUtil.getFieldValue(value, annotation.dependProp());
        if (dependPropHasValue) {
            if (dependOnValue != null && (fieldValue == null || (fieldValue instanceof String && StrUtil.isEmpty(((String) fieldValue))) )) {
                validResult.setErrorMsg(annotation.message());
                return false;
            }
        }else {
            if (dependOnValue == null && (fieldValue == null || (fieldValue instanceof String && StrUtil.isEmpty(((String) fieldValue))) )) {
                validResult.setErrorMsg(annotation.message());
                return false;
            }
        }


        return true;
    }
}
