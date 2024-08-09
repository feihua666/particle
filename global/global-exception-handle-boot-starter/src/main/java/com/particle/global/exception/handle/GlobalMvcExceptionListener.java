package com.particle.global.exception.handle;

/**
 * <p>
 * 全局mvc架构异常监听器
 * 主要用于异常日志存储，方便后期排查问题
 * </p>
 *
 * @author yangwei
 * @since 2024/8/9 14:32
 */

public interface GlobalMvcExceptionListener {

    void onException(GlobalMvcExceptionDTO exceptionDTO);
}
