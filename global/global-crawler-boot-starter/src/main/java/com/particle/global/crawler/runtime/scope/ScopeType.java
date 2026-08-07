package com.particle.global.crawler.runtime.scope;

/**
 * 变量作用域类型
 * <p>
 * 作用域层级（从高到低）：
 * <ul>
 *   <li>RUNTIME - 全局运行时作用域，所有 Execution 共享</li>
 *   <li>EXECUTION - 单次执行作用域</li>
 *   <li>SESSION - Session 作用域</li>
 *   <li>LOOP - 循环作用域</li>
 *   <li>ACTION - 单个 Action 作用域</li>
 * </ul>
 * @author yangwei
 * @since 2026/05/12 13:00
 */
public enum ScopeType {

    /**
     * 全局运行时作用域
     */
    RUNTIME(0),

    /**
     * 单次执行作用域
     */
    EXECUTION(1),

    /**
     * Session 作用域
     */
    SESSION(2),

    /**
     * 循环作用域
     */
    LOOP(3),

    /**
     * 单个 Action 作用域
     */
    ACTION(4);

    private final int level;

    ScopeType(int level) {
        this.level = level;
    }

    /**
     * 获取作用域层级
     */
    public int getLevel() {
        return level;
    }

    /**
     * 检查当前作用域是否低于指定作用域
     */
    public boolean isBelow(ScopeType other) {
        return this.level > other.level;
    }

    /**
     * 检查当前作用域是否高于或等于指定作用域
     */
    public boolean isAboveOrEqual(ScopeType other) {
        return this.level <= other.level;
    }
}
