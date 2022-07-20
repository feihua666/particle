package com.particle.global.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 手机号验证
 * @author yangwei
 * @since 2019/11/27 13:43
 */
@Target({ FIELD})
@Retention(RUNTIME)
//指定验证器
@Constraint(validatedBy = {MobileValidator.class})
@Documented
public @interface Mobile {
    //默认错误消息
    String message() default "手机号格式不正确";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };

}
