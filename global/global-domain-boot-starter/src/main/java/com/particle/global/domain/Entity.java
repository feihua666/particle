package com.particle.global.domain;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * <p>
 * 被 {@code @Entity} 标记的类托管到 Spring 中非单例存在
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 17:39
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface Entity {
}
