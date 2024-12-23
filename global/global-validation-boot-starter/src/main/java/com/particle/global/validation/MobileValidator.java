package com.particle.global.validation;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @since 2019/11/27 13:52
 */
@Component
public class MobileValidator implements ConstraintValidator<com.particle.global.validation.Mobile, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StrUtil.isEmpty(value)){
            return Validator.isMobile(value);
        }
        return true;
    }
}
