package com.particle.global.validation.depend;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.validation.ValidHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * 字段依赖验证器
 * @author yangwei
 * @since 2019/11/28 8:57
 */
@Component
@Slf4j
public class DependFieldValidator implements ConstraintValidator<DependField, Object> {


    private ValidHelper validHelper;

    private String dependProp;
    private String targetProp;
    private String equal;
    private String pattern;
    private String patternAlias;
    private String message;
    private String reportOn;

    @Override
    public void initialize(DependField constraintAnnotation) {

        this.dependProp = constraintAnnotation.dependProp();
        this.targetProp = constraintAnnotation.targetProp();
        this.equal = constraintAnnotation.ifEqual();
        this.pattern = constraintAnnotation.thenPattern();
        this.patternAlias = constraintAnnotation.patternAlias();
        this.message = constraintAnnotation.message();
        this.reportOn = constraintAnnotation.reportOn();
        this.reportOn = constraintAnnotation.reportOn();

        if(StrUtil.isEmpty(this.pattern) && !StrUtil.isEmpty((this.patternAlias))){
            this.pattern = validHelper.getPatternAliasMap().get(this.patternAlias);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 不验证空
        if (value == null) {
            return true;
        }
        Object dependValue = ReflectUtil.getFieldValue(value,this.dependProp);
        Object targetValue = ReflectUtil.getFieldValue(value,this.targetProp);
        log.debug("依赖字段验证：dependValue={},ifEqual={},targetValue={}",
                dependValue,
                this.equal,
                targetValue

        );
        // 判断字符串相等只考虑了字符串的情况
        if(dependValue != null && this.equal != null && Objects.equals(dependValue.toString(),this.equal)){

            // 验证pattern
            if (targetValue != null && !ReUtil.isMatch(this.pattern,targetValue.toString())) {
                // 验证错误
                validHelper.reportMessageOnProp(this.reportOn,this.message,constraintValidatorContext);
                return false;
            }
        }
        return true;
    }

    @Autowired
    public void setValidHelper(ValidHelper validHelper) {
        this.validHelper = validHelper;
    }
}
