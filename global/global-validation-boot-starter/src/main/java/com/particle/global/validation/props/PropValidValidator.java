package com.particle.global.validation.props;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.validation.ValidHelper;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 主要用来属性验证扩展
 * @author yangwei
 * @since 2019/11/28 8:57
 */
@Component
@Slf4j
public class PropValidValidator implements ConstraintValidator<PropValid, Object> {

    
    private ValidHelper validHelper;
    
    private String message;

    @Autowired
    private List<IPropValidator> iPropValidators;


    @Override
    public void initialize(PropValid constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 不验证空
        if (value == null) {
            return true;
        }
        ValidContext validContext = new ValidContext();
        ValidResult validResult = new ValidResult();

        for (Field field : ReflectUtil.getFields(value.getClass())) {
            Annotation[] annotations = AnnotationUtil.getAnnotations(field, false);
            for (Annotation annotation : annotations) {
                for (IPropValidator iPropValidator : iPropValidators) {
                    if(iPropValidator.support(annotation)){
                        if(!iPropValidator.valid(value,annotation, ReflectUtil.getFieldValue(value,field),field,validResult,validContext)){
                            String reportOn = validResult.getReportOn();
                            String message = validResult.getErrorMsg();
                            if(StrUtil.isEmpty(reportOn)){
                                reportOn = field.getName();
                            }
                            if(StrUtil.isEmpty(message)){
                                message = this.message;
                            }
                            validHelper.reportMessageOnProp(reportOn,message,constraintValidatorContext);
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
    
    @Autowired
    public void setValidHelper(ValidHelper validHelper) {
        this.validHelper = validHelper;
    }
}
