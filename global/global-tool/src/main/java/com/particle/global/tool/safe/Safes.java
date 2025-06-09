package com.particle.global.tool.safe;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;
/**
 * <p>
 * 安全操作类
 * </p>
 *
 * @author yangwei
 * @since 2025/6/5 16:02
 */
public class Safes {
    private Safes() {

    }

    public static <T> Stream<T> ofDefault(Stream<T> source) {
        return Optional.ofNullable(source)
                .orElse(Stream.empty());
    }

    public static <T> T[] ofDefault(T[] source) {
        return ofDefault(source, (T[]) Collections.emptyList().toArray());
    }

    public static <T> T ofDefault(T source, T defaultValue) {
        return Optional.ofNullable(source)
                .orElse(defaultValue);
    }

    public static <T> Collection<T> ofDefault(Collection<T> source) {
        return ofDefault(source, Collections.emptyList());
    }

    public static BigDecimal ofDefault(BigDecimal source) {
        return ofDefault(source, BigDecimal.ZERO);
    }

    public static Double ofDefault(Double source) {
        return ofDefault(source, 0.0d);
    }

    public static Integer ofDefault(Integer source) {
        return ofDefault(source, 0);
    }

    public static Long ofDefault(Long source) {
        return ofDefault(source, 0L);
    }

    public static String ofDefault(String source) {
        return ofDefault(source, "");
    }

    public static <T> Iterator<T> ofDefault(Iterator<T> it) {
        return ofDefault(it, Collections.emptyIterator());
    }

    public static <T> Iterable<T> ofDefault(Iterable<T> it) {
        return ofDefault(it, Collections.emptyList());
    }

    public static <T> Stream<T> peek(Stream<T> source, BiConsumer<Integer, T> action) {
        final AtomicInteger idx = new AtomicInteger(0);
        return ofDefault(source)
                .peek(each -> action.accept(idx.getAndIncrement(), each));
    }

    public static <T> Long each(Stream<T> source, BiConsumer<Integer, T> action) {
        return peek(source, action).count();
    }

    public static <T> Stream<T> eachThen(Stream<T> source, BiConsumer<Integer, T> action) {
        return peek(source, action);
    }

    public static <T> Stream<T> of(Collection<T> source) {
        return ofDefault(source)
                .stream()
                .filter(Objects::nonNull);
    }

    @SafeVarargs
    public static <T> Stream<T> of(T... source) {
        return Arrays.stream(ofDefault(source))
                .filter(Objects::nonNull);
    }

    public static <K, V> Stream<Map.Entry<K, V>> of(Map<K, V> source) {
        return ofDefault(source, Collections.<K, V>emptyMap())
                .entrySet()
                .stream();
    }

    /**
     * 非异步
     *
     * @param predicate    条件
     * @param callable     执行块
     * @param defaultValue 默认值
     * @return 执行值 或者默认值
     */
    public static <T> T call(boolean predicate, Callable<T> callable, T defaultValue, Consumer<Exception> exceptionConsumer) {
        if (predicate) {
            try {
                return callable.call();
            } catch (Exception e) {
                if (exceptionConsumer != null) {
                    exceptionConsumer.accept(e);
                }
            }
        }
        return defaultValue;
    }

    public static <T> T call(boolean predicate, Callable<T> callable, T defaultValue) {
        return call(predicate, callable, defaultValue, null);
    }

    public static <T> T ofIf(boolean predicate, Callable<T> callable, T defaultValue) {
        return call(predicate, callable, defaultValue);
    }

    public static long ofLong(String numStr) {
        return ofLong(numStr, 0L);
    }

    public static long ofLong(String numStr, Long defaultValue) {
        return Safes.ofDefault(StringUtils.isNumeric(StringUtils.trimToNull(numStr)) ? Long.parseLong(numStr) : null, defaultValue);
    }

    public static int ofInt(String numStr) {
        return ofInt(numStr, 0);
    }

    public static int ofInt(String numStr, Integer defaultValue) {
        return Safes.ofDefault(StringUtils.isNumeric(StringUtils.trimToNull(numStr)) ? Integer.parseInt(numStr) : null, defaultValue);
    }

    public static Integer ofSize(Collection<?> list) {
        return list != null ? list.size() : 0;
    }

    public static Integer ofSize(String size) {
        return size != null ? size.length() : 0;
    }

    public static Integer ofSize(Object... arr) {
        return arr != null ? arr.length : 0;
    }

    public static Integer ofSize(Map<?, ?> dict) {
        return dict != null ? dict.size() : 0;
    }
}
