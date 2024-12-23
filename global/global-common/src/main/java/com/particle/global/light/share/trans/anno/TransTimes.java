package com.particle.global.light.share.trans.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注到类上，用来全局标识类对象需要多次翻译
 * 主要是为了角色间接翻译的场景，如：翻译 功能菜单字典类型，这时只能根据功能菜单翻译出字典类型id，并不成直接翻译字典类型id对应的字典数据
 *
 *
 * Created by yangwei
 * Created at 2023-04-18 22:07:47
 * @author yw
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface TransTimes {
    /**
     * 默认翻译再次解决二次字段问题
     * 合法值 2或3
     * @return
     */
    int times() default 2;
}
