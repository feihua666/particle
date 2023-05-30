package com.particle.global.validation.props.propvalidator;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.validation.ValidHelper;
import com.particle.global.validation.depend.DependFieldValidator;
import com.particle.global.validation.form.ValidContext;
import com.particle.global.validation.form.ValidResult;
import com.particle.global.validation.props.IPropValidator;
import com.particle.global.validation.props.PropValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yangwei
 * @since 2019/12/6 9:56
 */
@Component
public class DependConditionValidator implements IPropValidator<PropValid.DependCondition> {


    @Autowired
    private ValidHelper validHelper;

    @Override
    public boolean support(Annotation annotation) {
        return annotation instanceof PropValid.DependCondition;
    }

    @Override
    public boolean valid(Object value, PropValid.DependCondition annotation, Object fieldValue, Field field, ValidResult validResult, ValidContext validContext) {

        Object dependOnValue = ReflectUtil.getFieldValue(value, annotation.dependProp());

        if (dependOnValue != null) {
            boolean r = true;
            String isEqual = annotation.ifEqual();

            boolean isAllAny = DependFieldValidator.isAllAny(dependOnValue, isEqual);
            if(isAllAny){
                String pattern = annotation.thenPattern();
                if(StrUtil.isEmpty(pattern) && !StrUtil.isEmpty(annotation.patternAlias())){
                    pattern = validHelper.getPatternAliasMap().get(annotation.patternAlias());
                    if (!StrUtil.isEmpty(pattern)) {
                        r = ReUtil.isMatch(pattern,fieldValue.toString());
                    }else {
                        throw new RuntimeException("不支持的正则别名" + annotation.patternAlias()+",请参考 "+ ValidHelper.class.getName() + ".patternAliasMap");
                    }
                }
                if (StrUtil.isEmpty(pattern)) {
                    r = fieldValue != null;
                }
            }
            if(!r){
                validResult.setErrorMsg(annotation.message());
                return false;
            }
        }

        return true;
    }
}
