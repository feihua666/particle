package com.particle.global.messaging.event.messaging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 消息 消息监听注解 包装
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CloudStreamConsume {
}
