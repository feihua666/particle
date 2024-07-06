package com.particle.global.data.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个数据约束，用于限制数据权限
 * Created by yangwei
 * Created at 2021/4/1 10:02
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.PARAMETER })
public @interface DataConstraint {

    /**
     * dataObjectCode 数据对象编码
     * @return
     */
    String dataObject();

    /**
     * 数据对象名称
     * @return
     */
    String name();

    /**
     * 描述备注
     * @return
     */
    String remark() default "";

    /**
     * 是否忽略
     * @return
     */
    boolean ignore() default false;
}
