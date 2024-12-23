package com.particle.global.validation.depend;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.validation.ValidHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
        // 不验证空，value为需要验证的对象比如一个 UserCreateCommand
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

        boolean isAllAny = isAllAny(dependValue, equal);
        if(isAllAny){

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


    public static boolean isEqual(Object dependValue, String equalStr) {
        return Objects.equals(dependValue,equalStr)
                || (dependValue instanceof Boolean) && Boolean.toString(((Boolean) dependValue)).equals(equalStr);
    }
    public static boolean isNull(Object dependValue, String equalStr) {
        return "null".equals(equalStr) && dependValue == null;
    }
    public static boolean isEmpty(Object dependValue, String equalStr) {
        return "empty".equals(equalStr)
                && (dependValue == null
                    || (dependValue instanceof String && StrUtil.isEmpty(dependValue.toString()))
                    || (dependValue instanceof Collection && CollectionUtil.isEmpty((Collection)dependValue)));
    }

    public static boolean isAllAny(Object dependValue, String equalStr) {
        return isEqual(dependValue, equalStr) || isNull(dependValue, equalStr) || isEmpty(dependValue, equalStr);
    }
}
