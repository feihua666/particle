package com.particle.global.light.share.trans.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注到类上，用来全局标识需要被翻译的字段们
 * 添加该注解主要用来解决grape.common.service.trans.TransBy或grape.common.service.trans.TransFor不方便标注到父类的字段时使用
 *
 *
 * Created by yangwei
 * Created at 2019/10/9 9:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface Trans {
    TransItem [] value();
}
