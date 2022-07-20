package com.particle.global.validation.form;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 感觉使用脚本验证器需要拼接大量字字符串脚本，不够优雅
 * 所以添加了表单验证器，旨在表单内问完成校验，可以访问表单本身的所有字段
 * 只能标注在一个form表单对象上，暂时没有考虑其它情况
 * @author yangwei
 * @since 2019/11/28 8:54
 */
@Target({ TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FormValidator.class })
public @interface Form {

    String message() default "表单验证不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    // 这里也添加了一个作用到哪个属性，如果只有一个可以在这里用，如果有多个不支持，只能在验证时动态指定
    String reportOn() default "";

    // 如果需要字典转code请指定字典的属性
    String[] dictIdProp() default {};
}
