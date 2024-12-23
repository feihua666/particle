package com.particle.global.validation;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单验证帮助类
 * @author yangwei
 * @since 2019/12/6 10:18
 */
@Component
public class ValidHelper{

    public static final String PATTERN_ALIAS_MOBILE = "mobile";
    public static final String PATTERN_ALIAS_EMAIL = "email";

    private static Map<String,String> patternAliasMap = null;
    private static List<String> patternAliasScript = null;
    static {
        patternAliasMap = new HashMap<>(2);
        patternAliasMap.put(PATTERN_ALIAS_MOBILE, PatternPool.MOBILE.pattern());
        patternAliasMap.put(PATTERN_ALIAS_EMAIL, PatternPool.EMAIL.pattern());

        patternAliasScript = new ArrayList<>(patternAliasMap.size());
        patternAliasMap.forEach((key,pattern) -> {
            patternAliasScript.add("var " + key + " = function (value){return !value? true: new RegExp(/"+pattern+"/).test(value)};");
        });
    }
    /**
     * 添加错误消息到字段上
     * @param reportOn
     * @param message
     * @param constraintValidatorContext
     */
    public void reportMessageOnProp(String reportOn, String message, ConstraintValidatorContext constraintValidatorContext){
        if(!StrUtil.isEmpty(reportOn) && !StrUtil.isEmpty(message)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(reportOn).addConstraintViolation();
        }
    }

    /**
     * 正则别名
     * @return
     */
    public Map<String,String> getPatternAliasMap(){
        return patternAliasMap;
    }
    /**
     * 正则别名为函数名的验证脚本
     * @return
     */
    public List<String> getPatternAliasScript (){
        return patternAliasScript;

    };
}
