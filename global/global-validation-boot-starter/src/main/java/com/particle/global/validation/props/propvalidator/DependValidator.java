package com.particle.global.validation.props.propvalidator;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import com.particle.global.validation.props.IPropValidator;
import com.particle.global.validation.props.PropValid;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

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
            if (dependOnValue == null && (fieldValue == null
                                            || (fieldValue instanceof String && StrUtil.isEmpty(((String) fieldValue)))
                                            || (fieldValue instanceof Collection && CollectionUtil.isEmpty(((Collection) fieldValue)))
            )) {
                validResult.setErrorMsg(annotation.message());
                return false;
            }
        }


        return true;
    }
}
