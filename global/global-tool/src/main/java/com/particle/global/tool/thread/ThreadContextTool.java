package com.particle.global.tool.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程变量工具类
 * @author yangwei
 * @since 2017/9/25 20:19
 */
@Slf4j
public class ThreadContextTool {
    private static final ThreadLocal<Map<Object, Object>> RESOURCES = new InheritableThreadLocalMap<>();

    protected ThreadContextTool() {
    }

    public static Map<Object, Object> getResources() {
        return (Map)(RESOURCES.get() == null? Collections.emptyMap():(Map) RESOURCES.get());
    }

    public static void setResources(Map<Object, Object> newResources) {
        if(!CollectionUtils.isEmpty(newResources)) {
            ensureResourcesInitialized();
            ((Map) RESOURCES.get()).clear();
            ((Map) RESOURCES.get()).putAll(newResources);
        }
    }

    /**
     * 获取线程变量的map
     * @param key
     * @return
     */
    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = (Map) RESOURCES.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    private static void ensureResourcesInitialized() {
        if(RESOURCES.get() == null) {
            RESOURCES.set(new HashMap());
        }

    }

    public static Object get(Object key) {
        if(log.isTraceEnabled()) {
            String msg = "get() - in thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        Object value = getValue(key);
        if(value != null && log.isTraceEnabled()) {
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" + key + "] " + "bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    /**
     * 将数据添加到线程变量
     * @param key 线程变量的key
     * @param value 线程变量的value
     */
    public static void put(Object key, Object value) {
        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else if(value == null) {
            remove(key);
        } else {
            ensureResourcesInitialized();
            ((Map) RESOURCES.get()).put(key, value);
            if(log.isTraceEnabled()) {
                String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" + key + "] to thread " + "[" + Thread.currentThread().getName() + "]";
                log.trace(msg);
            }

        }
    }

    /**
     * 移除线程变量
     * @param key 线程变量的key
     * @return
     */
    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = (Map) RESOURCES.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;
        if(value != null && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" + key + "]" + "from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }
        // 这里如果为空了就直接将本地线程变量清空，以免出现内存泄漏
        if (perThreadResources != null && perThreadResources.isEmpty()) {
            remove();
        }

        return value;
    }

    /**
     * 移除全部，请谨慎使用
     */
    public static void remove() {
        RESOURCES.remove();
    }


    /**
     * 可继承父级的线程变量的类
     * 但一般在线程池中也会有问题，请使用时尽量手动放入线程变量
     * @param <T>
     */
    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        private InheritableThreadLocalMap() {
        }

        @Override
		protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            return parentValue != null?(Map)((HashMap)parentValue).clone():null;
        }
    }
}
