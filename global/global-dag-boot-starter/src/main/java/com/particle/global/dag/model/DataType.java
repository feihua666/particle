package com.particle.global.dag.model;

/**
 * <p>
 * 数据类型枚举
 * </p>
 * <p>
 * 用于端口声明的类型约束，前端和后端保持一致。
 * </p>
 *
 * @author particle
 * @since 2026-04-29
 */
public enum DataType {

    /** 字符串 */
    STRING,

    /** 数字 */
    NUMBER,

    /** 布尔值 */
    BOOLEAN,

    /** JSON 对象 */
    JSON,

    /** 数组 */
    ARRAY,

    /** 二进制数据 */
    BINARY,

    /** 任意类型 */
    ANY
}
