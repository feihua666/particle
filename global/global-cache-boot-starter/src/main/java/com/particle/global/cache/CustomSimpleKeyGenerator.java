package com.particle.global.cache;

import org.springframework.cache.interceptor.SimpleKeyGenerator;

import java.lang.reflect.Method;

/**
 * <p>
 * 自定义缓存key生成器
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 10:09
 */
public class CustomSimpleKeyGenerator extends SimpleKeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if (target == null && method == null) {
            return SimpleKeyGenerator.generateKey(params);
        }else {
            return super.generate(target, method, params);
        }
    }
}
