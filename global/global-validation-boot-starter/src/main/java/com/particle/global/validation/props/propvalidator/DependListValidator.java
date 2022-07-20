package com.particle.global.validation.props.propvalidator;


import org.springframework.beans.factory.annotation.Autowired;
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
public class DependListValidator implements IPropValidator<PropValid.DependList> {


    @Autowired
    private DependValidator dependValidator;

    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.DependList;
    }

    @Override
    public boolean valid(Object value, PropValid.DependList annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {
        for (PropValid.Depend depend : annotation.value()) {
            if(!dependValidator.valid(value,depend,fieldValue,field,validResult,validContext)){
                return false;
            }
        }
        return true;
    }
}
