package com.particle.global.bootstrap.boot;

import org.springframework.boot.ApplicationArguments;

/**
 * 服务启动完成调用
 * @see OnCommandLineListener
 * 为了统一服务启动完成调用，请实现 scatter.common.boot.CommonApplicationRunner 接口来回调
 * 注：添加该服务启动后调用当前是为了添加数据对象使用
 * Created by yangwei
 * Created at 2021/4/1 10:39
 */
public interface OnApplicationRunnerListener {
    void run(ApplicationArguments args) throws Exception;
}
