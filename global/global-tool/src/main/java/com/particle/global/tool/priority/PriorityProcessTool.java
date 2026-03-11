package com.particle.global.tool.priority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * 优先执行处理器工具类
 * </p>
 *
 * @author yangwei
 * @since 2026/1/16 09:32
 */
public class PriorityProcessTool<T> {

    private final List<Processor<T>> processors = new ArrayList<>();
    private Supplier<T> defaultSupplier;

    /**
     * 创建处理器实例
     */
    public static <T> PriorityProcessTool<T> create() {
        return new PriorityProcessTool<>();
    }

    /**
     * 添加处理器（无条件）
     * @param supplier 处理函数
     * @return this
     */
    public PriorityProcessTool<T> add(Supplier<T> supplier) {
        return add(() -> true, supplier);
    }

    /**
     * 添加处理器（带条件）
     * @param condition 执行条件
     * @param supplier 处理函数
     * @return this
     */
    public PriorityProcessTool<T> add(Supplier<Boolean> condition, Supplier<T> supplier) {
        processors.add(new Processor<>(condition, supplier));
        return this;
    }

    /**
     * 批量添加处理器（无条件）
     * @param suppliers 处理器列表
     * @return this
     */
    @SafeVarargs
    public final PriorityProcessTool<T> addAll(Supplier<T>... suppliers) {
        for (Supplier<T> supplier : suppliers) {
            add(supplier);
        }
        return this;
    }

    /**
     * 批量添加处理器（通过列表）
     * @param suppliers 处理器列表
     * @return this
     */
    public PriorityProcessTool<T> addAll(List<Supplier<T>> suppliers) {
        for (Supplier<T> supplier : suppliers) {
            add(supplier);
        }
        return this;
    }

    /**
     * 设置默认处理器
     * @param defaultSupplier 默认处理函数
     * @return this
     */
    public PriorityProcessTool<T> withDefault(Supplier<T> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    /**
     * 执行处理
     * @return 处理结果
     */
    public T execute() {
        for (Processor<T> processor : processors) {
            Boolean conditionResult = processor.condition.get();
            if (conditionResult != null && conditionResult) {  // 添加null检查
                T result = processor.supplier.get();
                if (result != null) {
                    return result;
                }
            }
        }

        if (defaultSupplier != null) {
            return defaultSupplier.get();
        }

        return null;
    }

    /**
     * 执行处理（返回Optional）
     */
    public Optional<T> executeOptional() {
        return Optional.ofNullable(execute());
    }

    /**
     * 执行处理，必须有结果
     * @throws IllegalStateException 如果没有处理器返回结果
     */
    public T executeOrThrow() {
        T result = execute();
        if (result == null) {
            throw new IllegalStateException("No processor returned a result");
        }
        return result;
    }

    /**
     * 执行处理，自定义异常
     */
    public T executeOrThrow(Supplier<RuntimeException> exceptionSupplier) {
        T result = execute();
        if (result == null) {
            throw exceptionSupplier.get();
        }
        return result;
    }

    private static class Processor<T> {
        final Supplier<Boolean> condition;
        final Supplier<T> supplier;

        Processor(Supplier<Boolean> condition, Supplier<T> supplier) {
            this.condition = condition;
            this.supplier = supplier;
        }
    }


}
