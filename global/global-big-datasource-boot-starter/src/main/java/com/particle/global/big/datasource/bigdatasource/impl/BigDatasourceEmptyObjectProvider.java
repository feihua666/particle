package com.particle.global.big.datasource.bigdatasource.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/12/9 20:08
 */
public class BigDatasourceEmptyObjectProvider<T> implements ObjectProvider<T> {

    private T object;

    public BigDatasourceEmptyObjectProvider(T object) {
        this.object = object;
    }

    public T getObject(Object... args) throws BeansException {
        return object;
    }

    public T getIfAvailable() throws BeansException {
        return object;
    }

    public T getIfUnique() throws BeansException {
        return object;
    }

    public T getObject() throws BeansException {
        return object;
    }

    public void forEach(Consumer action) {
    }

    @Override
    public Stream<T> stream() {
        return Stream.empty();
    }

    @Override
    public Stream<T> orderedStream() {
        return Stream.empty();
    }

    /**
     * 创建实例
     * @return
     * @param <T>
     */
    public static <T> BigDatasourceEmptyObjectProvider<T> create() {
        return new BigDatasourceEmptyObjectProvider<T>(null);
    }
    /**
     * 创建实例
     * @return
     * @param <T>
     */
    public static <T> BigDatasourceEmptyObjectProvider<T> create(T object) {
        return new BigDatasourceEmptyObjectProvider<T>(object);
    }
}
