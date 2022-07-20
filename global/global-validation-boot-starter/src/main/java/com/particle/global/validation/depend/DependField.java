package com.particle.global.validation.depend;

import com.particle.global.validation.form.FormValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 虽然加了 {@link FormValidator} 但感觉在有些场景下还是实现起来还是比较繁琐
 * 所以又加这个验证器，主要用来一个字段依赖另一个字段来判断
 * @author  yangwei
 * @since 2019/11/28 10:21
 */
@Target({ TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DependFieldValidator .class })
public @interface DependField {

    String message() default "表单验证不通过";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * 依赖哪个字段
     * @return
     */
    String dependProp();

    /**
     * 需要验证的字段
     * @return
     */
    String targetProp();

    /**
     * 表示依赖的字段值与该值相等正则验证 pattern
     * @return
     */
    String ifEqual() default "";

    /**
     * 支持正则
     * @return
     */
    String thenPattern() default "";

    /**
     * 提供默认正则内置别名
     * @return
     */
    String patternAlias() default "";

    /**
     * 字段名，输出哪个字段验证有问题
     * @return
     */
    String reportOn() default "";

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        DependField[] value();
    }
}
